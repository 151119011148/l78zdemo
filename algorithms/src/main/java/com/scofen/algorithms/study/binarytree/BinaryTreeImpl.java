package com.scofen.algorithms.study.binarytree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

import org.springframework.util.CollectionUtils;

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
    public List<Integer> preOrderRec(Node root) {
        if (root == null) {
        } else {
            System.out.println(root.val);
            result.add(root.val);
            Node leftTree = root.left;
            if (leftTree != null) {
                preOrderRec(leftTree);
            }
            Node rightTree = root.right;
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
    public List<Integer> preOrderStack(Node root) {
        Stack<Node> stack = new Stack<>();
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
    public List<Integer> inOrderRec(Node root) {
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
    public List<Integer> inOrderStack(Node root) {
        if (root == null) {
            return null;
        }
        Stack<Node> s = new Stack<Node>();
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
    public List<Integer> postOrderRec(Node root) {
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
    public List<Integer> postOrderStack(Node root) {
        if (root == null) {
            return null;
        }
        Stack<Node> s = new Stack<>();
        Map<Node, Boolean> map = new HashMap<>();
        s.push(root);
        while (!s.isEmpty()) {
            Node temp = s.peek();
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
            Node t = s.pop();
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
    public List<List<Integer>> levelTravel(Node root) {
        List<List<Integer>> ret = new ArrayList<>();
        if (root == null) {
            return ret;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            List<Integer> level = new ArrayList<>();
            int currentLevelSize = queue.size();
            for (int i = 1; i <= currentLevelSize; ++i) {
                Node node = queue.poll();
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
    public int maxDepthByHigh(Node root, int temp) {
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
    public int maxDepthByLow(Node root) {
        if (root == null) {
            return 0;
        }
        int leftDepth = maxDepthByLow(root.left);
        int rightDepth = maxDepthByLow(root.right);
        return Math.max(leftDepth, rightDepth) + 1;
    }



    //迭代实现
    @Override
    public Node buildTreePreInStack(int[] preorder, int[] inorder) {
        if (preorder == null || preorder.length == 0) {
            return null;
        }
        Node root = new Node(preorder[0]);
        Deque<Node> stack = new LinkedList<>();
        stack.push(root);
        int inorderIndex = 0;
        for (int i = 1; i < preorder.length; i++) {
            int preorderVal = preorder[i];
            Node node = stack.peek();
            if (node.val != inorder[inorderIndex]) {
                node.left = new Node(preorderVal);
                stack.push(node.left);
            } else {
                while (!stack.isEmpty() && stack.peek().val == inorder[inorderIndex]) {
                    node = stack.pop();
                    inorderIndex++;
                }
                node.right = new Node(preorderVal);
                stack.push(node.right);
            }
        }
        return root;

    }

    //递归实现
    @Override
    public Node buildTreePreInRec(int[] preorder, int[] inorder) {
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

    private Node buildTreePreIn(int[] preorder, int preLeft, int preRight, Map<Integer, Integer> map, int inLeft, int inRight) {
        if (preLeft > preRight || inLeft > inRight){
            return null;
        }
        int rootVal = preorder[preLeft];
        Node root = new Node(rootVal);
        //中序子树根节点位置
        int pIndex = map.get(rootVal);

        root.left = buildTreePreIn(preorder, preLeft + 1, pIndex - inLeft + preLeft,
                map, inLeft, pIndex - 1);
        root.right = buildTreePreIn(preorder, pIndex - inLeft + preLeft + 1, preRight,
                map, pIndex + 1, inRight);
        return root;
    }

    @Override
    public Node buildTreeInPostRec(int[] inorder, int[] postorder) {
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

    private Node buildTreeInPost(int[] postorder, int postLeft, int postRight, Map<Integer, Integer> map, int inLeft, int inRight) {
        if (postLeft > postRight || inLeft > inRight){
            return null;
        }
        int rootVal = postorder[postRight];
        Node root = new Node(rootVal);
        //中序子树根节点位置
        int pIndex = map.get(rootVal);

        root.left = buildTreeInPost(postorder, postLeft, pIndex - 1 -inLeft + postLeft,
                map, inLeft, pIndex - 1);
        root.right = buildTreeInPost(postorder, postRight - inRight+pIndex, postRight - 1,
                map, pIndex + 1, inRight);
        return root;
    }



    @Override
    public Node buildTreeInPost(int[] inorder, int[] postorder) {
        return null;
    }

    @Override
    public Node buildTreeInPostStack(int[] inorder, int[] postorder) {
        return null;
    }


    @Override
    public Node connect(Node root) {
        if (root == null) {
            return null;
        }
        // 初始化队列同时将第一层节点加入队列中，即根节点
        Queue<Node> queue = new LinkedList<Node>();
        queue.add(root);
        // 外层的 while 循环迭代的是层数
        while (!queue.isEmpty()) {
            // 记录当前队列大小
            int currentLevelSize = queue.size();
            // 遍历这一层的所有节点
            for (int i = 0; i < currentLevelSize; i ++) {
                // 从队首取出元素
                Node current = queue.poll();
                assert current != null;
                // 连接
                if (i < currentLevelSize - 1) {
                    current.next = queue.peek();
                }
                // 拓展下一层节点
                if (current.left != null) {
                    queue.offer(current.left);
                }
                if (current.right != null) {
                    queue.offer(current.right);
                }
            }
        }
        return root;
    }

    Node ans;
    @Override
    public Node lowestCommonAncestor(Node root, Node p, Node q) {
        //第一种：递归
        this.dfs(root, p, q);
        return ans;
        //第二种：
/*        dfs(root);
        while (p != null) {
            visited.add(p.val);
            p = parent.get(p.val);
        }
        while (q != null) {
            if (visited.contains(q.val)) {
                return q;
            }
            q = parent.get(q.val);
        }
        return null;*/

    }


    private boolean dfs(Node root, Node p, Node q) {
        if (root == null) {
            return false;
        }
        boolean lson = dfs(root.left, p, q);
        boolean rson = dfs(root.right, p, q);
        if ((lson && rson) || ((root.val == p.val || root.val == q.val) && (lson || rson))) {
            ans = root;
        }
        return lson || rson || (root.val == p.val || root.val == q.val);
    }

    Map<Integer, Node> parent = new HashMap<Integer, Node>();
    Set<Integer> visited = new HashSet<Integer>();

    /**
     * 我们可以用哈希表存储所有节点的父节点，然后我们就可以利用节点的父节点信息
     * 从 p 结点开始不断往上跳，并记录已经访问过的节点，再从 q 节点开始不断往上跳，
     * 如果碰到已经访问过的节点，那么这个节点就是我们要找的最近公共祖先。
     * @param root
     */
    public void dfs(Node root) {
//        从根节点开始遍历整棵二叉树，用哈希表记录每个节点的父节点指针。
//        从 p 节点开始不断往它的祖先移动，并用数据结构记录已经访问过的祖先节点。
//        同样，我们再从 q 节点开始不断往它的祖先移动，如果有祖先已经被访问过，即意味着这是 p 和 q 的深度最深的公共祖先，即 LCA 节点。
        if (root.left != null) {
            parent.put(root.left.val, root);
            dfs(root.left);
        }
        if (root.right != null) {
            parent.put(root.right.val, root);
            dfs(root.right);
        }
    }

    @Override
    public String serialize(Node root) {
        return serialize(root, "");
    }

    public String serialize(Node root, String str) {
        if (root == null) {
            str += "null,";
        } else {
            str += root.val + ",";
            str = serialize(root.left, str);
            str = serialize(root.right, str);
        }
        return str;
    }


    @Override
    public Node deserialize(String data) {
        String[] data_array = data.split(",");
        List<String> data_list = new LinkedList<>(Arrays.asList(data_array));
        return deserialize(data_list);
    }

    private Node deserialize(List<String> list) {
        if (CollectionUtils.isEmpty(list)){
            return null;
        }
        if (list.get(0).equals("null")) {
            list.remove(0);
            return null;
        }
        Node root = new Node(Integer.valueOf(list.get(0)));
        list.remove(0);
        root.left = deserialize(list);
        root.right = deserialize(list);
        return root;

    }

    /**
     * 108. 将有序数组转换为二叉搜索树
     *"https://leetcode-cn.com/problems/convert-sorted-array-to-binary-search-tree/"
     将一个按照升序排列的有序数组，转换为一棵高度平衡二叉搜索树。
     本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差
     的绝对值不超过 1。
     给定有序数组: [-10,-3,0,5,9],

     一个可能的答案是：[0,-3,9,-10,null,5]，它可以表示下面这个高度平衡二叉搜索树：
     0
     / \
     -3   9
     /   /
     -10  5
     * @param nums
     * @return
     */
    public Node sortedArrayToBST(int[] nums) {
        return helper(nums, 0, nums.length - 1);
    }

    public Node helper(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }

        // 总是选择中间位置左边的数字作为根节点
        int mid = (left + right) / 2;

        Node root = new Node(nums[mid]);
        root.left = helper(nums, left, mid - 1);
        root.right = helper(nums, mid + 1, right);
        return root;
    }

    /**
     * 103. 二叉树的锯齿形层序遍历
     * "https://leetcode-cn.com/problems/binary-tree-zigzag-level-order-traversal/"
     * 给定一个二叉树，返回其节点值的锯齿形层序遍历。
     * （即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
     *
     * 例如：
     * 给定二叉树 [3,9,20,null,null,15,7],
     *
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     * 返回锯齿形层序遍历如下：
     *
     * [
     *   [3],
     *   [20,9],
     *   [15,7]
     * ]
     * @param root
     * @return
     */
    public List<List<Integer>> zigzagLevelOrder(Node root) {
        List<List<Integer>> ans = new LinkedList<>();
        if (root == null) {
            return ans;
        }

        Queue<Node> nodeQueue = new LinkedList<>();
        nodeQueue.add(root);
        boolean isOrderLeft = true;

        while (!nodeQueue.isEmpty()) {
            Deque<Integer> levelList = new LinkedList<>();
            int size = nodeQueue.size();
            for (int i = 0; i < size; ++i) {
                Node curNode = nodeQueue.poll();
                assert curNode != null;
                if (isOrderLeft) {
                    levelList.addLast(curNode.val);
                } else {
                    levelList.addFirst(curNode.val);
                }
                if (curNode.left != null) {
                    nodeQueue.add(curNode.left);
                }
                if (curNode.right != null) {
                    nodeQueue.add(curNode.right);
                }
            }
            ans.add(new LinkedList<>(levelList));
            isOrderLeft = !isOrderLeft;
        }

        return ans;
    }


}
