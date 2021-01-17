/*
 * Copyright (c) 2001-2020 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.scofen.algorithms.study.linked;

/**
 * TODO
 *
 * @author weiyi
 * @version V1.0
 * @since 2020-12-15 17:47
 */
public class DoubleNode {

    int val;

    DoubleNode prev;

    DoubleNode next;

    public DoubleNode(int value){
        this.val = value;
    }

    public DoubleNode(DoubleNode val) {
    }
}
