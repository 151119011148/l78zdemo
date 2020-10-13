package com.scofen.util.bloomfilter;

import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

import java.util.List;
import java.util.Set;
import java.util.UUID;


/**
 * Create by  GF  in  15:48 2019/1/9
 * Description:
 * Modified  By:
 */
public class GuavaBloomFilter {

    private static int insertions = 1000000;

    public static void main(String[] args) {
        bloomFilterTest();
    }
    private static void bloomFilterTest(){

        //默认百分之三的误判率，5个hash函数，7298440个bit位数组约等于7M
        //初始化一个存储String的布隆过滤器，存放100W数据
        BloomFilter<String> bloomFilter = BloomFilter.create(Funnels.stringFunnel(Charsets.UTF_8), insertions);
        Set<String> set = Sets.newHashSet();
        List<String> list = Lists.newArrayList();

        for (int i = 0; i< insertions; i ++){
            String uuid = UUID.randomUUID().toString();
            bloomFilter.put(uuid);
            set.add(uuid);
            list.add(uuid);
        }
        double right = 0;
        double wrong = 0D;
        for (int i = 0; i < 10000; i ++){
            String test = i % 100 == 0 ? list.get(i / 100) : UUID.randomUUID().toString();
            if(bloomFilter.mightContain(test)){
                if (set.contains(test)){
                    right ++;
                }else {
                    wrong ++;
                }
            }
        }
        System.out.println("正确率:" + right*100/100 + "%");
        System.out.println("误判率:" + wrong*100/10000+ "%");
    }




}
