package com.scofen.util.mdesign;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.scofen.util.mdesign.model.InstanceVo;
import com.scofen.util.translation.youdao.utils.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpUriRequest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
public class MDesignUtil {

    public static final int RESPONSE_SUCCESS = 1000;
    public static final String RESPONSE_CODE = "code";
    public static final String RESPONSE_MESSAGE = "message";
    public static final String RESPONSE_DATA = "data";
    public static final String NODE_URL = "http://localhost:8237/";
    public static final String MODEL_URL = "http://localhost:8238/";



    public static List<InstanceVo> listChildren(String projectId, String modelId) {
        Map<String, List<String>> params = new HashMap<String, List<String>>() {{
            put("projectId", Lists.newArrayList(projectId));
            put("modelId", Lists.newArrayList(modelId));
        }};
        byte[] result = HttpUtil.doGet("http://localhost:8237/node/children", null, params, "application/json");
        // 打印返回结果
        if (result != null) {
            JSONObject jsonObject = JSON.parseObject(new String(result, StandardCharsets.UTF_8));
            if (jsonObject.containsKey("data")) {
                return JSON.parseArray(JSON.toJSONString(jsonObject.getJSONArray("data")
                        .stream()
                        .peek(instance -> ((JSONObject) instance).remove("id_"))
                        .filter(instance -> Objects.equals(((JSONObject) instance).getInteger("from"), 3))
                        .collect(Collectors.toList())), InstanceVo.class);
            }
            throw new RuntimeException(jsonObject.getString("message"));
        }
        throw new RuntimeException("listChildren failed!");
    }


    /**
     * 查询应用了某构造型及其子类型构造型的所有模型id
     */
    public static List<InstanceVo> listSpecificInstanceByClassId(String projectId, List<String> classfierIds) {
        String svrUrl = MODEL_URL + "model/listSpecificInstanceByClassId";
        RestTemplate restTemplate = fetchRestTemplate();
        Map<String, Object> param = new HashMap<>();
        param.put("projectId", projectId);
        param.put("classfierIds", classfierIds);
        Map<String, Object> retVal = getEntityToServer(restTemplate, svrUrl, JSON.toJSONString(param));
        if (!Objects.equals(retVal.get(RESPONSE_CODE), RESPONSE_SUCCESS)) {
            log.info("listSpecificInstanceByClassId is failed! param is " + JSON.toJSONString(param)
                    + ", listSpecificInstanceByClassIdResult is " + JSON.toJSONString(retVal));
        }
        List<InstanceVo> result = new LinkedList<>();
        JSON.parseArray(JSON.toJSONString(retVal.get("data")))
                .forEach(item -> result.add(JSON.parseObject(JSON.toJSONString(item), InstanceVo.class)));
        return result;

    }


    public static Map<String, Object> getEntityToServer(RestTemplate restTemplate, String svrUrl, String jStr) {
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        HttpEntity<String> formEntity = new HttpEntity<>(jStr, headers);
        return restTemplate.exchange(svrUrl, HttpMethod.GET, formEntity, HashMap.class).getBody();
    }

    /***
     * @Description: 初始化Rest方法
     * @param
     * @return: org.springframework.web.client.RestTemplate
     * @throws:
     */
    public static RestTemplate fetchRestTemplate() {
        HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientRestfulHttpRequestFactory();
        httpRequestFactory.setConnectionRequestTimeout(3600000);
        httpRequestFactory.setConnectTimeout(3600000);
        httpRequestFactory.setReadTimeout(3600000);
        return new RestTemplate(httpRequestFactory);
    }

    public static class HttpComponentsClientRestfulHttpRequestFactory extends HttpComponentsClientHttpRequestFactory {
        @Override
        protected HttpUriRequest createHttpUriRequest(HttpMethod httpMethod, URI uri) {

            if (httpMethod == HttpMethod.GET) {
                return new HttpGetRequestWithEntity(uri);
            }
            return super.createHttpUriRequest(httpMethod, uri);
        }

        /**
         * 定义HttpGetRequestWithEntity实现HttpEntityEnclosingRequestBase抽象类，以支持GET请求携带body数据
         */
        static final class HttpGetRequestWithEntity extends HttpEntityEnclosingRequestBase {
            public HttpGetRequestWithEntity(final URI uri) {
                super.setURI(uri);
            }

            @Override
            public String getMethod() {
                return HttpMethod.GET.name();

            }
        }
    }

}
