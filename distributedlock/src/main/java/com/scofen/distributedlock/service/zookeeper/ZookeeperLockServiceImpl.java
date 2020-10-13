package com.scofen.distributedlock.service.zookeeper;

/**
 * Create by  GF  in  15:07 2019/2/13
 * Description:
 * Modified  By:
 */

import com.alibaba.fastjson.JSON;
import com.scofen.distributedlock.service.AbstractDistributedLockService;
import com.google.common.base.Joiner;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.concurrent.ThreadSafe;
import java.util.List;
import java.util.TreeSet;
import java.util.concurrent.CountDownLatch;


/**
 * Create by  GF  in  21:58 2019/1/27
 * Description:
 * Modified  By:
 */
@ThreadSafe
@Slf4j
@Service("zookeeperLockService")
public class ZookeeperLockServiceImpl extends AbstractDistributedLockService{

    @Value("${zookeeper.IP_PORT}")
    public  String ipAndPort;

    public final static Joiner j = Joiner.on("|").useForNull("");

    //zk客户端
    private static ZooKeeper zk;
    //zk是一个目录结构，root为最外层目录
    private static String root = "/root";

    //当前线程创建的序列node
    private ThreadLocal<String> nodeId = new ThreadLocal<>();
    //用来同步等待zkclient链接到了服务端
    private static CountDownLatch connectedSignal = new CountDownLatch(1);
    private final static int sessionTimeout = 3000;
    private final static byte[] data = new byte[0];

    @PostConstruct
    private void init(){
        try {
            zk = new ZooKeeper(ipAndPort, sessionTimeout, event -> {
                // 建立连接
                if (event.getState() == Watcher.Event.KeeperState.SyncConnected) {
                    connectedSignal.countDown();
                }
            });

            connectedSignal.await();
            Stat stat = zk.exists(root, false);
            if (null == stat) {
                // 创建根节点
                zk.create(root, data, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


    @Override
    public boolean tryLock(String key) {

        try {

            // 创建临时子节点
            String myNode = zk.create(root +  key , data, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);

            // 取出所有子节点
            List<String> subNodes = zk.getChildren(root  , false);
            TreeSet<String> sortedNodes = new TreeSet<>();
            subNodes.forEach(node -> sortedNodes.add(root +  key +"/" +node));

            String smallNode = sortedNodes.first();
            String preNode = sortedNodes.lower(myNode);

            if (myNode.equals(smallNode)) {
                // 如果是最小的节点,则表示取得锁
                //log.info(j.join(Thread.currentThread().getName(), myNode, "get lock"));
                this.nodeId.set(myNode);
                return true;
            }

            CountDownLatch latch = new CountDownLatch(1);
            Stat stat = zk.exists(preNode, new LockWatcher(latch));// 同时注册监听。
            // 判断比自己小一个数的节点是否存在,如果不存在则无需等待锁,同时注册监听
            if (stat != null) {
                /*log.info(j.join(Thread.currentThread().getName(), myNode, "waiting for " + root + "/" + preNode + " released lock"));*/

                latch.await();// 等待，这里应该一直等待其他线程释放锁
                nodeId.set(myNode);
                latch = null;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return false;
    }
    private boolean tryLock(){
        return tryLock(lockKeyByZookeeper);
    }
    @Override
    public boolean releaseLock(String key) {
        try {
            /*log.info(j.join(Thread.currentThread().getName(), nodeId.get(), "unlock "));*/
            if (null != nodeId) {
                zk.delete(nodeId.get(), -1);
                nodeId.remove();
                return true;
            }
        } catch (InterruptedException | KeeperException e) {
            e.printStackTrace();
        }
        return false;
    }



    @AllArgsConstructor
    class LockWatcher implements Watcher {

        private CountDownLatch latch;

        @Override
        public void process(WatchedEvent event) {
            if (event.getType() == Event.EventType.NodeDeleted)
                latch.countDown();
        }

    }

    public static void main(String[] args) throws Exception{
        zk = new ZooKeeper("localhost:2181", sessionTimeout, event -> {
            // 建立连接
            if (event.getState() == Watcher.Event.KeeperState.SyncConnected) {
                connectedSignal.countDown();
            }
        });

        connectedSignal.await();
        List<String> children = zk.getChildren("/", false);
        System.out.println(children);
        if(children.contains(root)){
            zk.delete(root, -1);
            System.out.println(JSON.toJSONString(zk.getChildren("/", false)));
        }

    }
}
