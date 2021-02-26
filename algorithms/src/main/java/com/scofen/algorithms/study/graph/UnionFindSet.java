/*
 * Copyright (c) 2001-2021 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.scofen.algorithms.study.graph;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * TODO
 *
 * @author scofen
 * @version V1.0
 * @since 2021-01-07 10:35
 */
public class UnionFindSet {

    /*基本概念
        并查集是一种数据结构
    并查集这三个字，一个字代表一个意思。
    并（Union），代表合并
    查（Find），代表查找
    集（Set），代表这是一个以字典为基础的数据结构，它的基本功能是合并集合中的元素，
    查找集合中的元素

        并查集的典型应用是有关连通分量的问题
    并查集解决单个问题（添加，合并，查找）的时间复杂度都是O(1)
    因此，并查集可以应用到在线算法中

        并查集的实现
    并查集跟树有些类似，只不过她跟树是相反的。在树这个数据结构里面，每个节点会记录
    它的子节点。在并查集里，每个节点会记录它的父节点。


    初始化
    当把一个新节点添加到并查集中，它的父节点应该为空
        def add(self,x):
        """
        添加新节点
        """
        if x not in self.father:
            self.father[x] = None


    合并两个节点
    如果发现两个节点是连通的，那么就要把他们合并，也就是他们的祖先是相同的。
    这里究竟把谁当做父节点一般是没有区别的。
    def merge(self,x,y,val):
        """
        合并两个节点
        """
        root_x,root_y = self.find(x),self.find(y)

        if root_x != root_y:
            self.father[root_x] = root_y

    两节点是否连通
    我们判断两个节点是否处于同一个连通分量的时候，就需要判断它们的祖先是否相同
        def find(self,x):
        """
        查找根节点
        """
        root = x

        while self.father[root] != None:
            root = self.father[root]

        return root


    这里有一个优化的点：如果我们树很深，比如说退化成链表，那么每次查询的效率都会非常低。
    所以我们要做一下路径压缩。也就是把树的深度固定为二。
    这么做可行的原因是，并查集只是记录了节点之间的连通关系，而节点相互连通只需要有一个
    相同的祖先就可以了。
    路径压缩可以用递归，也可以迭代。
 def find(self,x):
        """
        查找根节点
        路径压缩
        """
        root = x

        while self.father[root] != None:
            root = self.father[root]

        # 路径压缩
        while x != root:
            original_father = self.father[x]
            self.father[x] = root
            x = original_father

        return root

    路径压缩的时间复杂度为O(\log^*n),log ∗n 表示 n 取多少次log2n并向下取整以后 变成 1
    可以认为O(log∗n)=O(1),因为\log^*2^{65536} = 5，而2^{65536}是一个天文数字。
    这个时间复杂度当成结论记下就可以。

*/

    public static void main(String[] args) {
        smallestStringWithSwaps("cbartefgd",
            Lists.newArrayList(Lists.newArrayList(0, 1), Lists.newArrayList(1, 2), Lists.newArrayList(5, 6)));
    }

    /**
     * 547. 省份数量
     * "https://leetcode-cn.com/problems/number-of-provinces/"
     *
     * @param isConnected
     * @return
     */
    public int findCircleNum(int[][] isConnected) {
        int provinces = isConnected.length;
        UnionFind0 unionFind = new UnionFind0(provinces);

        for (int i = 0; i < provinces; i++) {
            for (int j = i + 1; j < provinces; j++) {
                if (isConnected[i][j] == 1) {
                    unionFind.union(i, j);
                }
            }
        }
        int circles = 0;
        for (int i = 0; i < provinces; i++) {
            if (unionFind.find(i) == i) {
                circles++;
            }
        }
        return circles;
    }

    private static class UnionFind0 {

        public int[] parent;

        public UnionFind0(int provinces) {
            parent = new int[provinces];
            for (int i = 0; i < provinces; i++) {
                parent[i] = i;
            }
        }

