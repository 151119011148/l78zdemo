package com.scofen.study.designpattern.proxy.myselfProxy;



/**
 * Create by  GF  in  14:54 2018/7/23
 * Description:高富帅 单身
 * Modified  By:
 */

public class TallRichAndHandsome implements Person {



    String condition = "肤白貌美大长腿，研究生，26以下，160cm，50kg";

    @Override
    public void findLove() {
        System.out.println("要求是：" + condition);

    }


}
