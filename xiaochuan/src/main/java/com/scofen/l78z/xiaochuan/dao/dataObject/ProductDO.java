package com.scofen.l78z.xiaochuan.dao.dataObject;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;
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
    private Integer id;

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

    public void update(ProductDO update) {
        if (StringUtils.isNotBlank(update.model)) {
            this.model = update.model;
        }
        if (StringUtils.isNotBlank(update.title)) {
            this.title = update.title;
        }
        if (StringUtils.isNotBlank(update.placeOfOrigin)) {
            this.placeOfOrigin = update.placeOfOrigin;
        }
        if (StringUtils.isNotBlank(update.driveWheel)) {
            this.driveWheel = update.driveWheel;
        }
        if (StringUtils.isNotBlank(update.finishing)) {
            this.finishing = update.finishing;
        }
        if (StringUtils.isNotBlank(update.material)) {
            this.material = update.material;
        }
        if (StringUtils.isNotBlank(update.diameter)) {
            this.diameter = update.diameter;
        }
        if (StringUtils.isNotBlank(update.brandName)) {
            this.brandName = update.brandName;
        }
        if (StringUtils.isNotBlank(update.width)) {
            this.width = update.width;
        }
        if (StringUtils.isNotBlank(update.carMake)) {
            this.carMake = update.carMake;
        }
        if (StringUtils.isNotBlank(update.availableSize)) {
            this.availableSize = update.availableSize;
        }
        if (StringUtils.isNotBlank(update.availableSizeDetail)) {
            this.availableSizeDetail = update.availableSizeDetail;
        }
        if (StringUtils.isNotBlank(update.availableFinish)) {
            this.availableFinish = update.availableFinish;
        }
        if (StringUtils.isNotBlank(update.imgList)) {
            this.imgList = update.imgList;
        }
        if (StringUtils.isNotBlank(update.productDescription)) {
            this.productDescription = update.productDescription;
        }
        if (StringUtils.isNotBlank(update.supplyCapacity)) {
            this.supplyCapacity = update.supplyCapacity;
        }
        if (StringUtils.isNotBlank(update.offsetRange)) {
            this.offsetRange = update.offsetRange;
        }
        if (StringUtils.isNotBlank(update.pcd)) {
            this.pcd = update.pcd;
        }
        if (StringUtils.isNotBlank(update.centerBore)) {
            this.centerBore = update.centerBore;
        }
        if (StringUtils.isNotBlank(update.specifications)) {
            this.specifications = update.specifications;
        }
        if (StringUtils.isNotBlank(update.categoryId)) {
            this.categoryId = update.categoryId;
        }
        if (StringUtils.isNotBlank(update.relatedCategoryId)) {
            this.relatedCategoryId = update.relatedCategoryId;
        }

    }
}
