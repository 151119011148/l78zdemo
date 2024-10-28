package com.scofen.l78z.xiaochuan.dao.dataObject;


import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
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
@Table(name = "basket")
@EntityListeners(AuditingEntityListener.class)
public class BasketDO {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "visit_id")
    private String visitId;

    @Column(name = "product_ids")
    private String productIds;

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

}
