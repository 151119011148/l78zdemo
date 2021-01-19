/*
 * Copyright (c) 2001-2021 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.scofen.flowlimit.interfac;

/**
 * 流量控制接口
 *
 * @author scofen
 * @version V1.0
 * @since 2021-01-08 10:25
 */

public interface FlowLimiter {


    /**
     *
     * @return
     */
    Boolean execute(String key, Integer limit);




}



