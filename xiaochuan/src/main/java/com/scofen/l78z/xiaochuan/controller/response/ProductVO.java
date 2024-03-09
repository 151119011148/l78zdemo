package com.scofen.l78z.xiaochuan.controller.response;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import com.scofen.l78z.xiaochuan.dao.dataObject.ProductDO;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Data
public class ProductVO {

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

    private ImgList imgList;

    private ProductDescription productDescription;

    private SupplyCapacity supplyCapacity;

    private String offsetRange;

    private String pcd;

    private String centerBore;

    private String specifications;

    private CategoryVO category;

    private List<CategoryVO> relatedCategory;

    private Date createdTime;

    private Date modifiedTime;


    @Data
    public static class ImgList {

        @JSONField(name = "big_pic")
        private List<String> bigPic;

        @JSONField(name = "small_pic")
        private List<String> smallPic;

        @JSONField(name = "product_photos")
        private List<String> productPhotos;
    }

    @Data
    public static class ProductDescription {
        private ProductDescription.PaymentAndShippingTerms paymentAndShippingTerms;

        @Data
        public static class PaymentAndShippingTerms {

            @JSONField(name = "unit_price")
            private String unitPrice;

            @JSONField(name = "trade_term")
            private String tradeTerm;

            @JSONField(name = "payment_terms")
            private String paymentTerms;

            @JSONField(name = "min_order")
            private String minOrder;

            @JSONField(name = "means_of_transport")
            private String meansOfTransport;
        }
    }

    @Data
    public static class SupplyCapacity {

        @JSONField(name = "production_capacity")
        private String productionCapacity;

        private String packing;

        @JSONField(name = "delivery_date")
        private String deliveryDate;
    }


    public static ProductVO read4(ProductDO param) {
        ProductVO result = new ProductVO();
        result.setProductId(param.getProductId());
        result.setModel(param.getModel());
        result.setTitle(param.getTitle());
        result.setPlaceOfOrigin(param.getPlaceOfOrigin());
        result.setDriveWheel(param.getDriveWheel());
        result.setFinishing(param.getFinishing());
        result.setMaterial(param.getMaterial());
        result.setDiameter(param.getDiameter());
        result.setBrandName(param.getBrandName());
        result.setWidth(param.getWidth());
        result.setCarMake(param.getCarMake());
        result.setAvailableSize(param.getAvailableSize());
        result.setAvailableSizeDetail(param.getAvailableSizeDetail());
        result.setAvailableFinish(param.getAvailableFinish());
        result.setImgList(StringUtils.isEmpty(param.getImgList()) ? null : JSON.parseObject(param.getImgList(), ImgList.class));
        result.setProductDescription(StringUtils.isEmpty(param.getProductDescription()) ? null : JSON.parseObject(param.getProductDescription(), ProductDescription.class));
        result.setSupplyCapacity(StringUtils.isEmpty(param.getSupplyCapacity()) ? null : JSON.parseObject(param.getSupplyCapacity(), SupplyCapacity.class));
        result.setOffsetRange(param.getOffsetRange());
        result.setPcd(param.getPcd());
        result.setCenterBore(param.getCenterBore());
        result.setSpecifications(param.getSpecifications());
        result.setCreatedTime(param.getCreatedTime());
        result.setModifiedTime(param.getModifiedTime());
        return result;
    }

    public static ProductDO convert2(ProductVO param) {
        ProductDO result = new ProductDO();
        result.setProductId(param.getProductId());
        result.setModel(param.getModel());
        result.setTitle(param.getTitle());
        result.setPlaceOfOrigin(param.getPlaceOfOrigin());
        result.setDriveWheel(param.getDriveWheel());
        result.setFinishing(param.getFinishing());
        result.setMaterial(param.getMaterial());
        result.setDiameter(param.getDiameter());
        result.setBrandName(param.getBrandName());
        result.setWidth(param.getWidth());
        result.setCarMake(param.getCarMake());
        result.setAvailableSize(param.getAvailableSize());
        result.setAvailableSizeDetail(param.getAvailableSizeDetail());
        result.setAvailableFinish(param.getAvailableFinish());
        result.setImgList(Objects.isNull(param.getImgList()) ? StringUtils.EMPTY : JSON.toJSONString(param.getImgList()));
        result.setProductDescription(Objects.isNull(param.getProductDescription()) ? StringUtils.EMPTY : JSON.toJSONString(param.getProductDescription()));
        result.setSupplyCapacity(Objects.isNull(param.getSupplyCapacity()) ? StringUtils.EMPTY : JSON.toJSONString(param.getSupplyCapacity()));
        result.setOffsetRange(param.getOffsetRange());
        result.setPcd(param.getPcd());
        result.setCenterBore(param.getCenterBore());
        result.setSpecifications(param.getSpecifications());
        result.setCategoryId(param.getCategory() == null ? StringUtils.EMPTY : param.getCategory().getCategoryId());
        result.setRelatedCategoryId(Objects.isNull(param.getRelatedCategory())
                ? StringUtils.EMPTY
                : JSON.toJSONString(param.getRelatedCategory()
                    .stream()
                    .map(CategoryVO::getCategoryId)
                    .collect(Collectors.toList())));
        result.setCreatedTime(param.getCreatedTime());
        result.setModifiedTime(param.getModifiedTime());
        result.setIsRemoved(0);
        return result;
    }

}