        public int find(int x){
            return parent[x] == x ? parent[x] : find(parent[x]);
        }

        public void union(int index1, int index2) {
            parent[find(index1)] = find(index2);
        }

    }


    /**
     * 1202. 交换字符串中的元素
     * "https://leetcode-cn.com/problems/smallest-string-with-swaps/"
     * 你可以 任意多次交换 在 pairs 中任意一对索引处的字符。
     * 返回在经过若干次交换后，s 可以变成的按字典序最小的字符串。
     *
     * @param s
     * @param pairs
     * @return
     */
    public static String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        if (pairs.size() == 0) {
            return s;
        }

        // 第 1 步：将任意交换的结点对输入并查集
        int len = s.length();
        UnionFind1 unionFind = new UnionFind1(len);
        for (List<Integer> pair : pairs) {
            int index1 = pair.get(0);
            int index2 = pair.get(1);
            unionFind.union(index1, index2);
        }

        // 第 2 步：构建映射关系
        char[] charArray = s.toCharArray();
        // key：连通分量的代表元，value：同一个连通分量的字符集合（保存在一个优先队列中）
        Map<Integer, PriorityQueue<Character>> hashMap = new HashMap<>(len);
        for (int i = 0; i < len; i++) {
            int root = unionFind.find(i);
            if (hashMap.containsKey(root)) {
                hashMap.get(root).offer(charArray[i]);
            } else {
                // PriorityQueue<Character> minHeap = new PriorityQueue<>();
                // minHeap.offer(charArray[i]);
                // hashMap.put(root, minHeap);
                // 上面三行代码等价于下面一行代码，JDK 1.8 以及以后支持下面的写法
                hashMap.computeIfAbsent(root, key -> new PriorityQueue<>()).offer(charArray[i]);
            }
        }

