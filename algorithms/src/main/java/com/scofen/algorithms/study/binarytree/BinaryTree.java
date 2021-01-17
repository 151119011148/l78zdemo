package com.scofen.algorithms.study.binarytree;



import java.util.List;

/**
 * @author 高锋
 * @className: BinaryTree
 * @description: TODO
 * @date 2020/12/120:49
 */
public interface BinaryTree {

    public List<Integer> preOrderRec(TreeNode root);

    public List<Integer> preOrderStack(TreeNode root);

    public List<Integer> inOrderRec(TreeNode root);

    public List<Integer> inOrderStack(TreeNode root);

    public List<Integer> postOrderRec(TreeNode root);

    public List<Integer> postOrderStack(TreeNode root);

    public List<List<Integer>> levelTravel(TreeNode root);


    public int maxDepthByHigh(TreeNode root, int depth);

    /**
     * @author 高锋
     * @description 求最大深度
     * “自底向上” 的解决方案
     * @Date 20:57 2020/12/1
     * @Param [root]
     * @return int
     **/
    public int maxDepthByLow(TreeNode root);

    /**
     * 从前序与中序遍历序列构造二叉树
     * 递归实现
     * @param inorder
     * @param postorder
     * @return
     */
    public TreeNode buildTreePreInRec(int[] inorder, int[] postorder);

    /**
     * 从前序与中序遍历序列构造二叉树
     * 迭代实现
     * @param inorder
     * @param postorder
     * @return
     */
    public TreeNode buildTreePreInStack(int[] inorder, int[] postorder);

    /**
     * 从中序与后序遍历序列构造二叉树
     * 递归实现
     * @param inorder
     * @param postorder
     * @return
     */
    public TreeNode buildTreeInPostRec(int[] inorder, int[] postorder);

    /**
     * 从中序与后序遍历序列构造二叉树
     * 递归实现
     * @param inorder
     * @param postorder
     * @return
     */
    public TreeNode buildTreeInPostStack(int[] inorder, int[] postorder);


    /**
     * 填充每个节点的下一个右侧节点指针
     */
    public TreeNode connect(TreeNode root);


}
