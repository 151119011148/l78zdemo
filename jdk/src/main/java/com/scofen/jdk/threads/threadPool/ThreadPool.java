package com.scofen.jdk.threads.threadPool;



public interface ThreadPool<Job extends  Runnable> {
    /**
     * 执行一个job，这个job需要实现runnable
     * @param job
     */
    void execute(Job job);

    /**
     * 关闭线程池
     */
    void shutdown();

    /**
     * 增加工作线程
     * @param number
     */
    void addWorks(int number);

    /**
     * 减少工作线程
     * @param number
     */
    void removeWork(int number);

    /**
     *得到正在等待执行的任务数量
     */
    int getJobSize();


}
