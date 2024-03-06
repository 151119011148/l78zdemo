package com.scofen.l78z.xiaochuan.controller.response;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.TemporalType.TIMESTAMP;

@Data
public class ProductVO {

    private int id;

    private String productId;

    private String model;

    private String title;

    private String placeOfOrigin;

    private String driveWheel;

    private String finishing;

    private String material;

    private String diameter;

    private String brandName;

    private String width;

    private String carMake;

    private String availableSize;

    private String availableSizeDetail;

    private String availableFinish;

    private String imgList;

    private String productDescription;

    private String supplyCapacity;

    private String offsetRange;

    private String pcd;

    private String centerBore;

    private String specifications;

    private String categoryId;

    private String relatedCategoryId;

    private Date createdTime;

    private Date modifiedTime;

    private Integer isRemoved;
}
