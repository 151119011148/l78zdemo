package com.scofen.util.file;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.scofen.util.sql.strategy.xmltosql.GroupTreeModel;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

public class ReadFileUtils {


    public static final String translate_mapping_path = "E:\\gaofeng\\worker\\project_relation\\disa\\translate.json";
    public static final String xmi_model_path = "E:\\gaofeng\\worker\\project_relation\\disa\\华为标准_模型输出_xmi.json";
    public static final String design_model_path = "E:\\gaofeng\\worker\\project_relation\\disa\\华为标准_模型输出_design.json";
    public static final String model_sql_path = "E:\\gaofeng\\worker\\project_relation\\disa\\model_sql_path.sql";



    public static String getString(String path){
        String content = "";
        File sourceFile = new File(path);
        if (!sourceFile.exists()){
            return content;
        }
        try {
            content = new String(Files.readAllBytes(Paths.get(path)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }

    public static Map<String, Object> getJSONObject(String path){
        return JSON.parseObject(getString(path));
    }

    public static List<GroupTreeModel> getJSONArray(String path) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String sourceString = getString(path);
        TypeReference<List<GroupTreeModel>> typeRef = new TypeReference<List<GroupTreeModel>>() {};
        return objectMapper.readValue(sourceString, typeRef);
    }


}
