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
 * @since 2020-12-14 14:24
 */
public class SingleLinkedImpl implements Linked {

    int size;

    // sentinel node as pseudo-head
    SingleNode head;

    @Override
    public int get(int index) {
        // if index is invalid
        if (index < 0 || index >= size) {
            return -1;
        }

        SingleNode curr = head;
        // index steps needed
        // to move from sentinel node to wanted index
        for(int i = 0; i < index + 1; ++i) {
            curr = curr.next;
        }
        return curr.val;

    }

    @Override
    public boolean addAtHead(int value) {
        return addAtIndex(0, value);
    }

    @Override
    public boolean addAtTail(int value) {
        return addAtIndex(size, value);
    }

    @Override
    public boolean addAtIndex(int index, int val) {
        // If index is greater than the length,
        // the node will not be inserted.
        if (index > size) {
            return false;
        }

        // [so weird] If index is negative,
        // the node will be inserted at the head of the list.
        if (index < 0) {
            index = 0;
            return false;
        }

        ++ size;
        // find predecessor of the node to be added
        SingleNode pred = head;
        for(int i = 0; i < index; ++ i) {
            pred = pred.next;
        }

        // node to be added
        SingleNode toAdd = new SingleNode(val);
        // insertion itself
        toAdd.next = pred.next;
        pred.next = toAdd;
        return true;
    }

    @Override
    public boolean deleteAtIndex(int index) {
        // if the index is invalid, do nothing
        if (index < 0 || index >= size) {
            return true;
        }

        size --;
        // find predecessor of the node to be deleted
        SingleNode pred = head;
        for(int i = 0; i < index; ++i) {
            pred = pred.next;
        }

        // delete pred.next
        pred.next = pred.next.next;
        return true;
    }

    /**
     * "https://leetcode-cn.com/problems/delete-node-in-a-linked-list/"
     * 237. 删除链表中的节点
     * 请编写一个函数，使其可以删除某个链表中给定的（非末尾）节点。
     * 传入函数的唯一参数为 要被删除的节点 。
     * @param node
     */
    public void deleteNode(SingleNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
//        本该死掉的A偷了B的皮囊继续活在世上
    }




}
