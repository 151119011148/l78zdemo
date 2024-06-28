package com.scofen.util.sql.strategy;

import java.util.List;
import java.util.Map;

public interface ModelToSql {

      Map<String, String> convert2Sql(List models, Map<String, Object> translateMap);

}
