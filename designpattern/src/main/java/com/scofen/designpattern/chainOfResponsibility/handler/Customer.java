package com.scofen.designpattern.chainOfResponsibility.handler;

import lombok.Setter;
import org.junit.Test;

/**
 * Create by  GF  in  16:44 2018/11/21
 * Description:客户，请求折扣
 * Modified  By:
 */
public class Customer {

    @Setter
    private PriceHandler priceHandler;

    public void requestDiscount(final float discount){
        priceHandler.processDiscount(discount);
    }

    @Test
    public void testChainOfResponsibility(){
        PriceHandler handler = PriceHandlerFactory.createPriceHandler();
        Customer customer = new Customer();
        customer.setPriceHandler(handler);
        //customer.requestDiscount(0.05F);
        //customer.requestDiscount(0.06F);
        //customer.requestDiscount(0.31F);
        //customer.requestDiscount(0.41F);
        customer.requestDiscount(0.51F);

    }

}
