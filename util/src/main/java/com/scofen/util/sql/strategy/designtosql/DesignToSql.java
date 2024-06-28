package com.scofen.util.sql.strategy.designtosql;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.scofen.util.file.WriteFileUtil;
import com.scofen.util.mdesign.MDesignUtil;
import com.scofen.util.sql.strategy.ModelToSql;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

import static com.scofen.util.file.ReadFileUtils.design_model_path;
import static com.scofen.util.file.ReadFileUtils.translate_mapping_path;

public class DesignToSql implements ModelToSql {

     public Map<String, String> convert2Sql(List models, Map<String, Object> translateMap) {
        Map<String, String> sqlMap = new ConcurrentHashMap<>();

        ConcurrentLinkedQueue<JSONObject> treeModels = new ConcurrentLinkedQueue<>();
        ConcurrentLinkedQueue<JSONObject> needs = new ConcurrentLinkedQueue<>();
        models
                .parallelStream()
                .forEach(model -> collectTreeModel((JSONObject) model, treeModels, needs));

        WriteFileUtil.writeFile(design_model_path, JSON.toJSONString(needs));
        WriteFileUtil.writeFile(translate_mapping_path, JSON.toJSONString(translateMap).replace(" ", "_"));

        return sqlMap;

    }


    private static void collectTreeModel(JSONObject model,
                                         ConcurrentLinkedQueue<JSONObject> treeModels,
                                         ConcurrentLinkedQueue<JSONObject> needs) {
        if (StringUtils.equals("Profile", model.getString("metaclass"))){
            return;
        }
        if (StringUtils.equals("Class", model.getString("metaclass"))
                || StringUtils.equals("Property", model.getString("metaclass"))){
            needs.add(model);
        }
        if (!model.getBooleanValue("hasChild")){
            return;
        }
        JSONArray children = MDesignUtil.listChildren("129qu38t-6w8-622", model.getString("id"));
        children
                .parallelStream()
                .forEach(node -> collectTreeModel((JSONObject) node, treeModels, needs));
    }

}
