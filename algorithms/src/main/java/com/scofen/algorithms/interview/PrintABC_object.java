package com.scofen.algorithms.interview;

/**
 * @Description: TODO
 * @Author gaofeng
 * @Date 8/1/23 3:51 PM
 **/

public class PrintABC_object {
    private final int PRINT_TIMES = 10;
    private int times = 0;

    public synchronized void printA() throws InterruptedException {
        for (int i = 0; i < PRINT_TIMES; i++) {
            while (times % 3 != 0) {
                wait();
            }
            System.out.print("A");
            times++;
            notifyAll();
        }
    }

    public synchronized void printB() throws InterruptedException {
        for (int i = 0; i < PRINT_TIMES; i++) {
            while (times % 3 != 1) {
                wait();
            }
            System.out.print("B");
            times++;
            notifyAll();
        }
    }

    public synchronized void printC() throws InterruptedException {
        for (int i = 0; i < PRINT_TIMES; i++) {
            while (times % 3 != 2) {
                wait();
            }
            System.out.print("C");
            System.out.print(" ");
            times++;
            notifyAll();
        }
        System.out.println(); // 换行
    }

    public static void main(String[] args) {
        PrintABC_object printABC = new PrintABC_object();

        Thread threadA = new Thread(() -> {
            try {
                printABC.printA();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread threadB = new Thread(() -> {
            try {
                printABC.printB();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread threadC = new Thread(() -> {
            try {
                printABC.printC();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        threadA.start();
        threadB.start();
        threadC.start();
    }
}
