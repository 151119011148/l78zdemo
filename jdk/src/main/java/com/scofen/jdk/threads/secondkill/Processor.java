package com.scofen.jdk.threads.secondkill;

import com.sun.deploy.net.HttpRequest;

/**
 * Create by  GF  in  15:34 2019/2/25
 * Description:
 * Modified  By:
 */
public class Processor {
    /**
     * 发送秒杀事务到数据库队列.
     */
    public static void kill(BidInfo info) {
        DB.bids.add(info);
    }

    public static void process() {
        BidInfo info = new BidInfo(RequestQueue.queue.poll());
        if (info != null) {
            kill(info);
        }
    }
}

class BidInfo {
    BidInfo(HttpRequest request) {

// Do something.
    }

}
