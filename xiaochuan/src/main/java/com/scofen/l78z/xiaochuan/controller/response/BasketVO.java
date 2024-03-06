package com.scofen.l78z.xiaochuan.controller.response;

import lombok.Data;

import java.util.List;

@Data
public class BasketVO {

    private String visitId;

    private List<ProductVO> products;

}
