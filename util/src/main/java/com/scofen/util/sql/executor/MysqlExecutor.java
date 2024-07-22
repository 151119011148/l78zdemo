package com.scofen.util.sql.executor;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class MysqlExecutor {

    public static String url = "jdbc:mysql://localhost:3306/l78z";
    public static String user = "root";
    public static String password = "Gf651125";

    private static Statement statement;

    static {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, user, password);
            statement = connection.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Map<String, String> execute(Map<String, String> sqlMap) {
        Map<String, String> result = new ConcurrentHashMap<>();
        Set<String> executed = ConcurrentHashMap.newKeySet();
        Set<String> notExecuted = ConcurrentHashMap.newKeySet();

        sqlMap.keySet().forEach(key -> {
            try {
                String sql = sqlMap.get(key);
                if (sql.contains("DROP TABLE")) {
                    doExecute(sql);
//                    executed.add(key);
                } else {
                    notExecuted.add(key);
                }
            } catch (Exception e) {
                log.warn("current sql execute failed! sql is {}", sqlMap.get(key));
            }
        });

        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();
            int count = 0;
            while (CollectionUtils.isNotEmpty(notExecuted) && count < 10) {
                count ++;
                sqlMap.keySet()
                        .parallelStream()
                        .forEach(key -> {
                            if (executed.contains(key)) {
                                return;
                            }
                            String sql = null;
                            try {
                                sql = sqlMap.get(key);
                                statement.executeUpdate(sql);
                                executed.add(key);
                                notExecuted.remove(key);
                            } catch (SQLException e) {
                                log.warn("sql execute failed! sql is {}", sql);
                            }
                        });
            }
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        notExecuted.parallelStream().forEach(key -> result.put(key, sqlMap.get(key)));
        return result;
    }

    private static void doExecute(String sql) {
        try {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
