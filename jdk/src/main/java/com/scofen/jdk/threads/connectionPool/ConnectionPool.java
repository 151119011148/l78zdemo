package com.scofen.jdk.threads.connectionPool;

import com.google.common.collect.Lists;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

/**
 * Create by  GF  in  10:33 2019/2/15
 * Description:等待超时模式实现数据库连接池
 * Modified  By:
 */
class ConnectionPool {

    private final LinkedList<Connection> pool = Lists.newLinkedList();

    ConnectionPool(int initialSize){
        if (initialSize > 0){
            for (int i = 0; i < initialSize; i++){
                pool.addLast(ConnectionDriver.createConnection());
            }
        }
    }

    void releaseConnection(Connection connection){
        if (connection != null){
            synchronized (pool){
                pool.addLast(connection);
                pool.notifyAll();
            }
        }
    }

    //在mills内无法获取到连接，返回null
    Connection fetchConnection(long mills)throws InterruptedException{
        synchronized (pool){
            if (mills < 0){
                while (pool.isEmpty()){
                    pool.wait();
                }
                return pool.removeFirst();
            }else {
                long future = System.currentTimeMillis() + mills;
                long remaining = mills;
                while (pool.isEmpty() && remaining > 0){
                    pool.wait(remaining);
                    remaining = future - System.currentTimeMillis();
                }
                Connection connection = null;
                if (!pool.isEmpty()){
                    connection = pool.removeFirst();
                }
                return connection;
            }
        }
    }


    static class ConnectionDriver{

         static Connection createConnection() {

            return (Connection) Proxy.newProxyInstance(ConnectionDriver.class.getClassLoader(),
                    new Class<?>[]{Connection.class}, new ConnectionHandler());

        }

        static class ConnectionHandler implements InvocationHandler{

            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                if(method.getName().equals("commit")){
                    TimeUnit.MILLISECONDS.sleep(100);
                }
                return null;
            }
        }


    }


}
