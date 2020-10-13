package com.scofen.distributedlock.service.zookeeper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ZookeeperLockServiceImplWithCuratorTest {

    @Resource
    private ZookeeperLockServiceImplWithCurator ZookeeperLockServiceImplWithCurator;
    @Resource
    private ZookeeperLockServiceImpl ZookeeperLockServiceImpl;

    @Test
    public  void testValue() {
        System.out.println(ZookeeperLockServiceImplWithCurator.ipAndPort);
        System.out.println(ZookeeperLockServiceImpl.ipAndPort);
    }
}