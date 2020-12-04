/*
 * Copyright (c) 2001-2020 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.scofen.algorithms.study.binarytree;


/**
 * 基于二叉查找树的符号表
 * @author scofen
 * @version V1.0
 * @since 2020-12-03 11:24
 */
public class BinarySearchTreeMap<Key extends Comparable, Value> {

    class Node {

        private Key key;

        private Value val;

        private Node left;

        private Node right;

        /**
         * 以该节点位根的子树中的节点总数
         */
        private int count;

        public Node(){}

        public Node(Key key, Value value, int count){
            this.key = key;
            this.val = value;
            this.count = count;
        }

    }

    private Node root;


    public int size(){
        return size(root);
    }

    public int size(Node node){
        if (node == null){
            return 0;
        }else {
            return node.count;
        }
    }

    public Value get(Key key){
        return get(root, key);
    }

    public Value get(Node node, Key key){
        //在x为根节点的子树中查找并返回key对应的值
        //如果找不到返回null
        if (node == null){
            return null;
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0){
            return get(node.left, key);
        }else if(cmp > 0){
            return get(node.right, key);
        }
        return node.val;
    }

    public void put(Key key, Value value){
        put(root, key, value);
    }

    public Node put(Node node, Key key, Value value){
        if (node == null){
            return new Node(key, value, 1);
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0){
            node.left = put(node.left, key, value);
        }else if(cmp > 0){
            node.right = put(node.right, key, value);
        }else {
            node.val = value;
        }
        node.count = size(node.left) + size(node.right) + 1;
        return node;
    }


}
