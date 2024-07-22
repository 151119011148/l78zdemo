package com.scofen.util.sql.strategy.designtosql;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.scofen.util.file.WriteFileUtil;
import com.scofen.util.mdesign.MDesignUtil;
import com.scofen.util.mdesign.model.InstanceVo;
import com.scofen.util.sql.Model2SqlUtils;
import com.scofen.util.sql.bean.ForeignKey;
import com.scofen.util.sql.executor.MysqlExecutor;
import com.scofen.util.sql.strategy.ModelToSql;
import com.scofen.util.translation.youdao.TranslateUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.list.SynchronizedList;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.stream.Collectors;

import java.util.concurrent.ConcurrentHashMap;

import static com.scofen.util.file.ReadFileUtils.*;

@Slf4j
public class DesignToSql implements ModelToSql {


    public Map<String, String> convert2Sql(String projectId, List<InstanceVo> models) {
        Map<String, String> sqlMap;

        ConcurrentLinkedQueue<InstanceVo> treeModels = new ConcurrentLinkedQueue<>();
        ConcurrentLinkedQueue<InstanceVo> needs = new ConcurrentLinkedQueue<>();
        models
                .parallelStream()
                .forEach(model -> collectTreeModel(projectId, model, treeModels, needs));

        WriteFileUtil.writeFile(design_model_path, JSON.toJSONString(needs));
        sqlMap = generateCreateTableSql(/*filterBlock(needs)*/needs.parallelStream().collect(Collectors.toList()), Model2SqlUtils.translateMap);

        WriteFileUtil.writeFile(translate_mapping_path, JSON.toJSONString(Model2SqlUtils.translateMap).replace(" ", "_"));
        sqlMap = MysqlExecutor.execute(sqlMap);
        WriteFileUtil.writeFile(model_sql_path, String.join("\r\n", sqlMap.values()));
        return sqlMap;

    }


    private List<InstanceVo> filterBlock(ConcurrentLinkedQueue<InstanceVo> needs) {
        List<InstanceVo> result = Lists.newArrayList();
        for (InstanceVo instance : needs) {
            List<InstanceVo> realChildren = new LinkedList<>();
            List<InstanceVo> children = instance.getChildren() == null ? new LinkedList<>() : instance.getChildren()
                    .parallelStream()
                    .filter(property -> property.getMetaClass().equals("Property") || "SysML::Blocks::Block".equals(property.getBaseSt()))
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
            for (InstanceVo child : children) {
                if (child.getMetaClass().contains("Property") && StringUtils.isNotEmpty(child.getName())) {
                    realChildren.add(child);
                }
            }
            if (CollectionUtils.isNotEmpty(realChildren)) {
                instance.setChildren(realChildren);
                result.add(instance);
            }
        }
        return result;
    }

    private static Map<String, String> generateCreateTableSql(List<InstanceVo> instanceVos, Map<String, Object> translateMap) {
        Map<String, String> result = new ConcurrentHashMap<>();
        instanceVos
                .stream()
                .forEach(instance -> {
                    StringBuffer sql = new StringBuffer();
                    String tableName = (String) translateMap.getOrDefault(instance.getName(), null);
                    if (StringUtils.isEmpty(tableName)) {
                        String translateText = TranslateUtil.doSingleTranslate(instance.getName());
                        translateMap.put(instance.getName(), translateText.replace(" ", "_"));
                    }
                    if (StringUtils.isEmpty(tableName)/* || CollectionUtils.isEmpty(instance.getChildren())*/) {
                        return;
                    }
                    List<ForeignKey> foreignKeys = Lists.newArrayList();
                    // TODO: 2024/7/22
//                    result.put(tableName + "_drop", new StringBuffer("DROP TABLE IF EXISTS ").append(tableName).append(";").toString());
                    sql.append("CREATE TABLE IF NOT EXISTS ").append("`").append(tableName).append("`").append(" (")
                            .append("`id` varchar(64) NOT NULL,");
                    instance.getChildren()
                            .forEach(property -> {
                                String columnName = (String) translateMap.getOrDefault(property.getName(), null);
                                if (StringUtils.isEmpty(columnName)) {
                                    return;
                                }
                                if (StringUtils.equals("Customization::PartProperty", property.getBaseSt())) {
                                    String typeName = ((JSONObject) property.getShowAttribute().getExtraAttribute()).getString("typeName");
                                    String relateTableName = (String) translateMap.getOrDefault(typeName, null);
                                    if (StringUtils.isEmpty(relateTableName)) {
                                        log.warn("current relateTable name is {}, but translateMap not contain!", typeName);
                                        return;
                                    }
                                    columnName = relateTableName + "_id";
                                    foreignKeys.add(ForeignKey.builder()
                                            .currentTable(tableName)
                                            .currentTableFieldName(columnName)
                                            .relateTable(relateTableName)
                                            .relateTableFieldName(columnName)
                                            .build());
                                }
                                sql
                                        .append("`").append(columnName).append("`")
                                        .append(" VARCHAR(64) DEFAULT NULL COMMENT '")
                                        .append(property.getName())
                                        .append("',");

                            });
                    sql.append("PRIMARY KEY (`id`) USING BTREE,");
                    //CONSTRAINT fk_topic_aspect_template_theme_id FOREIGN KEY `theme_id` REFERENCES `theme` ( `id` )
                    if (CollectionUtils.isNotEmpty(foreignKeys)) {
                        foreignKeys.forEach(foreignKey -> {
                            sql.append("CONSTRAINT ")
                                    .append("`").append(foreignKey.getKeyName()).append("`").append(" ")
                                    .append("FOREIGN KEY ")
                                    .append("(`").append(foreignKey.getCurrentTableFieldName()).append("`)").append(" ")
                                    .append("REFERENCES ")
                                    .append("`").append(foreignKey.getRelateTable()).append("`")
                                    .append("(`").append("id").append("`),");
                        });
                    }
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
        if (isBlock) {
            needs.add(model);
        }
//        if (!model.isHasChild()) {
//            return;
//        }
        List<InstanceVo> children = MDesignUtil.listChildren(projectId, model.getId());
        model.setChildren(children);
        treeModels.add(model);
        children
                .stream()
                .forEach(node -> collectTreeModel(projectId, node, treeModels, needs));
    }

}
