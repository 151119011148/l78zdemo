package com.scofen.thread.secondkill;

import com.sun.deploy.net.HttpRequest;


/**
 * Create by  GF  in  15:29 2019/2/25
 * Description:每一个HTTP请求都要经过该预处理.
 * Modified  By:
 */
public class PreProcessor {



    // 商品是否还有剩余
    private static boolean reminds = true;
    private static void forbidden() {

        // Do something.
    }

    public static boolean checkReminds() {
        if (reminds) {

            // 远程检测是否还有剩余，该RPC接口应由数据库服务器提供，不必完全严格检查.
/*            if (!RPC.checkReminds()) {
                reminds = false;
            }*/
        }
        return reminds;
    }

    public static void preProcess(HttpRequest request) {
        if (checkReminds()) {

            // 一个并发的队列
            RequestQueue.queue.add(request);
        } else {

            // 如果已经没有商品了，则直接驳回请求即可.
            forbidden();
        }
    }


}
