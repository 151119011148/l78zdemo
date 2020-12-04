/*
 * Copyright (c) 2001-2020 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.scofen.algorithms.study.binarytree;


/**
 * 基于（二分查找）有序数组的符号表
 * @author scofen
 * @version V1.0
 * @since 2020-12-03 11:24
 */
public class BinarySearchST<Key extends Comparable, Value> {

    private Key[] keys;

    private Value[] values;

    private int count;

    public BinarySearchST(int capacity){
        keys = (Key[]) new Comparable[capacity];
        values = (Value[]) new Object[capacity];
    }

    public boolean isEmpty(){
        return values == null || keys[0] == null;
    }

    public int size(){
        return count;
    }

    public Value get(Key key){
        if(isEmpty()){
            return null;
        }
        int i = rank(key);
        if (i < count && keys[i].compareTo(key) == 0){
            return values[i];
        }
        return null;
    }

    public int rank(Key key) {
        int lo = 0;
        int hi = keys.length - 1;
        while (lo <= hi){
            int mid = lo + (hi - lo)/2;
            if (key.compareTo(keys[mid]) < 0){
                hi = mid - 1;
            }else if (keys[mid].compareTo(key) < 0){
                lo = mid + 1;
            }else {
                return mid;
            }
        }
        return lo;
    }

    public void put(Key key, Value value){
        int i = rank(key);
        if (i < count && keys[i].compareTo(key) == 0){
            values[i] = value;
        }
        //每个键值对向后移动一位
        for (int j = count; j > i; j --){
            keys[j] = keys[j - 1];
            values[j] = values[j - 1];
        }
        keys[i] = key;
        values[i] = value;
        count ++;
    }

    public Key min(){
        return keys[0];
    }

    public Key max(){
        return keys[count - 1];
    }

    public Key select(int k){
        return keys[k];
    }

    public Key ceiling(Key key){
        int i = rank(key);
        return keys[i];
    }

    public Key floor(Key key){
        // TODO: 2020/12/3
        return null;
    }

    public Key delete(Key key){
        // TODO: 2020/12/3
        return null;
    }



}
