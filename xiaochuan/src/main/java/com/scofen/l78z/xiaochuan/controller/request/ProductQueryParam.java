package com.scofen.l78z.xiaochuan.controller.request;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ProductQueryParam implements Serializable {

    /**
     * in_basket，category_id，all
     */
    private String queryKey;

    /**
     * in_basket，category_id
     */
    private String queryValue;

    private Integer pageIndex;

    private Integer pageSize;

}
