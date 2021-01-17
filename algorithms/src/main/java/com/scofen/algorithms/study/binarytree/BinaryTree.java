package com.scofen.algorithms.study.binarytree;



import java.util.List;

/**
 * @author 高锋
 * @className: BinaryTree
 * @description: TODO
 * @date 2020/12/120:49
 */
public interface BinaryTree {

    public List<Integer> preOrderRec(Node root);

    public List<Integer> preOrderStack(Node root);

    public List<Integer> inOrderRec(Node root);

    public List<Integer> inOrderStack(Node root);

    public List<Integer> postOrderRec(Node root);

    public List<Integer> postOrderStack(Node root);

    /**
     * 层序遍历
     * @param root
     * @return
     */
    public List<List<Integer>> levelTravel(Node root);


    public int maxDepthByHigh(Node root, int depth);

    /**
     * @author 高锋
     * @description 求最大深度
     * “自底向上” 的解决方案
     * @Date 20:57 2020/12/1
     * @Param [root]
     * @return int
     **/
    public int maxDepthByLow(Node root);

    /**
     * 从前序与中序遍历序列构造二叉树
     * 递归实现
     * @param inorder
     * @param postorder
     * @return
     */
    public Node buildTreePreInRec(int[] inorder, int[] postorder);

    /**
     * 从前序与中序遍历序列构造二叉树
     * 迭代实现
     * @param inorder
     * @param postorder
     * @return
     */
    public Node buildTreePreInStack(int[] inorder, int[] postorder);

    /**
     * 从中序与后序遍历序列构造二叉树
     * 递归实现
     * @param inorder
     * @param postorder
     * @return
     */
    public Node buildTreeInPost(int[] inorder, int[] postorder);

    public Node buildTreeInPostRec(int[] inorder, int[] postorder);

    /**
     * 从中序与后序遍历序列构造二叉树
     * 递归实现
     * @param inorder
     * @param postorder
     * @return
     */
    public Node buildTreeInPostStack(int[] inorder, int[] postorder);


    /**
     * 填充每个节点的下一个右侧节点指针
     */
    public Node connect(Node root);


    /**
     * 二叉树的最近公共祖先
     * @param root
     * @param p
     * @param q
     * @return
     */
    public Node lowestCommonAncestor(Node root, Node p, Node q);

    /**
     * 你可以将以下二叉树：
     *
     *     1
     *    / \
     *   2   3
     *      / \
     *     4   5
     *
     * 序列化为 "[1,2,3,null,null,4,5]"
     * @param root
     * @return
     */
    // Encodes a tree to a single string.
    public String serialize(Node root);

    // Decodes your encoded data to tree.
    public Node deserialize(String data);


}
