package com.scofen.jdk.threads.connectionPool;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Create by  GF  in  11:11 2019/2/15
 * Description:测试线程池的性能
 *随着客户端线程的增加，客户端出现超时无法获取的比例也在增加
 * 这种机制能保证客户端不会出现一直获取连接的操作，而是按时返回
 * 告诉客户端连接出现的问题，是系统的一种保护机制
 * 针对昂贵资源的获取都应该设置超时等待机制
 * Modified  By:
 */
public class ConnectionPoolTest {

    static ConnectionPool pool = new ConnectionPool(10);

    static CountDownLatch start = new CountDownLatch(1);

    static CountDownLatch end;

    public static void main(String[] args) throws Exception{

        int threadCount = 30;//10,20,30,40,50
        end = new CountDownLatch(threadCount);
        int count = 20;
        AtomicInteger got = new AtomicInteger(0);
        AtomicInteger notGot = new AtomicInteger(0);
        for (int i = 0; i < threadCount; i ++){
            Thread thread = new Thread(new ConnectionRunner(count, got, notGot), "ConnectionRunnerThread");
            thread.start();

        }
        start.countDown();
        end.await();
        System.out.println("total invoke: " + threadCount * count);
        System.out.println("got connection: " + got);
        System.out.println("not got connection: " + notGot);
        System.out.println("not got connection rate: " + (double)notGot.intValue()/(threadCount * count) * 100 + "%");

    }

    @Data
    @AllArgsConstructor
    static class ConnectionRunner implements Runnable{

        int count;
        AtomicInteger got;
        AtomicInteger notGot;

        @Override
        public void run() {
            try {
                start.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            while (count > 0){
                try {
                    //从线程池中获取连接，如果1000ms无法获取到，返回null
                    //分别统计连接获取的数量got和为获取的数量notGot
                    Connection connection = pool.fetchConnection(1000);
                    if (connection != null){
                        try {
                            connection.createStatement();
                            connection.commit();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }finally {
                            pool.releaseConnection(connection);
                            got.incrementAndGet();
                        }
                    }else {
                        notGot.incrementAndGet();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    count --;
                }
            }
            end.countDown();
        }

    }


}
