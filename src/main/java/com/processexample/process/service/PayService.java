package com.processexample.process.service;

import com.alibaba.fastjson.JSON;
import com.processexample.process.bean.Param;
import com.processexample.process.excutor.ProcessExecutorInterface;
import com.processexample.process.bean.factory.TxnExecutorFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Create by  GF  in  17:15 2018/6/4
 * Description:
 * Modified  By:
 */
@Component
@Slf4j
public class PayService {

    @Resource
    TxnExecutorFactory txnExecutorFactory;


    public String pay() {

        log.info("PayService pay processExecutor.execute is start~");
        ProcessExecutorInterface processExecutorInterface = txnExecutorFactory.getProcessExecutor(null, "1");
        Param result = processExecutorInterface.execute();
        log.info("PayService pay processExecutor.execute is end~");
        return JSON.toJSONString(result);

    }


}
