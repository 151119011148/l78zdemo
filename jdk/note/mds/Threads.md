进程

　　进程是可并发执行的程序在某个数据集合上的一次计算活动，也是OS进行资源分配和运行调度的基本单位。

　　运行状态的程序以进程的形态存储在内存中。

　　指一个执行单元，在PC、mobile中指一个程序或者一个应用。一个进程可以包含多个线程。


线程

　　CPU调度的最小单元，是一种有限的系统资源。

    i>线程是进程中的一个相对可独立运行的单元
    ii>线程是操作系统中的基本调度单位，在线程中包含调度所需要的基本信息。
    iii>在具备线程机制的操作系统中，进程不再是调度单位，一个进程中至少包含一个线程，以线程作为调度单位。
    iv>线程自己并不拥有资源，它与同进程中的其他线程共享该进程所拥有的资源。由于线程之间涉及资源共享，所以需要同步机制来实现进程内多线程之间的通信。
    v>与进程类似，线程还可以创建其他线程，线程也有声明周期，也有状态的变化。


    
创建线程4种方式

    * 1继承Thread类（本质上实现的runnable接口）
    * 2实现runnable接口
    * 3实现callable接口 带返回值的
    * 4线程池取
    
    
线程的状态

    新建状态(New)
    
    当用new操作符创建一个线程后， 例如new Thread(r)，此时线程处在新建状态。 当一个线程处于新建状态时，线程中的任务代码还没开始运行。
    
    就绪状态(Runnable)
    
    也被称为“可执行状态”。一个新创建的线程并不自动开始运行，要执行线程，必须调用线程的start()方法。当调用了线程对象的start()方法即启动了线程，此时线程就处于就绪状态。
    
    处于就绪状态的线程并不一定立即运行run()方法，线程还必须同其他就绪线程竞争CPU，只有获得CPU使用权才可以运行线程。比如在单核心CPU的计算机系统中，不可能同时运行多个线程，一个时刻只能有一个线程处于运行状态。对与多个处于就绪状态的线程是由Java运行时系统的线程调度程序(thread scheduler)来调度执行。
    
    除了调用start()方法后让线程变成就绪状态，一个线程阻塞状态结束后也可以变成就绪状态，或者从运行状态变化到就绪状态。
    
    运行状态(Running)
    
    线程获取到CPU使用权进行执行。需要注意的是，线程只能从就绪状态进入到运行状态。真正开始执行run()方法的内容。
    
    阻塞状态(Blocked)
    
    线程在获取锁失败时(因为锁被其它线程抢占)，它会被加入锁的同步阻塞队列，然后线程进入阻塞状态(Blocked)。处于阻塞状态(Blocked)的线程放弃CPU使用权，暂时停止运行。待其它线程释放锁之后，阻塞状态(Blocked)的线程将在次参与锁的竞争，如果竞争锁成功，线程将进入就绪状态(Runnable) 。
    
    等待状态(WAITING)
    
    或者叫条件等待状态，当线程的运行条件不满足时，通过锁的条件等待机制(调用锁对象的wait()或显示锁条件对象的await()方法)让线程进入等待状态(WAITING)。处于等待状态的线程将不会被cpu执行，除非线程的运行条件得到满足后，其可被其他线程唤醒，进入阻塞状态(Blocked)。调用不带超时的Thread.join()方法也会进入等待状态。
    
    限时等待状态(TIMED_WAITING)
    
    限时等待是等待状态的一种特例，线程在等待时我们将设定等待超时时间，如超过了我们设定的等待时间，等待线程将自动唤醒进入阻塞状态(Blocked)或就绪状态(Runnable) 。在调用Thread.sleep()方法，带有超时设定的Object.wait()方法，带有超时设定的Thread.join()方法等，线程会进入限时等待状态(TIMED_WAITING)。
    
    死亡状态(TERMINATED)
    
    线程执行完了或者因异常退出了run()方法，该线程结束生命周期。


sleep() 与 wait()的区别

    i>这两个方法来自不同的类分别是，sleep来自Thread类，和wait来自Object类；
    ii>sleep方法没有释放锁，而wait方法释放了锁，使得其他线程可以使用同步控制块或者方法。
    sleep不出让系统资源；wait是进入线程等待池等待，出让系统资源，其他线程可以占用CPU。一般wait不会加时间限制， 因为如果wait线程的运行资源不够，再出来也没用，要等待其他线程调用notify/notifyAll唤醒等待池中的所有线程，才会进入就绪队列等待OS分配系统资源。sleep(milliseconds)可以用时间指定使它自动唤醒过来，如果时间不到只能调用interrupt()强行打断。
    iii>wait，notify和notifyAll只能在同步控制方法或者同步控制块里面使用，而
    sleep可以在任何地方使用
    iv>Sleep需要捕获异常,而wait不需要
    
