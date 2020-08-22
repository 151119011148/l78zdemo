package com.scofen.jdk.io.bio2;


import java.io.IOException;
import java.util.Random;

import static java.lang.Thread.sleep;

/**
 * Create by  GF  in  14:01 2019/1/22
 * Description:
 * Modified  By:
 */
public class IOTest {

    public static void main(String[] args) {

        //运行bio服务器
        //normalServerStart();

        //伪异步 io
        betterServerStart();

        clientStart();
    }


    private static void clientStart() {
        //运行客户端
        char operators[] = {'+','-','*','/'};
        Random random = new Random(System.currentTimeMillis());
        new Thread(() -> {
            while(true){
                //随机产生算术表达式
                String expression = random.nextInt(10) + ""+operators[random.nextInt(4)] + (random.nextInt(10)+1);
                Client.send(expression);
                try {
                    Thread.currentThread().sleep(random.nextInt(1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private static void normalServerStart()  {
        //运行bio服务器
        new Thread(() -> {
            try {
                ServerNormal.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
        //避免客户端先于服务器启动前执行代码
        try {
            sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    private static void betterServerStart()   {
        //运行伪异步 io服务器
        new Thread(() -> {
            try {
                ServerBetter.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

        //避免客户端先于服务器启动前执行代码
        try {
            sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
