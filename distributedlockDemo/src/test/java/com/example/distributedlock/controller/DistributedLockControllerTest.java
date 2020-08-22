package com.example.distributedlock.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DistributedLockControllerTest {

    @Autowired
    private DistributedLockController distributedLockController;

    @Test
    public void mysqlLock() throws Exception {
        System.out.println(distributedLockController.mysqlLock());
    }

    @Test
    public void redisLock() throws Exception {
        System.out.println(distributedLockController.redisLock());
    }

    @Test
    public void zookeeperLock() throws Exception {
        System.out.println(distributedLockController.zookeeperLock());
    }
}