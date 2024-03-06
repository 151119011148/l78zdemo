package com.scofen.l78z.xiaochuan.controller.request;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ProductParam implements Serializable {

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

    private String categoryId;

    private List<String> relatedCategoryId;

    @Data
    public static class ImgList {
        private List<String> bigPic;
        private List<String> smallPic;
        private List<String> productPhotos;
    }

    @Data
    public static class ProductDescription {
        private PaymentAndShippingTerms paymentAndShippingTerms;

        @Data
        public static class PaymentAndShippingTerms {
            private String unitPrice;
            private String tradeTerm;
            private String paymentTerms;
            private String minOrder;
            private String meansOfTransport;
        }
    }
    @Data
    public static class SupplyCapacity {
        private String productionCapacity;
        private String packing;
        private String deliveryDate;
    }



}
