package com.scofen.study.designpattern.proxy.dynamicProxy;

import com.scofen.study.designpattern.proxy.Person;
import lombok.Data;

/**
 * Create by  GF  in  14:54 2018/7/23
 * Description:高富帅 单身
 * Modified  By:
 */
@Data
public class TallRichAndHandsome implements Person {

    private String sex = "男";

    private String name = "张三";

    String girlFriendCondition = "肤白貌美大长腿，研究生，26以下，160cm，50kg";
    String jobCondition = "朝九晚五，双休，年薪50万";
    String houseCondition = "一居室，40平米";

    @Override
    public String findLove() {
        System.out.println("我叫：" + this.name + "。性别：" + this.sex + "。要求是：");
        System.out.println(girlFriendCondition);
        return girlFriendCondition;
    }

    @Override
    public String findJob() {
        System.out.println("我叫：" + this.name + "。性别：" + this.sex + "。要求是：");
        System.out.println(jobCondition);
        return jobCondition;
    }

    @Override
    public String findHouse() {
        System.out.println("我叫：" + this.name + "。性别：" + this.sex + "。要求是：");
        System.out.println(houseCondition);
        return houseCondition;
    }


}
