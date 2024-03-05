package com.scofen.l78z.xiaochuan.response;

import com.scofen.l78z.xiaochuan.util.TreeNode;
import lombok.Data;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class CategoryResponse implements TreeNode<CategoryResponse, String>, Serializable {

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
    private String desc;

    private List<CategoryResponse> children;

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
    public List<CategoryResponse> children() {
        return children;
    }
}
