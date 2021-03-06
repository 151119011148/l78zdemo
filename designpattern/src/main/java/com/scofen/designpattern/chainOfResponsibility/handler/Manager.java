package com.scofen.designpattern.chainOfResponsibility.handler;

/**
 * Create by  GF  in  15:47 2018/11/21
 * Description:销售经理，可以批准5%以内的折扣
 * Modified  By:
 */
public class Manager extends PriceHandler{
    @Override
    public void processDiscount(float discount) {
        if(discount <= 0.3){
            System.out.format("%s批准了折扣，%.2f%n", this.getClass().getName(), discount);
        }else{
            System.out.format("%s无法批准折扣，%.2f%n", this.getClass().getName(), discount);
            System.out.format("交由%s批准", this.getClass().getSuperclass().getName());
            System.out.println();
            successor.processDiscount(discount);
        }
    }
}
