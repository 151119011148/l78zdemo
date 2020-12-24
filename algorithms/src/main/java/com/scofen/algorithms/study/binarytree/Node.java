package com.scofen.algorithms.study.binarytree;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Node {

    int val;

    Node left;

    Node right;

    Node next;

    Node ans;

    Node(int val) {
        this.val = val;
    }

    List<Integer> result = new ArrayList<>();

}