package com.scofen.util.sql.strategy.designtosql;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.scofen.util.file.WriteFileUtil;
import com.scofen.util.mdesign.MDesignUtil;
import com.scofen.util.mdesign.model.InstanceVo;
import com.scofen.util.sql.strategy.ModelToSql;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.list.SynchronizedList;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.stream.Collectors;

import java.util.concurrent.ConcurrentHashMap;

import static com.scofen.util.file.ReadFileUtils.*;

public class DesignToSql implements ModelToSql {


    public Map<String, String> convert2Sql(String projectId, List<InstanceVo> models, Map<String, Object> translateMap) {
        Map<String, String> sqlMap = new ConcurrentHashMap<>();

        ConcurrentLinkedQueue<InstanceVo> treeModels = new ConcurrentLinkedQueue<>();
        ConcurrentLinkedQueue<InstanceVo> needs = new ConcurrentLinkedQueue<>();
        models
                .parallelStream()
                .forEach(model -> collectTreeModel(projectId, model, treeModels, needs));

//        List<InstanceVo> tables =  MDesignUtil.listSpecificInstanceByClassId(projectId, Lists.newArrayList("SysML::Blocks::Block"));

        WriteFileUtil.writeFile(design_model_path, JSON.toJSONString(needs));
        WriteFileUtil.writeFile(translate_mapping_path, JSON.toJSONString(translateMap).replace(" ", "_"));

        sqlMap = generateCreateTableSql(filterBlock(needs), translateMap);
        WriteFileUtil.writeFile(model_sql_path, String.join("\r\n", sqlMap.values()));
        return sqlMap;

    }

    private List<InstanceVo> filterBlock(ConcurrentLinkedQueue<InstanceVo> needs) {
        List<InstanceVo> result = Lists.newArrayList();
        for (InstanceVo instance : needs) {
            List<InstanceVo> realChildren = new LinkedList<>();
            List<InstanceVo> children =  instance.getChildren() == null ? new LinkedList<>() : instance.getChildren();
            for (InstanceVo child : children) {
                if (child.getMetaClass().contains("Property") && StringUtils.isNotEmpty(child.getName())){
                    realChildren.add(child);
                }
            }
            if (CollectionUtils.isNotEmpty(realChildren)){
                instance.setChildren(realChildren);
                result.add(instance);
            }
        }
        return result;
    }

    private static Map<String, String> generateCreateTableSql(List<InstanceVo> instanceVos, Map<String, Object> translateMap) {
        Map<String, String> result = new ConcurrentHashMap<>();
        instanceVos
                .parallelStream()
                .forEach(instance -> {
                    StringBuilder sql = new StringBuilder();
                    String tableName = (String) translateMap.getOrDefault(instance.getName(), null);
                    if (StringUtils.isEmpty(tableName) || CollectionUtils.isEmpty(instance.getChildren())) {
                        return;
                    }
                    sql.append("CREATE TABLE IF NOT EXISTS ").append(tableName).append(" (");
                    instance.getChildren()
                            .forEach(property -> {
                                String columnName = (String) translateMap.getOrDefault(property.getName(), null);
                                if (StringUtils.isEmpty(columnName)) {
                                    return;
                                }
                                sql.append(columnName)
                                        .append(" VARCHAR(64) DEFAULT NULL COMMENT '")
                                        .append(property.getName())
                                        .append("',");

                            });
                    sql.deleteCharAt(sql.length() - 1);
                    sql.append(")ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='")
                            .append(instance.getName())
                            .append("';");
                    result.put(instance.getName(), sql.toString());
                });
        return result;
    }


    private static void collectTreeModel(String projectId, InstanceVo model,
                                         ConcurrentLinkedQueue<InstanceVo> treeModels,
                                         ConcurrentLinkedQueue<InstanceVo> needs) {
        boolean isBlock = StringUtils.equals("Class", model.getMetaClass())
                && StringUtils.equals("SysML::Blocks::Block", model.getBaseSt());
//         boolean isProperty = StringUtils.equals("Property", model.getMetaClass());
        if (isBlock/* || isProperty*/) {
            needs.add(model);
        }
        if (!model.isHasChild()) {
            return;
        }
        List<InstanceVo> children = MDesignUtil.listChildren(projectId, model.getId());
        model.setChildren(children);
        treeModels.add(model);
        children
                .parallelStream()
                .forEach(node -> collectTreeModel(projectId, node, treeModels, needs));
    }

}
