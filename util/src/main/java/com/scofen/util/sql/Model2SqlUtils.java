package com.scofen.util.sql;

import com.scofen.util.file.ReadFileUtils;
import com.scofen.util.mdesign.MDesignUtil;
import com.scofen.util.mdesign.model.InstanceVo;
import com.scofen.util.sql.strategy.ModelToSqlFactory;
import com.scofen.util.translation.youdao.TranslateUtil;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.scofen.util.file.ReadFileUtils.translate_mapping_path;

public class Model2SqlUtils {

    public static Map<String, Object> translateMap = new HashMap<>();
    public static Map<String, Object> translate_null_Map = new HashMap<>();
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

    public static void convert(String projectId, String modelId) throws IOException {
        List<InstanceVo> models = MDesignUtil.listChildren(projectId, modelId);

        ModelToSqlFactory.build("DesignToSql")
                .convert2Sql(projectId, models);


//        convert2Sql(ReadFileUtils.getJSONArray(model_path));
    }








}
