package com.processexample.process.controller;

import com.processexample.process.annotation.Explain;
import com.processexample.process.manager.PayManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.logging.LogLevel;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Create by  GF  in  16:47 2018/6/4
 * Description:
 * Modified  By:
 */

@RestController
public class CommonPayController {

    @Autowired
    private PayManager payManager;

    @PostMapping(value ="CommonPay")
    @Explain(name= "支付接口", logLv = LogLevel.INFO)
    public String CommonPay(){
        return payManager.pay();
    }






}
