package com.scofen.l78z.xiaochuan.controller.response;

import com.scofen.l78z.xiaochuan.util.TreeNode;
import com.scofen.l78z.xiaochuan.util.TreeUtils;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class CategoryVO implements TreeNode<CategoryVO, String>, Serializable {

    /**
     *
     */
    private String parentId;

    /**
     *
     */
    private Integer level;

    /**
     *
     */
    private String categoryId;

    /**
     *
     */
    private String name;

    /**
     *
     */
    private String categoryDesc;

    private List<CategoryVO> children;

    private Date createdTime;

    private Date modifiedTime;

    @Override
    public String id() {
        return categoryId;
    }

    @Override
    public String parentId() {
        return parentId;
    }

    @Override
    public List<CategoryVO> children() {
        return children;
    }

    @Override
    public void children(List<CategoryVO> children) {
        this.children = children;
    }
}
