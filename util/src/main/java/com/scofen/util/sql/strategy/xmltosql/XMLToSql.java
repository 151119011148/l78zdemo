package com.scofen.util.sql.strategy.xmltosql;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.scofen.util.file.WriteFileUtil;
import com.scofen.util.mdesign.MDesignUtil;
import com.scofen.util.mdesign.model.InstanceVo;
import com.scofen.util.sql.strategy.ModelToSql;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.regex.Pattern;

import static com.scofen.util.file.ReadFileUtils.translate_mapping_path;

public class XMLToSql implements ModelToSql {

    public Map<String, String> convert2Sql(String projectId, List<InstanceVo> models, Map<String, Object> translateMap) {
        Map<String, String> sqlMap = new ConcurrentHashMap<>();

        ConcurrentLinkedQueue<InstanceVo> treeModels = new ConcurrentLinkedQueue<>();
        models
                .parallelStream()
                .forEach(model -> collectTreeModel(model, treeModels));

        WriteFileUtil.writeFile(translate_mapping_path, JSON.toJSONString(translateMap).replace(" ", "_"));

        return sqlMap;

    }

    // 定义一个正则表达式来匹配英文字符
    static String englishRegex = "[a-zA-Z]+";

    // 创建 Pattern 对象
    static Pattern pattern = Pattern.compile(englishRegex);
    private static void collectTreeModel(InstanceVo model, ConcurrentLinkedQueue<InstanceVo> needs) {
//        String sourceText = model.getString("name");
//        if ((StringUtils.equals(model.getString("metaclass"), "Class") || StringUtils.equals(model.getString("metaclass"), "uml:Property"))
//                && StringUtils.isNotEmpty(sourceText)
//                && !translateMap.containsKey(sourceText)
//                && StringUtils.isEmpty((String) translateMap.get(sourceText))) {
//            // 创建 matcher 对象
//            Matcher matcher = pattern.matcher(sourceText);
//            if (matcher.find()) {
//                translateMap.put(sourceText, sourceText);
//            } else {
//                String translateText = TranslateUtil.doSingleTranslate(sourceText);
//                translateMap.put(sourceText, translateText.replace(" ", "_"));
//            }
//        }
//        if (StringUtils.equals(model.getRefType(), "uml:Class") && CollectionUtils.isNotEmpty(model.getChildren())){
//            needs.add(model);
//        }

    }
}
