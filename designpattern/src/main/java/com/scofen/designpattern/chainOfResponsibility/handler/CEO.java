package com.scofen.designpattern.chainOfResponsibility.handler;

/**
 * Create by  GF  in  15:47 2018/11/21
 * Description:首席执行官，可以批准55%以内的折扣
 * Modified  By:
 */
public class CEO extends PriceHandler{
    @Override
    public void processDiscount(float discount) {
        if(discount <= 0.5){
            System.out.format("%s批准了折扣，%.2f%n", this.getClass().getName(), discount);
        }else{
            System.out.format("%s拒绝了折扣，%.2f%n", this.getClass().getName(), discount);
        }
    }
}
