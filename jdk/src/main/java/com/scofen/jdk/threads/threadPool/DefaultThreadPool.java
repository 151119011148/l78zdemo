package com.scofen.jdk.threads.threadPool;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Create by  GF  in  15:18 2019/2/15
 * Description:
 * 线程池的本质就是使用了一个线程安全的工作队列连接工作者线程和客户端线程
 * 客户端将任务放入工作队列后返回，而工作线程则不断地从工作队列中去处工作并执行
 * 工作队列为空时，工作者线程均等待在工作队列上，
 * 随着大量的任务被提交，更多的工作者线程会被唤醒
 * Modified  By:
 */
public class DefaultThreadPool<Job extends Runnable> implements ThreadPool<Job> {

    private static final int MAX_WORKER_NUMBERS = 10;

    private static final int DEFAULT_WORKER_NUMBERS = 5;

    private static final int MIN_WORKER_NUMBERS = 1;

    //工作队列
    private final LinkedList<Job> jobs = Lists.newLinkedList();

    //工作者线程
    private final List<Worker> workers = Collections.synchronizedList(new ArrayList<Worker>());

    private   int workNumber = DEFAULT_WORKER_NUMBERS;

    private AtomicLong threadNumber = new AtomicLong(0);

    public DefaultThreadPool(){
        initializeWorkers(DEFAULT_WORKER_NUMBERS);
    }
    public DefaultThreadPool(int number){
        workNumber = number > MAX_WORKER_NUMBERS  ?
                MAX_WORKER_NUMBERS :
                number < MIN_WORKER_NUMBERS ? MIN_WORKER_NUMBERS : number;
        initializeWorkers(DEFAULT_WORKER_NUMBERS);
    }

    private void initializeWorkers(int defaultWorkerNumbers) {
        for (int i = 0; i< defaultWorkerNumbers; i ++){
            Worker worker = new Worker();
            workers.add(worker);
            Thread thread = new Thread(worker, "ThreadPool-Worker-" + threadNumber.incrementAndGet());
            thread.start();
        }
    }

    @Override
    public void execute(Job job) {
        if (job != null){
            //添加一个工作，然后进行通知
            synchronized (jobs){
                jobs.addLast(job);
                //为什么不用notifyAll？
                /**
                 * 只要确保有工作者线程被唤醒就OK，开销更少，避免将等待队列中的线程全部移动到同步队列中
                 增加线程竞争
                 */
                jobs.notify();
            }
        }
    }

    @Override
    public void shutdown() {
        for (Worker worker : workers){
            worker.shutdown();
        }
    }

    @Override
    public void addWorks(int number) {
        synchronized (jobs){
            if (number + this.workNumber > MAX_WORKER_NUMBERS){
                number = MAX_WORKER_NUMBERS - this.workNumber;
            }
            initializeWorkers(number);
            this.workNumber += number;
        }
    }

    @Override
    public void removeWork(int number) {
        synchronized (jobs){
            if (number >= this.workNumber){
                throw new IllegalArgumentException("beyond workNumber");
            }

            int count = 0;
            while (count < number){
                Worker worker = workers.get(count);
                if (workers.remove(worker)){
                    worker.shutdown();
                    count ++;
                }
            }
            this.workNumber  -= count;

        }
    }

    @Override
    public int getJobSize() {
        return jobs.size();
    }


    class Worker implements Runnable{

        private volatile boolean running = true;

        public void shutdown(){
            running = false;
        }
        @Override
        public void run() {

            while (running){
                Job job = null;
                synchronized (jobs){
                    while (jobs.isEmpty()){
                        try {
                            jobs.wait();
                        } catch (InterruptedException e) {
                            //感知到外部对工作线程强制中断操作，返回
                            Thread.currentThread().interrupt();
                            return;
                        }
                        job = jobs.removeFirst();
                    }
                    if (job != null){
                        job.run();
                    }
                }
            }

        }

    }
}
