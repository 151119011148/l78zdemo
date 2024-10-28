package com.scofen.util.sql.strategy;

import com.scofen.util.sql.strategy.designtosql.DesignToSql;
import com.scofen.util.sql.strategy.xmltosql.XMLToSql;
import org.apache.commons.lang3.StringUtils;

public class ModelToSqlFactory {


    public static ModelToSql build(String strategy){
        if (StringUtils.equals(strategy, "DesignToSql")){
            return new DesignToSql();
        } else if (StringUtils.equals(strategy, "XMLToSql")) {
            return new XMLToSql();
        }
        throw new RuntimeException("current strategy invalid!");
    }
}