线程中断与复位

　　中断不同于终止,终止是将处于阻塞状态的线程终止,清理资源.通常中断的线程不在执行状态,
而是处于 sleep(),wait()等状态,线程中断方式 Thread.interrupt().
　　线程停止:不管线程是否在执行,强制停止,已有的过时方法 Thread.stop(),线程停止的方法目前最好
的方式是用标记位.

　　Thread类包含interrupt()方法，因此你可以终止被阻塞的任务，这个方法将设置线程的中断状态，
如果一个线程已经被阻塞，或者试图执行一个阻塞操作，那么设置这个线程的中断状态将抛出
InterrupedException，当抛出该异常或者该任务调用Thread.interrupt()时，中断状态将被复位。

　　线程Thread.stop方法的隐患:调用Thread.stop()方法是不安全的，这是因为当调用Thread.stop()方法时，会发生下面两件事：
1. 即刻抛出ThreadDeath异常，在线程的run()方法内，任何一点都有可能抛出ThreadDeath Error，包括在catch或finally语句中。
2. 会释放该线程所持有的所有的锁，而这种释放是不可控制的，非预期的会产生的不可控
现象一:当如果在给线程run方法中加同步锁, stop方法无法停止这个线程,原因是stop方法是同步的, 所以这两个方法持有同一个锁
现象二:当调用thread.stop()时，被停止的线程会不会释放其所持有的锁


    
    

Synchronized 和 Lock 的区别

　　Lock 是一个接口，而 synchronized 是 Java 中的关键字，synchronized 是内置的语言实现；
　　synchronized 在发生异常时，会自动释放线程占有的锁，因此不会导致死锁现象发生；
而 Lock 在发生异常时，如果没有主动通过 unLock() 去释放锁，则很可能造成死锁现象，因此使用
 Lock 时需要在 finally 块中释放锁；
　　Lock 可以让等待锁的线程响应中断，而 synchronized 却不行，使用 synchronized 时，等待的线程
会一直等待下去，不能够响应中断；
　　通过 Lock 可以知道有没有成功获取锁，而 synchronized 却无法办到。
　　Lock 可以提高多个线程进行读操作的效率。


更深的：

　　与 synchronized 相比，ReentrantLock 提供了更多，更加全面的功能，具备更强的扩展性。例如：时间锁等候，
可中断锁等候，锁投票。
　　ReentrantLock 还提供了条件 Condition ，对线程的等待、唤醒操作更加详细和灵活，所以在多个条件变量
和高度竞争锁的地方，ReentrantLock 更加适合（以后会阐述 Condition）。
　　ReentrantLock 提供了可轮询的锁请求。它会尝试着去获取锁，如果成功则继续，否则可以等到下次运行时
处理，而 synchronized则一旦进入锁请求要么成功要么阻塞，所以相比synchronized 而言，ReentrantLock 会不容易产生
死锁些。
　　ReentrantLock 支持更加灵活的同步代码块，但是使用 synchronized时，只能在同一个synchronized块结构中获取
和释放。注意，ReentrantLock 的锁释放一定要在finally 中处理，否则可能会产生严重的后果。
　　ReentrantLock 支持中断处理，且性能较 synchronized 会好些。



Java 中线程同步的方式

    sychronized 同步方法或代码块
    volatile
    Lock
    ThreadLocal
    阻塞队列（LinkedBlockingQueue）
    使用原子变量（java.util.concurrent.atomic）
    变量的不可变性


CAS 是一种什么样的同步机制？

　　Compare And Swap，比较交换。可以看到 synchronized 可以保证代码块原子性，很多时候会引起性能问题，
volatile也是个不错的选择，但是volatile 不能保证原子性，只能在某些场合下使用。所以可以通过 CAS 来进行
同步，保证原子性。

　　我们在读 Concurrent 包下的类的源码时，发现无论是 ReentrantLock 内部的 AQS，还是各种 Atomic 开头的原子类，
内部都应用到了 CAS。

　　在 CAS 中有三个参数：内存值 V、旧的预期值 A、要更新的值 B ，当且仅当内存值 V 的值等于旧的预期值 A
 时，才会将内存值 V 的值修改为 B，否则什么都不干。其伪代码如下：
 ```java
    if (this.value == A) {
      this.value = B
      return true;
    } else {
      return false;
    }
```
　　CAS 可以保证一次的读-改-写操作是原子操作


ConcurrentHashMap 的实现方式？

　　ConcurrentHashMap 的实现方式和 Hashtable 不同，不采用独占锁的形式，更高效，其中在 jdk1.7 和 jdk1.8 中实现的
方式也略有不同。

