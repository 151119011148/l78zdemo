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
 * @author scofen
 * @version V1.0
 * @since 2020-12-14 14:19
 */
public interface Linked {

    /**
     * 获取链表中第 index 个节点的值。如果索引无效，则返回-1。
     * @param index
     */
    int get(int index);

    /**
     * 在链表的第一个元素之前添加一个值为 val 的节点。插入后，新节点将成为链表的第一个节点。
     * @param val
     */
    boolean addAtHead(int val);

    /**
     * 将值为 val 的节点追加到链表的最后一个元素。
     * @param val
     */
    boolean addAtTail(int val);

    /**
     * 在链表中的第 index 个节点之前添加值为 val  的节点。如果 index 等于链表的长度，
     * 则该节点将附加到链表的末尾。如果 index 大于链表长度，则不会插入节点。
     * 如果index小于0，则在头部插入节点。
     * @param index
     * @param val
     */
    boolean addAtIndex(int index, int val);

    /**
     * 如果索引 index 有效，则删除链表中的第 index 个节点。
     * @param index
     */
    boolean deleteAtIndex(int index);

}