        // 第 3 步：重组字符串
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < len; i++) {
            int root = unionFind.find(i);
            stringBuilder.append(hashMap.get(root).poll());
        }
        return stringBuilder.toString();
    }

    public static class UnionFind1 {

        private int[] parent;
        /**
         * 以 i 为根结点的子树的高度（引入了路径压缩以后该定义并不准确）
         */
        private int[] rank;

        public UnionFind1(int n) {
            this.parent = new int[n];
            this.rank = new int[n];
            for (int i = 0; i < n; i++) {
                this.parent[i] = i;
                this.rank[i] = 1;
            }
        }

        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX == rootY) {
                return;
            }

            if (rank[rootX] == rank[rootY]) {
                parent[rootX] = rootY;
                // 此时以 rootY 为根结点的树的高度仅加了 1
                rank[rootY]++;
            } else if (rank[rootX] < rank[rootY]) {
                parent[rootX] = rootY;
                // 此时以 rootY 为根结点的树的高度不变
            } else {
                // 同理，此时以 rootX 为根结点的树的高度不变
                parent[rootY] = rootX;
            }
        }

        public int find(int x) {
            if (x != parent[x]) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }
    }

    /**
     * 947. 移除最多的同行或同列石头
     * "https://leetcode-cn.com/problems/most-stones-removed-with-same-row-or-column/"
     *
     * @param stones
     * @return
     */
    public int removeStones(int[][] stones) {
        UnionFind2 unionFind = new UnionFind2();

        for (int[] stone : stones) {
            unionFind.union(stone[0] + 10001, stone[1]);
        }
        //最多可以移除的石头的个数 = 所有石头的个数 - 连通分量的个数。
        return stones.length - unionFind.getCount();
    }

    private class UnionFind2 {

        private Map<Integer, Integer> parent;
        private int count;

        public UnionFind2() {
            this.parent = new HashMap<>();
            this.count = 0;
        }

        public int getCount() {
            return count;
        }

        public int find(int x) {
            if (!parent.containsKey(x)) {
                parent.put(x, x);
                count++;
            }

            if (x != parent.get(x)) {
                parent.put(x, find(parent.get(x)));
            }
            return parent.get(x);
        }

        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX == rootY) {
                return;
            }

            parent.put(rootX, rootY);
            count--;
        }
    }

    /**
     * 721. 账户合并
     * "https://leetcode-cn.com/problems/accounts-merge/"
     *
     * @param accounts
     * @return
     */
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        Map<String, Integer> emailToIndex = new HashMap<String, Integer>();
        Map<String, String> emailToName = new HashMap<String, String>();
        int emailsCount = 0;
        for (List<String> account : accounts) {
            String name = account.get(0);
            int size = account.size();
            for (int i = 1; i < size; i++) {
                String email = account.get(i);
                if (!emailToIndex.containsKey(email)) {
                    emailToIndex.put(email, emailsCount++);
                    emailToName.put(email, name);
                }
            }
        }
        UnionFind3 uf = new UnionFind3(emailsCount);
        for (List<String> account : accounts) {
            String firstEmail = account.get(1);
            int firstIndex = emailToIndex.get(firstEmail);
            int size = account.size();
            for (int i = 2; i < size; i++) {
                String nextEmail = account.get(i);
                int nextIndex = emailToIndex.get(nextEmail);
                uf.union(firstIndex, nextIndex);
            }
        }
        Map<Integer, List<String>> indexToEmails = new HashMap<Integer, List<String>>();
        for (String email : emailToIndex.keySet()) {
            int index = uf.find(emailToIndex.get(email));
            List<String> account = indexToEmails.getOrDefault(index, new ArrayList<String>());
            account.add(email);
            indexToEmails.put(index, account);
        }
        List<List<String>> merged = new ArrayList<List<String>>();
        for (List<String> emails : indexToEmails.values()) {
            Collections.sort(emails);
            String name = emailToName.get(emails.get(0));
            List<String> account = new ArrayList<String>();
            account.add(name);
            account.addAll(emails);
            merged.add(account);
        }
        return merged;
    }


    private class UnionFind3 {
        int[] parent;

        public UnionFind3(int n) {
            parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        public void union(int index1, int index2) {
            parent[find(index2)] = find(index1);
        }

        public int find(int index) {
            return parent[index] == index ? parent[index] : find(parent[index]);
        }
    }
    /**
     * 1584. 连接所有点的最小费用
     * "https://leetcode-cn.com/problems/min-cost-to-connect-all-points/"
     *
     * @param points
     * @return
     */
    public int minCostConnectPoints(int[][] points) {
        int h = points.length;
        UnionFind4 unionFind4 = new UnionFind4(h);
        ArrayList<Edge> edges = new ArrayList<>();
        //求边长
        for (int i = 0; i < h; i++) {
            for (int j = i + 1; j < h; j++) {
                int len = Math.abs(points[i][0] - points[j][0]) + Math.abs(points[i][1] - points[j][1]);
                if (len != 0) {
                    edges.add(new Edge(i, j, len));
                }
            }
        }
        int ans = 0;
        Collections.sort(edges);

        for (Edge e : edges) {
            int x = e.getX();
            int y = e.getY();
            int len = e.getLen();
            if (unionFind4.find(x) == unionFind4.find(y)) {
                continue;
            }
            ans += len;
            unionFind4.union(x, y);
        }

        return ans;
    }

    private class UnionFind4 {

        int[] parents; //并查集find数组

        public UnionFind4(int n) {
            parents = new int[n + 1];
            //初始化find数组，让初始每个节点都自成一个集合，互相不联通
            for (int i = 0; i < parents.length; i++) {
                parents[i] = i;
            }
        }

        public int find(int x) {//find函数，判断是否同一个root节点
            return parents[x] == x ? x : find(parents[x]);
        }

        //把两集合合并
        public void union(int index1, int index2) {
            parents[find(index1)] = find(index2);
        }

    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    class Edge implements Comparable<Edge> {

        private int x;

        private int y;

        private int len;

        @Override
        public int compareTo(Edge o) {//内置比较器实现按len升序
            return Integer.compare(this.len, o.len);
        }

    }

}
