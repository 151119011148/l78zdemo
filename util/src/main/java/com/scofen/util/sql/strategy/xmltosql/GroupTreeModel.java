package com.scofen.util.sql.strategy.xmltosql;

import com.scofen.util.tree.TreeNode;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
public class GroupTreeModel implements TreeNode<GroupTreeModel, String>, Serializable {

    private String key;
    private String text;
    private boolean groupFlag;
    private String category;
    private String group;
    private String refType;
    private String imageName;
    /**
     * 子节点
     */
    private List<GroupTreeModel> children;

    public GroupTreeModel(String key, String text, boolean isGroup, String category, String group, String refType) {
        this.key = key;
        this.text = text;
        this.groupFlag = isGroup;
        this.category = category;
        this.group = group;
        this.refType = refType;
        setImageNameByRefType(refType);
    }

    private void setImageNameByRefType(String refType) {
        if (refType.contains("uml:")) {
            imageName = refType.split(":")[1];
        } else if (refType.equals("RelationShipGroup")) {
            imageName = "Package";
        } else {
            if (!StringUtils.isEmpty(refType)) {
                imageName = refType;
            } else {
                imageName = "Instance";
            }
        }
    }

    @Override
    public String id() {
        return key;
    }

    @Override
    public String parentId() {
        return group;
    }

    @Override
    public List<GroupTreeModel> children() {
        return children;
    }

    @Override
    public void children(List<GroupTreeModel> children) {
        this.children = children;
    }

    public void setChildren(List<GroupTreeModel> children) {
        this.children = children;
    }
}

