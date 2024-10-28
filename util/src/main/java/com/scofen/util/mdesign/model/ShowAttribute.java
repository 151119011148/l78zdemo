package com.scofen.util.mdesign.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ShowAttribute<T> {

    //(title = "模型默认名称")
    private String displayName;

    //(title = "关系的source端模型名称")
    private String sourceName;

    //(title = "关系的target端模型名称")
    private String targetName;

    //(title = "关系的source端id")
    private String sourceId;

    //(title = "关系的target端id")
    private String targetId;

    //(title = "关系是否存在方向")
    private Boolean isDirection;

    //(title = "应用的stereotype的信息")
    private List<ApplyStereotypeVo> stereotypes = new ArrayList<>();

    private T extraAttribute;

    //(title = "图片的地址")
    private String location;

    //(title = "模型关键字")
    private List<String> keywords;

    //(title = "元素应用的约束")
    private List<ConstraintAttr> constraints;

}
