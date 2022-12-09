package com.scofen.designpattern.proxy.myselfProxy;


import com.scofen.designpattern.proxy.Person;

/**
 * Create by  GF  in  14:54 2018/7/23
 * Description:高富帅 单身
 * Modified  By:
 */

public class TallRichAndHandsome implements Person {



    String condition = "肤白貌美大长腿，研究生，26以下，160cm，50kg";

    @Override
    public String findLove() {
        System.out.println("要求是：" + condition);
        return null;
    }

    @Override
    public String getSex() {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public String findJob() {
        return null;
    }

    @Override
    public String findHouse() {
        return null;
    }


}
