package com.scofen.l78z.xiaochuan.dao.dataObject;


import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.AbstractAuditable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.TemporalType.TIMESTAMP;


/**
 * @Description: TODO
 * @Author gaofeng
 * @Date 2023/9/3 8:40 PM
 **/
@Data
@Entity
@Table(name = "category")
@EntityListeners(AuditingEntityListener.class)
public class CategoryDO {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "category_id")
    private String categoryId;

    @Column(name = "level")
    private Integer level;

    @Column(name = "parent_id")
    private String parentId;

    @Column(name = "name")
    private String name;

    @Column(name = "category_desc")
    private String categoryDesc;

    @CreatedDate
    @Column(name = "gmt_create", nullable = false, updatable = false)
    @Temporal(TIMESTAMP)
    private Date createdTime;

    @LastModifiedDate
    @Column(name = "gmt_modified", nullable = false)
    @Temporal(TIMESTAMP)
    private Date modifiedTime;

    @Column(name = "is_removed")
    private Integer isRemoved;

    public void update(CategoryDO update){
        if (StringUtils.isNotBlank(update.getCategoryDesc())){
            this.categoryDesc = update.getCategoryDesc();
        }
        if (StringUtils.isNotBlank(update.getName())){
            this.name = update.getName();
        }
        if (StringUtils.isNotBlank(update.getCategoryDesc())){
            this.categoryDesc = update.getCategoryDesc();
        }
    }

}
