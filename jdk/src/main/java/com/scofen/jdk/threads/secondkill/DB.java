package com.scofen.jdk.threads.secondkill;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * Create by  GF  in  15:38 2019/2/25
 * Description:
 * 数据库模块数据库主要是使用一个ArrayBlockingQueue来暂存有可能成功的用户请求。
 * Modified  By:
 */
public class DB {
    static ArrayBlockingQueue<BidInfo> bids = new ArrayBlockingQueue<>(10);
    private static int count = 10;

    public static boolean checkReminds() {

        // TODO
        return true;
    }

    // 单线程操作
    public static void bid() {
        BidInfo info = bids.poll();
        while (count-- > 0) {

            // insert into table Bids values(item_id, user_id, bid_date, other)

            // select count(id) from Bids where item_id = ?

            // 如果数据库商品数量大于总数，则标志秒杀已完成，设置标志位reminds = false.
            info = bids.poll();
        }
    }

}
