package com.processexample.process.manager;

import com.processexample.process.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Create by  GF  in  16:58 2018/6/4
 * Description:
 * Modified  By:
 */
@Component
public class PayManager {

    @Autowired
    private PayService payService;

    public String pay() {

        return payService.pay();


    }

}
