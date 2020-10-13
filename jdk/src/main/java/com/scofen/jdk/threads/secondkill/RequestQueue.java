package com.scofen.jdk.threads.secondkill;

import com.sun.deploy.net.HttpRequest;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Create by  GF  in  15:36 2019/2/25
 * Description:
 * Modified  By:
 */
public class RequestQueue {
    /**
     * Java的并发包提供了三个常用的并发队列实现，分别是：ConcurrentLinkedQueue 、 LinkedBlockingQueue 和 ArrayBlockingQueue。
     * <p>
     * ArrayBlockingQueue是初始容量固定的阻塞队列，我们可以用来作为数据库模块成功竞拍的队列，比如有10个商品，那么我们就设定一个10大小的数组队列。
     * <p>
     * ConcurrentLinkedQueue使用的是CAS原语无锁队列实现，是一个异步队列，入队的速度很快，出队进行了加锁，性能稍慢。
     * <p>
     * LinkedBlockingQueue也是阻塞的队列，入队和出队都用了加锁，当队空的时候线程会暂时阻塞。
     * <p>
     * 由于我们的系统入队需求要远大于出队需求，一般不会出现队空的情况，所以我们可以选择ConcurrentLinkedQueue来作为我们的请求队列实现：
     */
    public static ConcurrentLinkedQueue<HttpRequest> queue = new ConcurrentLinkedQueue<>();

}
