package com.scofen.algorithms.study.binarytree;

import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * @author 高锋
 * @className: BinaryTreeImpl
 * @description: JAVA实现二叉树的先序、中序、后序、层序遍历
 * 递归和非递归版本
 * @date 2020/12/113:56
 */

@AllArgsConstructor
@NoArgsConstructor
public class BinaryTreeImpl implements BinaryTree{


    List<Integer> result = new ArrayList<>();

    /**
     * @return java.util.List<java.lang.Integer>
     * @author 高锋
     * @description 二叉树的前序遍历
     * 递归
     * @Date 13:57 2020/12/1
     * @Param [root]
     **/
    @Override
    public List<Integer> preOrderRec(TreeNode root) {
        if (root == null) {
        } else {
            System.out.println(root.val);
            result.add(root.val);
            TreeNode leftTree = root.left;
            if (leftTree != null) {
                preOrderRec(leftTree);
            }
            TreeNode rightTree = root.right;
            if (rightTree != null) {
                preOrderRec(rightTree);
            }
        }
        return result;
    }

    /**
     * @return java.util.List<java.lang.Integer>
     * @author 高锋
     * @description 二叉树的前序遍历
     * 非递归
     * @Date 13:57 2020/12/1
     * @Param [root]
     **/
    @Override
    public List<Integer> preOrderStack(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            if (!stack.isEmpty()) {
                root = stack.pop();
                System.out.println(root.val);
                result.add(root.val);
                root = root.right;
            }
        }
        return result;
    }


    /**
     * @return java.util.List<java.lang.Integer>
     * @author 高锋
     * @description 中序遍历
     * 递归
     * @Date 14:43 2020/12/1
     * @Param [root]
     **/
    @Override
    public List<Integer> inOrderRec(TreeNode root) {
        if (root == null) {
        } else {
            inOrderRec(root.left);
            System.out.println(root.val);
            result.add(root.val);
            inOrderRec(root.right);
        }
        return result;
    }

    /**
     * @return java.util.List<java.lang.Integer>
     * @author 高锋
     * @description 二叉树的中序遍历
     * 非递归
     * @Date 13:57 2020/12/1
     * @Param [root]
     **/
    @Override
    public List<Integer> inOrderStack(TreeNode root) {
        if (root == null) {
            return null;
        }
        Stack<TreeNode> s = new Stack<TreeNode>();
        while (root != null || !s.isEmpty()) {
            while (root != null) {
                s.push(root);//先访问再入栈
                root = root.left;
            }
            root = s.pop();
            result.add(root.val);
            System.out.println(root.val);
            root = root.right;//如果是null，出栈并处理右子树
        }
        return result;
    }

    /**
     * @return java.util.List<java.lang.Integer>
     * @author 高锋
     * @description 二叉树的后序遍历
     * 递归
     * @Date 13:57 2020/12/1
     * @Param [root]
     **/
    @Override
    public List<Integer> postOrderRec(TreeNode root) {
        if (root == null) {
        } else {
            postOrderRec(root.left);
            postOrderRec(root.right);
            result.add(root.val);
            System.out.println(root.val);
        }
        return result;
    }

    /**
     * @return java.util.List<java.lang.Integer>
     * @author 高锋
     * @description 二叉树的后序遍历
     * 非递归
     * 首先要搞清楚先序、中序、后序的非递归算法共同之处：用栈来保存先前走过的路径，
     * 以便可以在访问完子树后,可以利用栈中的信息,回退到当前节点的双亲节点,进行下一步操作。
     *     后序遍历的非递归算法是三种顺序中最复杂的，原因在于，后序遍历是先访问左、右子树,
     *     再访问根节点，而在非递归算法中，利用栈回退到时，并不知道是从左子树回退到根节点，
     *     还是从右子树回退到根节点，如果从左子树回退到根节点，此时就应该去访问右子树，
     *     而如果从右子树回退到根节点，此时就应该访问根节点。所以相比前序和后序，
     *     必须得在压栈时添加信息，以便在退栈时可以知道是从左子树返回，
     *     还是从右子树返回进而决定下一步的操作。
     * @Date 13:57 2020/12/1
     * @Param [root]
     **/
    @Override
    public List<Integer> postOrderStack(TreeNode root) {
        if (root == null) {
            return null;
        }
        Stack<TreeNode> s = new Stack<>();
        Map<TreeNode, Boolean> map = new HashMap<>();
        s.push(root);
        while (!s.isEmpty()) {
            TreeNode temp = s.peek();
            if (temp.left != null && !map.containsKey(temp.left)) {
                temp = temp.left;
                while (temp != null) {
                    if (map.containsKey(temp)) {
                        break;
                    } else {
                        s.push(temp);
                    }
                    temp = temp.left;
                }
                continue;
            }
            if (temp.right != null && !map.containsKey(temp.right)) {
                s.push(temp.right);
                continue;
            }
            TreeNode t = s.pop();
            map.put(t, true);
            result.add(t.val);
            System.out.println(t.val);
        }
        return result;
    }

    /**
     * @param root 树根节点
     *             层次遍历
     *             层序遍历二叉树，用队列实现，先将根节点入队列，
     *             只要队列不为空，然后出队列，并访问，接着将访问节点的左右子树依次入队列
     */
    @Override
    public List<List<Integer>> levelTravel(TreeNode root) {
        List<List<Integer>> ret = new ArrayList<>();
        if (root == null) {
            return ret;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            List<Integer> level = new ArrayList<>();
            int currentLevelSize = queue.size();
            for (int i = 1; i <= currentLevelSize; ++i) {
                TreeNode node = queue.poll();
                assert node != null;
                level.add(node.val);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            ret.add(level);
        }

        return ret;

    }

    public int depth = 0;
    @Override
    public int maxDepthByHigh(TreeNode root, int temp) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            depth = Math.max(depth, temp);
        }
        maxDepthByHigh(root.left, temp + 1);
        maxDepthByHigh(root.right, temp + 1);
        return depth;
    }

    @Override
    public int maxDepthByLow(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftDepth = maxDepthByLow(root.left);
        int rightDepth = maxDepthByLow(root.right);
        return Math.max(leftDepth, rightDepth) + 1;
    }



    //迭代实现
    @Override
    public TreeNode buildTreePreInStack(int[] preorder, int[] inorder) {
        if (preorder == null || preorder.length == 0) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[0]);
        Deque<TreeNode> stack = new LinkedList<>();
        stack.push(root);
        int inorderIndex = 0;
        for (int i = 1; i < preorder.length; i++) {
            int preorderVal = preorder[i];
            TreeNode node = stack.peek();
            if (node.val != inorder[inorderIndex]) {
                node.left = new TreeNode(preorderVal);
                stack.push(node.left);
            } else {
                while (!stack.isEmpty() && stack.peek().val == inorder[inorderIndex]) {
                    node = stack.pop();
                    inorderIndex++;
                }
                node.right = new TreeNode(preorderVal);
                stack.push(node.right);
            }
        }
        return root;

    }

    //递归实现
    @Override
    public TreeNode buildTreePreInRec(int[] preorder, int[] inorder) {
        int preLen = preorder.length;
        int inLen = inorder.length;
        if (preLen != inLen){
            throw new RuntimeException("Incorrect Data Input!");
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0 ; i < inLen; i ++){
            map.put(inorder[i], i);
        }
        return buildTreePreIn(preorder, 0, preLen -1, map, 0, inLen -1);
    }

    private TreeNode buildTreePreIn(int[] preorder, int preLeft, int preRight, Map<Integer, Integer> map, int inLeft, int inRight) {
        if (preLeft > preRight || inLeft > inRight){
            return null;
        }
        int rootVal = preorder[preLeft];
        TreeNode root = new TreeNode(rootVal);
        //中序子树根节点位置
        int pIndex = map.get(rootVal);

        root.left = buildTreePreIn(preorder, preLeft + 1, pIndex - inLeft + preLeft,
            map, inLeft, pIndex - 1);
        root.right = buildTreePreIn(preorder, pIndex - inLeft + preLeft + 1, preRight,
            map, pIndex + 1, inRight);
        return root;
    }

    @Override
    public TreeNode buildTreeInPostRec(int[] inorder, int[] postorder) {
        int postLen = postorder.length;
        int inLen = inorder.length;
        if (postLen != inLen){
            throw new RuntimeException("Incorrect Data Input!");
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0 ; i < inLen; i ++){
            map.put(inorder[i], i);
        }
        return buildTreeInPost(postorder, 0, postLen -1, map, 0, inLen -1);
    }

    private TreeNode buildTreeInPost(int[] postorder, int postLeft, int postRight, Map<Integer, Integer> map, int inLeft, int inRight) {
        if (postLeft > postRight || inLeft > inRight){
            return null;
        }
        int rootVal = postorder[postRight];
        TreeNode root = new TreeNode(rootVal);
        //中序子树根节点位置
        int pIndex = map.get(rootVal);

        root.left = buildTreeInPost(postorder, postLeft, pIndex - 1 -inLeft + postLeft,
                map, inLeft, pIndex - 1);
        root.right = buildTreeInPost(postorder, postRight - inRight+pIndex, postRight - 1,
                map, pIndex + 1, inRight);
        return root;
    }

    @Override
    public TreeNode buildTreeInPostStack(int[] inorder, int[] postorder) {
        return null;
    }

    @Override
    public TreeNode connect(TreeNode root) {
        if (root == null) {
            return root;
        }

        // 初始化队列同时将第一层节点加入队列中，即根节点
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        // 外层的 while 循环迭代的是层数
        while (!queue.isEmpty()) {

            // 记录当前队列大小
            int size = queue.size();

            // 遍历这一层的所有节点
            for (int i = 0; i < size; i++) {

                // 从队首取出元素
                TreeNode node = queue.poll();

                // 连接
                if (i < size - 1) {
                    node.next = queue.peek();
                }

                // 拓展下一层节点
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
        }

        // 返回根节点
        return root;

    }

}
