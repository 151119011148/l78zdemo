/*
 * Copyright (c) 2001-2020 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.scofen.algorithms.leetcode.linked;

/**
 * TODO
 *
 * @author scofen
 * @version V1.0
 * @since 2020-12-14 15:16
 */
public class LinkedNode {

    int val;

    LinkedNode next;

    public LinkedNode() {
    }

    public LinkedNode(int x) {
        val = x;
    }
    public LinkedNode(int x, LinkedNode node) {
        val = x;
        next = node;
    }

}
