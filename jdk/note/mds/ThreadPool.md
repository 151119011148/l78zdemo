线程池

　　线程池有五种状态：RUNNING, SHUTDOWN, STOP, TIDYING, TERMINATED。

    RUNNING：接收并处理任务。
    SHUTDOWN：不接收但处理现有任务。
    STOP：不接收也不处理任务，同时终端当前处理的任务。
    TIDYING：所有任务终止，线程池会变为 TIDYING 状态。当线程池变为 TIDYING 状态时，会执行钩子函数 terminated()。
    TERMINATED：线程池彻底终止的状态。

　　内部变量** ctl **定义为 AtomicInteger ，记录了“线程池中的任务数量”和“线程池的状态”两个信息。共 32 位，其中高 3 位表示”线程池状态”，低 29 位表示”线程池中的任务数量”。


线程池创建参数

　　*corePoolSize

　　线程池中核心线程的数量。当提交一个任务时，线程池会新建一个线程来执行任务，直到当前线程数
等于 corePoolSize。如果调用了线程池的 prestartAllCoreThreads() 方法，线程池会提前创建并启动所有基本线程。

　　*maximumPoolSize

　　线程池中允许的最大线程数。线程池的阻塞队列满了之后，如果还有任务提交，如果当前的线程数
小于 maximumPoolSize，则会新建线程来执行任务。注意，如果使用的是无界队列，该参数也就没有什么效果了。

　　*keepAliveTime

　　线程空闲的时间。线程的创建和销毁是需要代价的。线程执行完任务后不会立即销毁，而是继续存活
一段时间：keepAliveTime。默认情况下，该参数只有在线程数大于 corePoolSize 时才会生效。

　　*unit

　　keepAliveTime 的单位。TimeUnit

　　*workQueue

　　用来保存等待执行的任务的阻塞队列，等待的任务必须实现 Runnable 接口。我们可以选择如下几种：

    ArrayBlockingQueue：基于数组结构的有界阻塞队列，FIFO。
    LinkedBlockingQueue：基于链表结构的有界阻塞队列，FIFO。
    SynchronousQueue：不存储元素的阻塞队列，每个插入操作都必须等待一个移出操作，反之亦然。
    PriorityBlockingQueue：具有优先界别的阻塞队列。

　　*threadFactory

　　用于设置创建线程的工厂。该对象可以通过 Executors.defaultThreadFactory()。他是通过 newThread() 方法提供创建
线程的功能，newThread() 方法创建的线程都是“非守护线程”而且“线程优先级都是 Thread.NORM_PRIORITY”。

　　*handler

　　RejectedExecutionHandler，线程池的拒绝策略。所谓拒绝策略，是指将任务添加到线程池中时，线程池拒绝
该任务所采取的相应策略。当向线程池中提交任务时，如果此时线程池中的线程已经饱和了，而且阻塞队列
也已经满了，则线程池会选择一种拒绝策略来处理该任务。

　　线程池提供了四种拒绝策略：

    AbortPolicy：直接抛出异常，默认策略；
    CallerRunsPolicy：用调用者所在的线程来执行任务；
    DiscardOldestPolicy：丢弃阻塞队列中靠最前的任务，并执行当前任务；
    DiscardPolicy：直接丢弃任务；
    
　　当然我们也可以实现自己的拒绝策略，例如记录日志等等，实现 RejectedExecutionHandler 接口即可。

　　当添加新的任务到线程池时：

    线程数量未达到 corePoolSize，则新建一个线程（核心线程）执行任务
    线程数量达到了 corePoolSize，则将任务移入队列等待
    队列已满，新建线程（非核心线程）执行任务
    队列已满，总线程数又达到了 maximumPoolSize，就会由 handler 的拒绝策略来处理
    
    
　　线程池可通过 Executor 框架来进行创建：
　　FixedThreadPool:
```java
public static ExecutorService newFixedThreadPool(int nThreads) {
   return new ThreadPoolExecutor(nThreads, nThreads,
                                 0L, TimeUnit.MILLISECONDS,
                                 new LinkedBlockingQueue<Runnable>());
}
```

