package com.scofen.algorithms.study.binarytree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TreeNode {

    int val;

    TreeNode left;

    TreeNode right;

    TreeNode next;

    TreeNode(int val) {
        this.val = val;
    }

    List<Integer> result = new ArrayList<>();

}