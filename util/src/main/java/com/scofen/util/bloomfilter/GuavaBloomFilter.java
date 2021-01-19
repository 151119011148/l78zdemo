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
 * Google 著名的分布式数据库 Bigtable 使用了布隆过滤器来查找不存在的行或列，以减少磁盘查找的IO次数［3］。
 *
 * Squid 网页代理缓存服务器在 cache digests 中使用了也布隆过滤器［4］。
 *
 * Venti 文档存储系统也采用布隆过滤器来检测先前存储的数据［5］。
 *
 * SPIN 模型检测器也使用布隆过滤器在大规模验证问题时跟踪可达状态空间［6］。
 *
 * Google Chrome浏览器使用了布隆过滤器加速安全浏览服务［7］。
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
        double containRight = 0D;
        double containWrong = 0D;
        double notContainRight = 0D;
        double notContainWrong = 0D;
        for (int i = 0; i < 10000; i ++){
            //生成100个旧的，已经存在于过滤器里的数据和9900新的数据
            String test = i % 100 == 0 ? list.get(i / 100) : UUID.randomUUID().toString();
            //过滤器也许包含
            if(bloomFilter.mightContain(test)){
                //真实的数据
                if (set.contains(test)){
                    containRight ++;
                }else {
                    containWrong ++;
                }
                //过滤器不包含
            }else {
                if (set.contains(test)){
                    notContainWrong ++;
                    System.out.println("过滤器不包含，但是真实包含！" );
                }else {
                    notContainRight ++;
                }
            }

        }
        System.out.println("过滤器认为包含，实际也包含的个数:" + containRight);
        System.out.println("过滤器认为包含，但实际不包含的个数:" + containWrong);
        System.out.println("误判率:" + containWrong*100/10000+ "%");

        System.out.println("过滤器认为不包含，但实际包含的个数:" + notContainWrong);
        System.out.println("过滤器认为不包含，但实际也不包含的个数:" + notContainRight);
    }




}
