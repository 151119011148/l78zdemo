package com.scofen.util.mdesign.model;

import lombok.Data;
import java.util.List;
import java.util.Set;

@Data
public class InstanceVo {
    /**
     * 模型名称
     */
    //(title = "模型名称")
    private String name;

    /**
     * 模型id
     */
    //(title = "模型id")
    private String id;

    /**
     * 模型的类型（元模型）
     */
    //(title = "模型的类型（元模型）")
    private String metaClass;

    /**
     * 应用的构造型
     */
    //(title = "应用的构造型")
    private List<String> appliedStereotypes;

    //(title = "父元素id")
    private String ownerId;

    //(title = "是否为关系模型")
    private Boolean isRelationShip = false;

    //(title = "图标地址")
    private String icon;

    //(title = "模型类型中文名")
    private String modelTypeCnName;

    //(title = "模型类型英文名称")
    private String modelTypeEnName;

    //(title = "泛化的基础stereotype类型")
    private String baseSt;

    //(title = "模型创建来源")
    private Integer from = 3;

    private String prefix;

    //(title = "依赖的模型id")
    private Set<String> dependentIds;

    //(title = "名称显示需要的属性")
    private ShowAttribute<?> showAttribute;

    //(title = "此模型是不是显示而来的。显示：即通过显示端口/部件而显示出来的模型")
    private Boolean isDisplay = false;

    private boolean hasChild;

    List<InstanceVo> children;

}
