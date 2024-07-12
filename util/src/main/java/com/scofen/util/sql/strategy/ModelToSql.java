package com.scofen.util.sql.strategy;

import com.scofen.util.mdesign.model.InstanceVo;

import java.util.List;
import java.util.Map;

public interface ModelToSql {

      Map<String, String> convert2Sql(String projectId, List<InstanceVo> models, Map<String, Object> translateMap);

}
