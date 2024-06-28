package com.scofen.util.sql;

import com.alibaba.fastjson.JSONArray;
import com.scofen.util.file.ReadFileUtils;
import com.scofen.util.mdesign.MDesignUtil;
import com.scofen.util.sql.strategy.ModelToSqlFactory;
import com.scofen.util.translation.youdao.TranslateUtil;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.scofen.util.file.ReadFileUtils.translate_mapping_path;

public class Model2SqlUtils {

    static Map<String, Object> translateMap = new HashMap<>();
    static Map<String, Object> translate_null_Map = new HashMap<>();
    static {
        Map<String, Object> sourceMap = ReadFileUtils.getJSONObject(translate_mapping_path);
        sourceMap.forEach((k,v) -> {
            if (StringUtils.isNotEmpty(k)){
                translateMap.put(k, ((String)v).toLowerCase());
                if (StringUtils.isEmpty(v.toString())){
                    translate_null_Map.put(k,v);
                    String translateText = TranslateUtil.doSingleTranslate(k);
                    translateMap.put(k, translateText.replace(" ", "_"));
                }
            }
        });
    }

    public static void main(String[] args) throws IOException {

        JSONArray models = MDesignUtil.listChildren("129qu38t-6w8-622", "76254024350487552");
        ModelToSqlFactory.build("DesignToSql")
                .convert2Sql(models, translateMap);


//        convert2Sql(ReadFileUtils.getJSONArray(model_path));
    }








}