　　Jdk1.7 中采用分段锁和 HashEntry 使锁更加细化。ConcurrentHashMap 采用了分段锁技术，其中 Segment 继承于 
ReentrantLock。不会像 HashTable 那样不管是 put 还是 get 操作都需要做同步处理，理论上 ConcurrentHashMap 支持 
CurrencyLevel (Segment 数组数量）的线程并发。

　　Jdk1.8 利用 CAS+Synchronized 来保证并发更新的安全，当然底层采用数组+链表+红黑树的存储结构。

    table 中存放 Node 节点数据，默认 Node 数据大小为 16，扩容大小总是 2^N。
    
    为了保证可见性，Node 节点中的 val 和 next 节点都用 volatile 修饰。
    
    当链表长度大于 8 时，会转换成红黑树，节点会被包装成 TreeNode放在TreeBin 中。
    
    put()：1. 计算键所对应的 hash 值；2. 如果哈希表还未初始化，调用 initTable() 初始化，否则在 table 中找到 index 位置，
    并通过 CAS 添加节点。如果链表节点数目超过 8，则将链表转换为红黑树。如果节点总数超过，则进行扩容操作。
   
    get()：无需加锁，直接根据 key 的 hash 值遍历 node。
    
    
    
CountDownLatch 和 CyclicBarrier 的区别？ 并发工具类

　　CyclicBarrier 它允许一组线程互相等待，直到到达某个公共屏障点 (Common Barrier Point)。在涉及一组固定大小的线程的程序中，这些线程必须不时地互相等待，此时 CyclicBarrier 很有用。因为该 Barrier 在释放等待线程后可以重用，所以称它为循环 ( Cyclic ) 的 屏障 ( Barrier ) 。

　　每个线程调用 #await() 方法，告诉 CyclicBarrier 我已经到达了屏障，然后当前线程被阻塞。当所有线程都到达了屏障，结束阻塞，所有线程可继续执行后续逻辑。

　　CountDownLatch 能够使一个线程在等待另外一些线程完成各自工作之后，再继续执行。使用一个计数器进行实现。计数器初始值为线程的数量。当每一个线程完成自己任务后，计数器的值就会减一。当计数器的值为 0 时，表示所有的线程都已经完成了任务，然后在 CountDownLatch 上等待的线程就可以恢复执行任务。

两者区别：

    CountDownLatch 的作用是允许 1 或 N 个线程等待其他线程完成执行；而 CyclicBarrier 则是允许 N 个线程相互等待。
    
    CountDownLatch 的计数器无法被重置；CyclicBarrier 的计数器可以被重置后使用，因此它被称为是循环的 barrier 。
    
　　Semaphore 是一个控制访问多个共享资源的计数器，和 CountDownLatch 一样，其本质上是一个“共享锁”。
一个计数信号量。从概念上讲，信号量维护了一个许可集。

    如有必要，在许可可用前会阻塞每一个 acquire，然后再获取该许可。
    每个 release 添加一个许可，从而可能释放一个正在阻塞的获取者。
    
    
    
怎么控制线程，尽可能减少上下文切换？

　　减少上下文切换的方法有无锁并发编程、CAS算法、使用最少线程和使用协程。

    无锁并发编程。多线程竞争锁时，会引起上下文切换，所以多线程处理数据时，可以使用一些方法
    来避免使用锁。如将数据的ID按照Hash算法取模分段，不同的线程处理不同段的数据。
    
    CAS算法。Java的Atomic包使用CAS算法来更新数据，而不需要加锁。
    
    使用最少线程。避免创建不需要的线程，比如任务很少，但是创建了很多线程来处理，这样会造成大量
    线程都处于等待状态。
    
    协程。在单线程里实现多任务的调度，并在单线程里维持多个任务间的切换。
    
    
    
阻塞队列

　　阻塞队列实现了 BlockingQueue 接口，并且有多组处理方法。

    抛出异常：add(e) 、remove()、element()
    返回特殊值：offer(e) 、pool()、peek()
    阻塞：put(e) 、take()
    
　　JDK 8 中提供了七个阻塞队列可供使用：

    ArrayBlockingQueue ：一个由数组结构组成的有界阻塞队列。
    LinkedBlockingQueue ：一个由链表结构组成的无界阻塞队列。
    PriorityBlockingQueue ：一个支持优先级排序的无界阻塞队列。
    DelayQueue：一个使用优先级队列实现的无界阻塞队列。
    SynchronousQueue：一个不存储元素的阻塞队列。
    LinkedTransferQueue：一个由链表结构组成的无界阻塞队列。
    LinkedBlockingDeque：一个由链表结构组成的双向阻塞队列。

　　ArrayBlockingQueue，一个由数组实现的有界阻塞队列。该队列采用 FIFO 的原则对元素进行排序添加的。
内部使用可重入锁 ReentrantLock + Condition 来完成多线程环境的并发操作。