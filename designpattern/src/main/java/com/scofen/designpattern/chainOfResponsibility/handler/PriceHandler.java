package com.scofen.designpattern.chainOfResponsibility.handler;

import lombok.Setter;

/**
 * Create by  GF  in  15:43 2018/11/21
 * Description:价格处理人，负责处理客户折扣申请
 * Modified  By:
 */

public abstract class PriceHandler {
    /**
     * 直接后继，便于传递请求
     */
    @Setter
    protected PriceHandler successor;

    /**
     * 处理折扣申请
     */
    public abstract void processDiscount(float discount);


}
