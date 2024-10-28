package com.scofen.util.mdesign.model;


import lombok.Data;

@Data
//(description = "约束额外属性")
public class ConstraintAttr extends ElementAttr {

    //(title = "约束模型Id")
    private String constraintId;

    //(title = "约束的规范定义")
    private String specificationName;
}
