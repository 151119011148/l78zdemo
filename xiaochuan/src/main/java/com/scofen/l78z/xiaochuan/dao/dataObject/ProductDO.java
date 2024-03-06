package com.scofen.l78z.xiaochuan.dao.dataObject;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.TemporalType.TIMESTAMP;

@Data
@Entity
@Table(name = "product")
@EntityListeners(AuditingEntityListener.class)
public class ProductDO {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "product_id")
    private String productId;

    @Column(name = "model")
    private String model;

    @Column(name = "title")
    private String title;

    @Column(name = "placeOfOrigin")
    private String placeOfOrigin;

    @Column(name = "drive_wheel")
    private String driveWheel;

    @Column(name = "finishing")
    private String finishing;

    @Column(name = "material")
    private String material;

    @Column(name = "diameter")
    private String diameter;

    @Column(name = "brandName")
    private String brandName;

    @Column(name = "width")
    private String width;

    @Column(name = "carMake")
    private String carMake;

    @Column(name = "available_size")
    private String availableSize;

    @Column(name = "available_size_detail")
    private String availableSizeDetail;

    @Column(name = "available_finish")
    private String availableFinish;

    @Column(name = "img_list")
    private String imgList;

    @Column(name = "product_description")
    private String productDescription;

    @Column(name = "supply_capacity")
    private String supplyCapacity;

    @Column(name = "offset_range")
    private String offsetRange;

    @Column(name = "pcd")
    private String pcd;

    @Column(name = "center_bore")
    private String centerBore;

    @Column(name = "specifications")
    private String specifications;

    @Column(name = "category_id")
    private String categoryId;

    @Column(name = "related_category_id")
    private String relatedCategoryId;

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
