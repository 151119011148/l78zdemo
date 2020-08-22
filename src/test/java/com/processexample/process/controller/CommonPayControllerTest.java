package com.processexample.process.controller;

import com.processexample.process.manager.PayManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class CommonPayControllerTest {

    @Autowired
    private PayManager payManager;

    @Test
    public void commonPay() throws Exception {
        String result = payManager.pay();
        System.out.println("统一支付结果：" + result);

    }

}