/*
 * Copyright (c) 2001-2020 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.scofen.algorithms.leetcode.linked;

import com.scofen.algorithms.leetcode.EveryDay;

import java.util.*;

/**
 * TODO
 *
 * @author weiyi
 * @version V1.0
 * @since 2020-12-14 14:18
 */
public class LinkedLists {

    public static void main(String[] args) {

    }

    /**
     * 19. 删除链表的倒数第N个节点
     * "https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list/"
     * 给定一个链表: 1->2->3->4->5, 和 n = 2.
     * <p>
     * 当删除了倒数第二个节点后，链表变为 1->2->3->5.
     *
     * @param head
     * @param n
     * @return
     */
    public LinkedNode removeNthFromEnd(LinkedNode head, int n) {
        LinkedNode dummy = new LinkedNode(0, head);
        LinkedNode fast = head;
        LinkedNode slow = dummy;
        for (int i = 0; i < n; ++i) {
            fast = fast.next;
        }
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        LinkedNode ans = dummy.next;
        return ans;

    }

    /**
     * 21. 合并两个有序链表
     * 将两个升序链表合并为一个新的 升序 链表并返回。
     * 新链表是通过拼接给定的两个链表的所有节点组成的。
     * 输入：1->2->4, 1->3->4
     * 输出：1->1->2->3->4->4
     *
     * @param l1
     * @param l2
     * @return
     */
    public LinkedNode mergeTwoLists(LinkedNode l1, LinkedNode l2) {
        LinkedNode prehead = new LinkedNode(-1);

        LinkedNode prev = prehead;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                prev.next = l1;
                l1 = l1.next;
            } else {
                prev.next = l2;
                l2 = l2.next;
            }
            prev = prev.next;
        }

        // 合并后 l1 和 l2 最多只有一个还未被合并完，我们直接将链表末尾指向未合并完的链表即可
        prev.next = (l1 == null ? l2 : l1);

        return prehead.next;

    }

    /**
     * 24. 两两交换链表中的节点
     * 示例 1：
     * 输入：head = [1,2,3,4]
     * 输出：[2,1,4,3]
     * <p>
     * 示例 2：
     * 输入：head = []
     * 输出：[]
     * <p>
     * 示例 3：
     * 输入：head = [1]
     * 输出：[1]
     */
    public ListNode swapPairs(ListNode head) {
//        创建哑结点 dummyHead，令 dummyHead.next = head。令 temp 表示当前到达的节点，初始时 temp = dummyHead。每次需要交换 temp 后面的两个节点。
//        temp.next = node2
//        node1.next = node2.next
//        node2.next = node1
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode temp = dummyHead;
        while (temp.next != null && temp.next.next != null) {
            ListNode node1 = temp.next;
            ListNode node2 = temp.next.next;
            temp.next = node2;
            node1.next = node2.next;
            node2.next = node1;
            temp = node1;
        }
        return dummyHead.next;

    }


    /**
     * 61. 旋转链表
     * "https://leetcode-cn.com/problems/rotate-list/"
     *
     * @param head
     * @param k
     * @return
     */
    public LinkedNode rotateRight(LinkedNode head, int k) {
        if (head == null) {
            return head;
        }
        LinkedNode fast = head, slow = head;
        //链表的长度
        int len = 0;
        //统计链表的长度，顺便找到链表的尾结点
        while (fast.next != null) {
            len++;
            fast = fast.next;
        }
        //首尾相连，先构成环
        fast.next = head;
        //慢指针移动的步数
        int step = len - k % (len + 1);
        //移动步数
        while (step-- > 0) {
            slow = slow.next;
        }
        //temp就是需要返回的结点
        LinkedNode temp = slow.next;
        //因为链表是环形的，slow就相当于尾结点了，
        //直接让他的next等于空
        slow.next = null;
        return temp;


    }

    public LinkedNode rotateRightSolution2(LinkedNode head, int k) {
        if (head == null) {
            return null;
        }

        //求链表长度
        int len = 0;
        LinkedNode cur = head;
        while (cur != null) {
            len += 1;
            cur = cur.next;
        }

        // 对长度取模
        k %= len;
        if (k == 0) {
            return head;
        }

        // 让 fast 先向后走 k 步
        LinkedNode fast = head, slow = head;
        while (k > 0) {
            fast = fast.next;
            k -= 1;
        }

        // 此时 slow 和 fast 之间的距离是 k；fast 指向第 k+1 个节点
        // 当 fast.next 为空时，fast 指向链表最后一个节点，slow 指向倒数第 k + 1 个节点
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        // newHead 是倒数第 k 个节点，即新链表的头
        LinkedNode newHead = slow.next;
        // 让倒数第 k + 1 个节点 和 倒数第 k 个节点断开
        slow.next = null;
        // 让最后一个节点指向原始链表的头
        fast.next = head;
        return newHead;

    }

    /**
     * 83.删除排序链表中的重复元素
     * 输入：head = [1,1,2,3,3]
     * 输出：[1,2,3]
     * <p>
     * 输入：head = [1,1,2]
     * 输出：[1,2]
     */
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode cur = head;
        while (cur.next != null) {
            if (cur.val == cur.next.val) {
                cur.next = cur.next.next;
            } else {
                cur = cur.next;
            }
        }
        return head;
    }

    /**
     * 82.删除排序链表中的重复元素二
     * 输入：head = [1,1,2,3,3]
     * 输出：[2,3]
     * <p>
     * 输入：head = [1,1,2]
     * 输出：[2]
     */
    public LinkedNode deleteDuplicates2(LinkedNode head) {
        if (head == null) {
            return head;
        }
        LinkedNode dummy = new LinkedNode(0, head);

        LinkedNode cur = dummy;
        while (cur.next != null && cur.next.next != null) {
            if (cur.next.val == cur.next.next.val) {
                int duplicateValue = cur.next.val;
                while (cur.next != null && cur.next.val == duplicateValue) {
                    cur.next = cur.next.next;
                }
            } else {
                cur = cur.next;
            }
        }

        return dummy.next;

    }

    /**
     * 86. 分隔链表
     * 给你一个链表的头节点 head 和一个特定值 x ，请你对链表进行分隔，使得所有 小于 x 的节点都出现在 大于或等于 x 的节点之前。
     * 你应当 保留 两个分区中每个节点的初始相对位置。
     * <p>
     * 输入：head = [1,4,3,2,5,2], x = 3
     * 输出：[1,2,2,4,3,5]
     * <p>
     * 输入：head = [2,1], x = 2
     * 输出：[1,2]
     */
    public ListNode partition(ListNode head, int x) {
        ListNode small = new ListNode(0);
        ListNode smallHead = small;
        ListNode large = new ListNode(0);
        ListNode largeHead = large;
        while (head != null) {
            if (head.val < x) {
                small.next = head;
                small = small.next;
            } else {
                large.next = head;
                large = large.next;
            }
            head = head.next;
        }
        large.next = null;
        small.next = largeHead.next;
        return smallHead.next;
    }


    /**
     * 92. 反转链表 II
     * "https://leetcode-cn.com/problems/reverse-linked-list-ii/"
     * 反转从位置 m 到 n 的链表。请使用一趟扫描完成反转。
     * <p>
     * 说明:
     * 1 ≤ m ≤ n ≤ 链表长度。
     * <p>
     * 示例:
     * <p>
     * 输入: 1->2->3->4->5->NULL, m = 2, n = 4
     * 输出: 1->4->3->2->5->NULL
     *
     * @param head
     * @param left
     * @param right
     * @return
     */
    public LinkedNode reverseBetween(LinkedNode head, int left, int right) {
        // 因为头节点有可能发生变化，使用虚拟头节点可以避免复杂的分类讨论
        LinkedNode dummyNode = new LinkedNode(-1);
        dummyNode.next = head;

        LinkedNode pre = dummyNode;
        // 第 1 步：从虚拟头节点走 left - 1 步，来到 left 节点的前一个节点
        // 建议写在 for 循环里，语义清晰
        for (int i = 0; i < left - 1; i++) {
            pre = pre.next;
        }

        // 第 2 步：从 pre 再走 right - left + 1 步，来到 right 节点
        LinkedNode rightNode = pre;
        for (int i = 0; i < right - left + 1; i++) {
            rightNode = rightNode.next;
        }

        // 第 3 步：切断出一个子链表（截取链表）
        LinkedNode leftNode = pre.next;
        LinkedNode curr = rightNode.next;

        // 注意：切断链接
        pre.next = null;
        rightNode.next = null;

        // 第 4 步：同第 206 题，反转链表的子区间
        reverseList(leftNode);

        // 第 5 步：接回到原来的链表中
        pre.next = rightNode;
        leftNode.next = curr;
        return dummyNode.next;
    }

    /**
     * 114. 二叉树展开为链表
     * 输入：root = [1,2,5,3,4,null,6]
     * 输出：[1,null,2,null,3,null,4,null,5,null,6]
     */
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public void flatten(TreeNode root) {
        List<TreeNode> list = new ArrayList<TreeNode>();
        preorderTraversal(root, list);
        int size = list.size();
        for (int i = 1; i < size; i++) {
            TreeNode prev = list.get(i - 1), curr = list.get(i);
            prev.left = null;
            prev.right = curr;
        }
    }

    public void preorderTraversal(TreeNode root, List<TreeNode> list) {
        Deque<TreeNode> stack = new LinkedList<TreeNode>();
        TreeNode node = root;
        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                list.add(node);
                stack.push(node);
                node = node.left;
            }
            node = stack.pop();
            node = node.right;
        }
    }


    /**
     * 141. 环形链表
     * <p>
     * 给定一个链表，判断链表中是否有环。
     * 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。
     * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置
     * （索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。注意：pos 不作为参数进行传递，
     * 仅仅是为了标识链表的实际情况。
     * 如果链表中存在环，则返回 true 。 否则，返回 false 。
     */
    public boolean hasCycle(LinkedNode head) {
        return solution1(head);
        //        return solution2(head);
    }

    /**
     * 时间复杂度：O(N)，其中 N 是链表中的节点数。
     * 最坏情况下我们需要遍历每个节点一次。
     * 空间复杂度：O(N)，其中 NN 是链表中的节点数。
     * 主要为哈希表的开销，最坏情况下我们需要将每个节点插入到哈希表中一次。
     */
    private boolean solution1(LinkedNode head) {
        Set<LinkedNode> seen = new HashSet<>();
        while (head != null) {
            if (!seen.add(head)) {
                return true;
            }
            head = head.next;
        }
        return false;
    }

    /**
     * 时间复杂度：O(N)，其中 N 是链表中的节点数。
     * <p>
     * 当链表中不存在环时，快指针将先于慢指针到达链表尾部，
     * 链表中每个节点至多被访问两次。
     * <p>
     * 当链表中存在环时，每一轮移动后，快慢指针的距离将减小一。
     * 而初始距离为环的长度，因此至多移动 N 轮。
     * <p>
     * 空间复杂度：O(1)。我们只使用了两个指针的额外空间。
     */
    private boolean solution2(LinkedNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        LinkedNode slow = head;
        LinkedNode fast = head.next;
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
            if (fast != null) {
                fast = fast.next;
            }
            if (fast == slow) {
                return true;
            }
        }
        return false;
    }

    /**
     * 142. 环形链表 II
     * 给定一个链表，返回链表开始入环的第一个节点。如果链表无环，则返回null。
     * <p>
     * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置
     * （索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。
     * 注意，pos 仅仅是用于标识环的情况，并不会作为参数传递到函数中。
     * <p>
     * 说明：不允许修改给定的链表。
     * <p>
     * 进阶：
     * 你是否可以使用 O(1) 空间解决此题？
     *
     * @param head
     * @return
     */
    public LinkedNode detectCycle(LinkedNode head) {
        /*根据题意，任意时刻，fast 指针走过的距离都为 slow 指针的 2 倍。
        因此，我们有
        a+(n+1)b+nc=2(a+b)⟹a=c+(n−1)(b+c)

        有了 a=c+(n-1)(b+c) 的等量关系，我们会发现：
        从相遇点到入环点的距离加上 n-1圈的环长，恰好等于从链表头部到入环点的距离。

        因此，当发现 slow 与fast 相遇时，我们再额外使用一个指针ptr。
        起始，它指向链表头部；随后，它和slow 每次向后移动一个位置。
        最终，它们会在入环点相遇。*/
        if (head == null) {
            return null;
        }
        LinkedNode slow = head, fast = head;
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
            if (fast != null) {
                fast = fast.next;
            } else {
                return null;
            }
            if (fast == slow) {
                LinkedNode ptr = head;
                while (ptr != slow) {
                    ptr = ptr.next;
                    slow = slow.next;
                }
                return ptr;
            }
        }
        return null;

    }

    /**
     * 160. 相交链表
     *
     * @param headA
     * @param headB
     * @return
     */
    public LinkedNode getIntersectionNode(LinkedNode headA, LinkedNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        LinkedNode pA = headA, pB = headB;
        while (pA != pB) {
            pA = pA == null ? headB : pA.next;
            pB = pB == null ? headA : pB.next;
        }
        return pA;

    }

    /**
     * 203 移除链表元素
     * 给你一个链表的头节点 head 和一个整数 val ，请你删除链表中所有满足 Node.val == val 的节点，并返回 新的头节点 。
     * <p>
     * 示例 2：
     * 输入：head = [], val = 1
     * 输出：[]
     * <p>
     * 示例 3：
     * 输入：head = [7,7,7,7], val = 7
     * 输出：[]
     * <p>
     * 输入：head = [1,2,6,3,4,5,6], val = 6
     * 输出：[1,2,3,4,5]
     */
    public LinkedNode removeElements(LinkedNode head, int val) {
        LinkedNode dummy = new LinkedNode(0, head), prev = dummy, cur = head;
        while (null != cur) {
            if (cur.val == val) {
                prev.next = cur.next;
            } else {
                prev = cur;
            }
            cur = cur.next;
        }
        return dummy.next;
    }

    /**
     * 206. 反转链表
     *
     * @param head
     * @return
     */
    public LinkedNode reverseList(LinkedNode head) {
        //申请节点，pre和 cur，pre指向null
        LinkedNode pre = null;
        LinkedNode cur = head;
        while (cur != null) {
            //记录当前节点的下一个节点
            LinkedNode tmp = cur.next;
            //然后将当前节点指向pre
            cur.next = pre;
            //pre和cur节点都前进一位
            pre = cur;
            cur = tmp;
        }
        return pre;
    }

    public LinkedNode reverseList1(LinkedNode head) {
        //递归终止条件是当前为空，或者下一个节点为空
        if (head == null || head.next == null) {
            return head;
        }
        //这里的cur就是最后一个节点
        LinkedNode cur = reverseList1(head.next);
        //这里请配合动画演示理解
        //如果链表是 1->2->3->4->5，那么此时的cur就是5
        //而head是4，head的下一个是5，下下一个是空
        //所以head.next.next 就是5->4
        head.next.next = head;
        //防止链表循环，需要将head.next设置为空
        head.next = null;
        //每层递归函数都返回cur，也就是最后一个节点
        return cur;

    }

    /**
     * 237. 删除链表中的节点
     * 输入：head = [4,5,1,9], node = 5
     * 输出：[4,1,9]
     * 解释：指定链表中值为 5 的第二个节点，那么在调用了你的函数之后，该链表应变为 4 -> 1 -> 9
     * <p>
     * 输入：head = [4,5,1,9], node = 1
     * 输出：[4,5,9]
     * 解释：指定链表中值为 1 的第三个节点，那么在调用了你的函数之后，该链表应变为 4 -> 5 -> 9
     */
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public void deleteNode(ListNode node) {
        //既然不能先删除自己，那就把自己整容成儿子，再假装自己就是儿子来养活孙子
        node.val = node.next.val;
        node.next = node.next.next;
    }

    /**
     * 328. 奇偶链表
     *
     * @param head
     * @return
     */
    public LinkedNode oddEvenList(LinkedNode head) {
        // 分别定义奇偶链表的 虚拟头结点 和 尾结点
        LinkedNode oddHead = new LinkedNode(0);
        LinkedNode oddTail = oddHead;
        LinkedNode evenHead = new LinkedNode(0);
        LinkedNode evenTail = evenHead;
        // 遍历原链表，根据 isOdd 标识位决定将当前结点插入到奇链表还是偶链表（尾插法）
        boolean isOdd = true;
        while (head != null) {
            if (isOdd) {
                oddTail.next = head;
                oddTail = oddTail.next;
            } else {
                evenTail.next = head;
                evenTail = evenTail.next;
            }
            head = head.next;
            isOdd = !isOdd;
        }
        // 将奇链表后面拼接上偶链表，并将偶链表的next设置为null
        oddTail.next = evenHead.next;
        evenTail.next = null;
        return oddHead.next;

    }

    /**
     * 234. 回文链表
     */
    public boolean isPalindrome(LinkedNode head) {
        LinkedNode fast = head, slow = head;
        //通过快慢指针找到中点
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        //如果fast不为空，说明链表的长度是奇数个
        if (fast != null) {
            slow = slow.next;
        }
        //反转后半部分链表
        slow = reverseList(slow);

        fast = head;
        while (slow != null) {
            //然后比较，判断节点值是否相等
            if (fast.val != slow.val) {
                return false;
            }
            fast = fast.next;
            slow = slow.next;
        }
        return true;
    }


    /**
     * @param l1
     * @param l2
     * @return
     */
    public LinkedNode addTwoNumbers(LinkedNode l1, LinkedNode l2) {
        LinkedNode prehead = new LinkedNode(-1);
        LinkedNode cur = prehead;
        boolean addOne = false;
        while (l1 != null || l2 != null) {
            int result = (l1 == null ? 0 : l1.val) + (l2 == null ? 0 : l2.val);
            result = addOne ? result + 1 : result;
            if (result > 9) {
                result = result - 10;
                addOne = true;
            } else {
                addOne = false;
            }
            cur.next = new LinkedNode(result);
            cur = cur.next;
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        if (addOne) {
            cur.next = new LinkedNode(1);
        }
        return prehead.next;
    }


    /**
     * 430. 扁平化多级双向链表
     * "https://leetcode-cn.com/problems/flatten-a-multilevel-doubly-linked-list/"
     * 多级双向链表中，除了指向下一个节点和前一个节点指针之外，它还有一个子链表指针，
     * 可能指向单独的双向链表。这些子列表也可能会有一个或多个自己的子项，依此类推，
     * 生成多级数据结构，如下面的示例所示。
     * <p>
     * 给你位于列表第一级的头节点，请你扁平化列表，使所有结点出现在单级双链表中。
     */
    class Node {
        public int val;
        public Node prev;
        public Node next;
        public Node child;
        Node random;
    }

    public Node flatten(Node head) {
        if (head == null) {
            return null;
        }
        Node nextNode = flatten(head.next);
        Node childNode = flatten(head.child);
        if (childNode == null) {
            head.next = nextNode;
            if (nextNode != null) {
                nextNode.prev = head;
            }
        } else {
            head.next = childNode;
            childNode.prev = head;
            while (childNode.next != null) {
                childNode = childNode.next;
            }
            childNode.next = nextNode;
            if (nextNode != null) {
                nextNode.prev = childNode;
            }
            head.child = null; // 注意前置节点的child要置空
        }
        return head;
    }

    /**
     * 138. 复制带随机指针的链表
     * "https://leetcode-cn.com/problems/copy-list-with-random-pointer/"
     * 给定一个链表，每个节点包含一个额外增加的随机指针，该指针可以指向链表中的
     * 任何节点或空节点。
     * <p>
     * 要求返回这个链表的 深拷贝。 
     * <p>
     * 我们用一个由 n 个节点组成的链表来表示输入/输出中的链表。每个节点用一个 
     * [val, random_index] 表示：
     * <p>
     * val：一个表示 Node.val 的整数。
     * random_index：随机指针指向的节点索引（范围从 0 到 n-1）；如果不指向任何节点，
     * 则为  null 。
     */
    // HashMap which holds old nodes as keys and new nodes as its values.
    HashMap<Node, Node> visitedHash = new HashMap<Node, Node>();

    public Node copyRandomList(Node head) {

        if (head == null) {
            return null;
        }

        // If we have already processed the current node, then we simply return the cloned version of
        // it.
        if (this.visitedHash.containsKey(head)) {
            return this.visitedHash.get(head);
        }

        // Create a new node with the value same as old node. (i.e. copy the node)
        Node node = new Node();
        node.val = head.val;

        // Save this value in the hash map. This is needed since there might be
        // loops during traversal due to randomness of random pointers and this would help us avoid
        // them.
        this.visitedHash.put(head, node);

        // Recursively copy the remaining linked list starting once from the next pointer and then from
        // the random pointer.
        // Thus we have two independent recursive calls.
        // Finally we update the next and random pointers for the new node created.
        node.next = this.copyRandomList(head.next);
        node.random = this.copyRandomList(head.random);

        return node;
    }

    /**
     * 1721 交换链表中第k个节点和倒数第k个节点
     */
    public ListNode swapNodes(ListNode head, int k) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;// 因为头结点可能会发生交换，所以要构造一个哑结点
        ListNode pre1 = dummy;// pre1指向第k个节点的前一个节点
        ListNode left = dummy.next;// 第k个节点
        ListNode pre2 = dummy;// pre2指向倒数第k个节点的前一个节点
        ListNode right = dummy.next;// 倒数第k个节点
        for (int i = 1; i < k; i++) {
            pre1 = pre1.next;
            left = left.next;
        }
        ListNode cur = left;
        ListNode temp = left.next;// 第k个节点的后一个节点
        while (cur.next != null) {
            pre2 = pre2.next;
            right = right.next;
            cur = cur.next;
        }
        if (right == pre1) {// 特殊情况，倒数第k个节点在第k个节点的左侧
            right.next = temp;
            left.next = right;
            pre2.next = left;
        } else {
            left.next = right.next;
            if (pre2 == left) {
                right.next = left;
            }// 特殊情况，第k个节点在倒数第k个节点的左侧
            else {
                pre2.next = left;
                right.next = temp;
            }
            pre1.next = right;
        }
        return dummy.next;

    }


}
