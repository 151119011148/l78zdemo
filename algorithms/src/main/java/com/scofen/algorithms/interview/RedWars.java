package com.scofen.algorithms.interview;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;


/**
 * Create by  GF  in  9:23 2019/3/5
 * Description:
 * Modified  By:
 */
public class RedWars {



    @Test
    public void testRedWar(){
        System.out.println(getRedWar(1, 100));
        System.out.println(getRedWar(3, 100));
        System.out.println(getRedWar(10, 100));
        System.out.println(getRedWar(100, 100));
        System.out.println(getRedWar(3, 50));
    }

    /**
     * 剩余红包金额M，剩余人数N，那么：每次抢到金额=随机(0，M/N*2)
     保证了每次随机金额的平均值是公平的
     假设10人，红包金额100元
     第一人：100/10*2=20，随机范围(0,20)，平均可以抢到10元
     第二人：90/9*2=20，随机范围(0,20)，平均可以抢到10元
     第三人：80/8*2=20，随机范围(0,20)，平均可以抢到10元
     以此类推，每次随机范围的均值是相等的
     缺点：除了最后一次，任何一次抢到的金额都不会超过人均金额的两倍，并不是任意的随机
     * @param users
     * @param total
     * @return
     */
     private Map<String, String> getRedWar(int users, int total){
        Map<String, String> result = new HashMap<>();
        while (users > 1) {
            int money = (total / users) * 2;
            //保证随机数最小为1
            int tmp = new Random().nextInt(money - 1) + 1;
            result.put(String.valueOf(users), String.valueOf(tmp));
            //更新当前余额
            total -= tmp;
            users--;
        }
         //最后一个人抽中剩余全部金额
         result.put("1", String.valueOf(total));
        return  result;
    }





}
