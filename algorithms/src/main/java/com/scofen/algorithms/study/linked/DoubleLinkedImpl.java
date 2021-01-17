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
public class DoubleLinkedImpl implements Linked {

    int size;
    // sentinel nodes as pseudo-head and pseudo-tail
    DoubleNode head, tail;

    public DoubleLinkedImpl() {
        size = 0;
        head = new DoubleNode(0);
        tail = new DoubleNode(0);
        head.next = tail;
        tail.prev = head;
    }

    /**
     * Get the value of the index-th node in the linked list. If the index is invalid, return -1.
     */
    @Override
    public int get(int index) {
        // if index is invalid
        if (index < 0 || index >= size) {
            return -1;
        }

        // choose the fastest way: to move from the head
        // or to move from the tail
        DoubleNode curr = head;
        if (index + 1 < size - index) {
            for (int i = 0; i < index + 1; ++i) {
                curr = curr.next;
            }
        } else {
            curr = tail;
            for (int i = 0; i < size - index; ++i) {
                curr = curr.prev;
            }
        }
        return curr.val;
    }

    /**
     * Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list.
     */
    @Override
    public boolean addAtHead(int val) {
        DoubleNode headNode = head, next = head.next;

        ++size;
        DoubleNode toAdd = new DoubleNode(val);
        toAdd.prev = headNode;
        toAdd.next = next;
        headNode.next = toAdd;
        next.prev = toAdd;
        return true;
    }

    /**
     * Append a node of value val to the last element of the linked list.
     */
    @Override
    public boolean addAtTail(int val) {
        DoubleNode tailNode = tail, pred = tail.prev;

        ++size;
        DoubleNode toAdd = new DoubleNode(val);
        toAdd.prev = pred;
        toAdd.next = tailNode;
        pred.next = toAdd;
        tailNode.prev = toAdd;
        return true;
    }

    /**
     * Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted.
     */
    @Override
    public boolean addAtIndex(int index, int val) {
        // If index is greater than the length,
        // the node will not be inserted.
        if (index > size) {
            return true;
        }

        // [so weird] If index is negative,
        // the node will be inserted at the head of the list.
        if (index < 0) {
            index = 0;
        }

        // find predecessor and successor of the node to be added
        DoubleNode pred, succ;
        if (index < size - index) {
            pred = head;
            for (int i = 0; i < index; ++i) {
                pred = pred.next;
            }
            succ = pred.next;
        } else {
            succ = tail;
            for (int i = 0; i < size - index; ++i) {
                succ = succ.prev;
            }
            pred = succ.prev;
        }

        // insertion itself
        ++size;
        DoubleNode toAdd = new DoubleNode(val);
        toAdd.prev = pred;
        toAdd.next = succ;
        pred.next = toAdd;
        succ.prev = toAdd;
        return true;
    }

    /**
     * Delete the index-th node in the linked list, if the index is valid.
     */
    @Override
    public boolean deleteAtIndex(int index) {
        // if the index is invalid, do nothing
        if (index < 0 || index >= size) {
            return true;
        }

        // find predecessor and successor of the node to be deleted
        DoubleNode pred, succ;
        if (index < size - index) {
            pred = head;
            for (int i = 0; i < index; ++i) {
                pred = pred.next;
            }
            succ = pred.next.next;
        } else {
            succ = tail;
            for (int i = 0; i < size - index - 1; ++i) {
                succ = succ.prev;
            }
            pred = succ.prev.prev;
        }

        // delete pred.next
        --size;
        pred.next = succ;
        succ.prev = pred;
        return true;
    }

}