　　corePoolSize 和 maximumPoolSize 都设置为创建 FixedThreadPool 时指定的参数 nThreads，意味着当线程池满时且阻塞队列
也已经满时，如果继续提交任务，则会直接走拒绝策略，该线程池不会再新建线程来执行任务，而是直接走
拒绝策略。FixedThreadPool 使用的是默认的拒绝策略，即 AbortPolicy，则直接抛出异常。

　　但是 workQueue 使用了无界的 LinkedBlockingQueue, 那么当任务数量超过 corePoolSize 后，全都会添加到队列中而不
执行拒绝策略。

　　SingleThreadExecutor:
```java
public static ExecutorService newSingleThreadExecutor() {
   return new FinalizableDelegatedExecutorService
       (new ThreadPoolExecutor(1, 1,
                               0L, TimeUnit.MILLISECONDS,
                               new LinkedBlockingQueue<Runnable>()));
}
```

　　作为单一 worker 线程的线程池，SingleThreadExecutor 把 corePool 和 maximumPoolSize 均被设置为 1，和 FixedThreadPool 一样
使用的是无界队列 LinkedBlockingQueue, 所以带来的影响和 FixedThreadPool 一样

　　CachedThreadPool
　　CachedThreadPool是一个会根据需要创建新线程的线程池 ，他定义如下：
```java
public static ExecutorService newCachedThreadPool() {
   return new ThreadPoolExecutor(0, Integer.MAX_VALUE,
                                 60L, TimeUnit.SECONDS,
                                 new SynchronousQueue<Runnable>());
}
```
　　这个线程池，当任务提交是就会创建线程去执行,执行完成后线程会空闲60s,之后就会销毁。但是如果主线程
提交任务的速度远远大于 CachedThreadPool 的处理速度，则 CachedThreadPool 会不断地创建新线程来执行任务，这样
有可能会导致系统耗尽 CPU 和内存资源，所以在使用该线程池是，一定要注意控制并发的任务数，否则创建
大量的线程可能导致严重的性能问题。


　　为什么要使用线程池？

　　创建/销毁线程伴随着系统开销，过于频繁的创建/销毁线程，会很大程度上影响处理效率。线程池缓存线程，
可用已有的闲置线程来执行新任务(keepAliveTime)   
　　线程并发数量过多，抢占系统资源从而导致阻塞。运用线程池能有效的控制线程最大并发数，避免以上的问题。   
　　对线程进行一些简单的管理(延时执行、定时循环执行的策略等)


　　生产者消费者问题

　　实例代码用 Object 的 wait()和notify() 实现，也可用 ReentrantLock 和 Condition 来完成。或者直接使用阻塞队列。

```java
public class ProducerConsumer {
    public static void main(String[] args) {
        ProducerConsumer main = new ProducerConsumer();
        Queue<Integer> buffer = new LinkedList<>();
        int maxSize = 5;
        new Thread(main.new Producer(buffer, maxSize), "Producer1").start();
        new Thread(main.new Consumer(buffer, maxSize), "Comsumer1").start();
        new Thread(main.new Consumer(buffer, maxSize), "Comsumer2").start();
    }

    class Producer implements Runnable {
        private Queue<Integer> queue;
        private int maxSize;

        Producer(Queue<Integer> queue, int maxSize) {
            this.queue = queue;
            this.maxSize = maxSize;
        }

        @Override
        public void run() {
            while (true) {
                synchronized (queue) {
                    while (queue.size() == maxSize) {
                        try {
                            System.out.println("Queue is full");
                            queue.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Random random = new Random();
                    int i = random.nextInt();
                    System.out.println(Thread.currentThread().getName() + " Producing value : " + i);
                    queue.add(i);
                    queue.notifyAll();
                }
            }
        }
    }

    class Consumer implements Runnable {
        private Queue<Integer> queue;
        private int maxSize;

        public Consumer(Queue<Integer> queue, int maxSize) {
            super();
            this.queue = queue;
            this.maxSize = maxSize;
        }

        @Override
        public void run() {
            while (true) {
                synchronized (queue) {
                    while (queue.isEmpty()) {
                        try {
                            System.out.println("Queue is empty");
                            queue.wait();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + " Consuming value : " + queue.remove());
                    queue.notifyAll();
                }
            }
        }
    }
}
```
    
    