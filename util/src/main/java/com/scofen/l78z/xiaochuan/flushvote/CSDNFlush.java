package com.scofen.l78z.xiaochuan.flushvote;

import com.google.common.collect.Lists;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * Create by  GF  in  10:55 2019/10/20
 * Description:
 * Modified  By:
 */
public class CSDNFlush {
    private static List<String> ids = Lists.newArrayList(
            "102495849",//
            "79289214",//给定目录，抽取目录下所有文件中所有中文，花了半天时间编写调试。好东西哦
            "85124162",//缓存穿透、缓存击穿、缓存雪崩概念及解决方案
            "85161084",//jdk集合源码阅读（一）：HashMap分析（基于JDK1.8）
            "85161028",//jdk集合源码阅读（二）：ConcurrentHashMap分析（基于JDK1.8）
            "86288757",//Java多线程（四）：synchronized锁机制
            "86289326",//Java多线程（五）：ReentrantLock源码分析（基于JDK1.8）
            "86302124",//性能分析（二）：公平锁与非公平锁（基于JDK1.8）
            "88170597",//TCP三次握手四次挥手详解
            "88171376"//热点账户高并发解决方案
            ,"102973487"//OOM（四）：201911080845再一次溢出
            ,"102930098"//OOM（一）：线上排查及解决流程
    );
    public static void main(String[] args) {
        String prefix = "https://blog.csdn.net/XiaoHanZuoFengZhou/article/details/";

        for(int i = 0; i < 60; i ++){
            ids.forEach(id ->{
                String url = prefix + id;
                send(url);
            });
            try {
                Thread.sleep(60 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(new Date());
        }



    }

    private static void send(String url) {
        OkHttpClient okHttpClient = new OkHttpClient();
        final Request request = new Request.Builder()
                .url(url)
                .header("Cookie",
                "uuid_tt_dd=10_20862851220-1560997589568-687048; dc_session_id=10_1560997589568.534171; Hm_ct_6bcd52f51e9b3dce32bec4a3997715ac=5744*1*XiaoHanZuoFengZhou!6525*1*10_20862851220-1560997589568-687048!1788*1*PC_VC; smidV2=201905131446122eb798123898c3cb491df33706ea78c5001c309d8f348c200; UN=XiaoHanZuoFengZhou; UserName=XiaoHanZuoFengZhou; UserInfo=ad126f736f044e4b9cb722042852e0fd; UserToken=ad126f736f044e4b9cb722042852e0fd; UserNick=%E6%99%93%E5%AF%92%E9%A3%8E%E9%AA%A4; AU=679; BT=1571387563750; p_uid=U000000; __gads=Test; acw_tc=2760824815727492798056845ea02c6228709b9a10ebb4ef1fdc2b1174d85f; Hm_lvt_6bcd52f51e9b3dce32bec4a3997715ac=1573308513,1573309000,1573350279,1573352312; acw_sc__v2=5dcd509195da924d91fd3a94f5d0c4963e11a879; acw_sc__v3=5dcd5092a4643ec75da9ea1c51f366d6cab89119; Hm_lpvt_6bcd52f51e9b3dce32bec4a3997715ac=1573736583; announcement=%257B%2522isLogin%2522%253Atrue%252C%2522announcementUrl%2522%253A%2522https%253A%252F%252Fblogdev.blog.csdn.net%252Farticle%252Fdetails%252F103053996%2522%252C%2522announcementCount%2522%253A1%252C%2522announcementExpire%2522%253A238456681%257D; dc_tos=q0ymxa"
                ).build();
        final Call call = okHttpClient.newCall(request);
        Response response = null;
        try {
            response = call.execute();
            System.out.print( "result: " + response.message() + ";");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
