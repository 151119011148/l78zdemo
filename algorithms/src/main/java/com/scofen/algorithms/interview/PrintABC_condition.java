package com.scofen.algorithms.interview;

/**
 * @Description: TODO
 * @Author gaofeng
 * @Date 8/1/23 3:51 PM
 **/
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class PrintABC_condition {
    private final int PRINT_TIMES = 10;
    private Lock lock = new ReentrantLock();
    private Condition conditionA = lock.newCondition();
    private Condition conditionB = lock.newCondition();
    private Condition conditionC = lock.newCondition();
    private int state = 1;

    public void printA() throws InterruptedException {
        try {
            lock.lock();
            for (int i = 0; i < PRINT_TIMES; i++) {
                while (state != 1) {
                    conditionA.await();
                }
                System.out.print("A");
                state = 2;
                //signal()和signalAll()均可
                conditionB.signalAll();
            }
        } finally {
            lock.unlock();
        }
    }

    public void printB() throws InterruptedException {
        try {
            lock.lock();
            for (int i = 0; i < PRINT_TIMES; i++) {
                while (state != 2) {
                    conditionB.await();
                }
                System.out.print("B");
                state = 3;
                conditionC.signalAll();
            }
        } finally {
            lock.unlock();
        }
    }

    public void printC() throws InterruptedException {
        try {
            lock.lock();
            for (int i = 0; i < PRINT_TIMES; i++) {
                while (state != 3) {
                    conditionC.await();
                }
                System.out.print("C");
                System.out.print(" ");
                state = 1;
                conditionA.signalAll();
            }
            System.out.println(); // 换行
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        PrintABC_condition printABCCondition = new PrintABC_condition();

        Thread threadA = new Thread(() -> {
            try {
                printABCCondition.printA();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread threadB = new Thread(() -> {
            try {
                printABCCondition.printB();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread threadC = new Thread(() -> {
            try {
                printABCCondition.printC();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        threadA.start();
        threadB.start();
        threadC.start();
    }
}
