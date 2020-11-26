package com.scofen.algorithms.leetcode;

import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * @author 高锋
 * @className: EveryDay
 * @description: TODO
 * @date 2020/11/1714:13
 */
public class EveryDay {

    public static void main(String[] args) {
        //1030
//        System.out.println(allCellsDistOrder(2,3,1,2));

        //283
//        moveZeroes(new int[]{1, 0, 3, 12, 0, 1, 0, 3, 12});

        //147
//        ListNode head = new ListNode(4);
//        head.next = new ListNode(2);
//        head.next.next = new ListNode(3);
//        head.next.next.next = new ListNode(1);
//        insertionSortList(head);

        //1470
//        shuffle(new int[]{2, 5, 1, 3, 4, 7}, 3);

        //452
//        findMinArrowShots(new int[][]{{10, 16}, {2, 8}, {1, 6}, {7, 12}});

        //164
//        numJewelsInStones("aA", "aAAbbbb");

//        arrayStringsAreEqual(new String[]{"ab", "c"}, new String[]{"a", "bc"});

        dominantIndex(new int[]{0,0,0,1});

    }



    /**
     * 1030. 距离顺序排列矩阵单元格
     * 给出 R 行 C 列的矩阵，其中的单元格的整数坐标为 (r, c)，满足 0 <= r < R 且 0 <= c < C。
     * 另外，我们在该矩阵中给出了一个坐标为 (r0, c0) 的单元格。
     * 返回矩阵中的所有单元格的坐标，并按到 (r0, c0) 的距离从最小到最大的顺序排，
     * 其中，两单元格(r1, c1) 和 (r2, c2) 之间的距离是曼哈顿距离，|r1 - r2| + |c1 - c2|。
     * （你可以按任何满足此条件的顺序返回答案。）
     * <p>
     * 输入：R = 1, C = 2, r0 = 0, c0 = 0
     * 输出：[[0,0],[0,1]]
     * 解释：从 (r0, c0) 到其他单元格的距离为：[0,1]
     **/
    public static int[][] allCellsDistOrder(int R, int C, int r0, int c0) {
        int[][] result = new int[R * C][2];
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                int t = i * C + j;
                result[t][0] = i;
                result[t][1] = j;
            }
        }
        Arrays.sort(result, (o1, o2) -> {
            int dis1 = Math.abs(o1[0] - r0) + Math.abs(o1[1] - c0);
            int dis2 = Math.abs(o2[0] - r0) + Math.abs(o2[1] - c0);
            return Integer.compare(dis1, dis2);
        });
        return result;
    }

    /**
     * 283. 移动零
     * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，
     * 同时保持非零元素的相对顺序。
     * <p>
     * 输入: [0,1,0,3,12]
     * 输出: [1,3,12,0,0]
     **/
    public static void moveZeroes(int[] nums) {
        int length = nums.length, left = 0, right = 0;
        while (right < length) {
            if (nums[right] != 0) {
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;
                left++;
            }
            right++;
        }

    }


    /**
     * 147. 对链表进行插入排序
     * 插入排序的动画演示如上。从第一个元素开始，
     * 该链表可以被认为已经部分排序（用黑色表示）。
     * 每次迭代时，从输入数据中移除一个元素（用红色表示），
     * 并原地将其插入到已排好序的链表中。
     * <p>
     * 输入: 4->2->1->3
     * 输出: 1->2->3->4
     * <p>
     * 对链表进行插入排序的具体过程如下。
     * 首先判断给定的链表是否为空，若为空，则不需要进行排序，直接返回。
     * 创建哑节点 dummyHead，令 dummyHead.next = head。引入哑节点是为了便于在 head 节点之前插入节点。
     * 维护 lastSorted 为链表的已排序部分的最后一个节点，初始时 lastSorted = head。
     * 维护 curr 为待插入的元素，初始时 curr = head.next。
     * 比较 lastSorted 和 curr 的节点值。
     * 若 lastSorted.val <= curr.val，说明 curr 应该位于 lastSorted 之后，将 lastSorted 后移一位，curr 变成新的 lastSorted。
     * 否则，从链表的头节点开始往后遍历链表中的节点，寻找插入 curr 的位置。
     * 令 prev 为插入 curr 的位置的前一个节点，进行如下操作，完成对 curr 的插入：
     * lastSorted.next = curr.next
     * curr.next = prev.next
     * prev.next = curr
     * 令 curr = lastSorted.next，此时 curr 为下一个待插入的元素。
     * 重复第 5 步和第 6 步，直到 curr 变成空，排序结束。
     * 返回 dummyHead.next，为排序后的链表的头节点。
     **/
    public static ListNode insertionSortList(ListNode head) {

        if (head == null || head.next == null) {
            return head;
        }
        //创建哑节点 dummyHead，令 dummyHead.next = head。引入哑节点是为了便于在 head 节点之前插入节点。
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        //维护 lastSorted 为链表的已排序部分的最后一个节点，初始时 lastSorted = head。
        ListNode lastSorted = head;
        //维护 curr 为待插入的元素，初始时 curr = head.next。
        ListNode curr = head.next;
        while (curr != null) {
            if (lastSorted.val <= curr.val) {
                lastSorted = lastSorted.next;
            } else {
                ListNode prev = dummyHead;
                while (prev.next.val <= curr.val) {
                    prev = prev.next;
                }
                exchange(lastSorted, curr, prev);
            }
            curr = lastSorted.next;
        }
        return dummyHead.next;
    }

    private static void exchange(ListNode lastSorted, ListNode curr, ListNode prev) {
        lastSorted.next = curr.next;
        curr.next = prev.next;
        prev.next = curr;
    }


    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }


    /**
     * 1480. 一维数组的动态和
     * 给你一个数组 nums 。数组「动态和」的计算公式为：runningSum[i] = sum(nums[0]…nums[i]) 。
     * 请返回 nums 的动态和。
     * <p>
     * 输入：nums = [1,2,3,4]
     * 输出：[1,3,6,10]
     * 解释：动态和计算过程为 [1, 1+2, 1+2+3, 1+2+3+4] 。
     **/
    public int[] runningSum(int[] nums) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum = sum + nums[i];
            nums[i] = sum;
        }
        return nums;
    }


    /**
     * 剑指 Offer 58 - II. 左旋转字符串
     * 字符串的左旋转操作是把字符串前面的若干个字符转移到字符串的尾部。请定义一个函数实现字符串左旋转操作的功能。比如，输入字符串"abcdefg"和数字2，该函数将返回左旋转两位得到的结果"cdefgab"。
     * <p>
     * <p>
     * 输入: s = "abcdefg", k = 2
     * 输出: "cdefgab"
     **/
    public String reverseLeftWords(String s, int n) {
        return s.substring(n) + s.substring(1, n);
    }


    /**
     * 1512. 好数对的数目
     * 如果一组数字 (i,j) 满足 nums[i] == nums[j] 且 i < j ，就可以认为这是一组 好数对 。
     * 返回好数对的数目。
     * <p>
     * 输入：nums = [1,2,3,1,1,3]
     * 输出：4
     * 解释：有 4 组好数对，分别是 (0,3), (0,4), (3,4), (2,5) ，下标从 0 开始
     **/
    public int numIdenticalPairs(int[] nums) {
        //双指针
        int result = 0;
        for (int left = 0; left < nums.length - 1; left++) {
            for (int right = left + 1; right < nums.length; right++) {
                if (nums[left] == nums[right]) {
                    result++;
                }
            }
        }
        return result;
    }


    /**
     * 1470. 重新排列数组
     * 给你一个数组 nums ，数组中有 2n 个元素，按 [x1,x2,...,xn,y1,y2,...,yn] 的格式排列。
     * 请你将数组按 [x1,y1,x2,y2,...,xn,yn] 格式重新排列，返回重排后的数组。
     * <p>
     * <p>
     * 输入：nums = [2,5,1,3,4,7], n = 3
     * 输出：[2,3,5,4,1,7]
     * 解释：由于 x1=2, x2=5, x3=1, y1=3, y2=4, y3=7 ，所以答案为 [2,3,5,4,1,7]
     **/
    public static int[] shuffle(int[] nums, int n) {
        int[] result = new int[nums.length];
        int index = 0;
        for (int i = 0; i < n; i++) {
            result[index++] = nums[i];
            result[index++] = nums[i + n];
        }
        return result;
    }

    /**
     * 面试题 02.03. 删除中间节点
     * 实现一种算法，删除单向链表中间的某个节点（即不是第一个或最后一个节点），
     * 假定你只能访问该节点。
     * <p>
     * 输入：单向链表a->b->c->d->e->f中的节点c
     * 结果：不返回任何数据，但该链表变为a->b->d->e->f
     **/
    public void deleteNode(ListNode node) {
        //先替换值，再接入新的指针
        node.val = node.next.val;
        node.next = node.next.next;
    }


    /**
     * 1431. 拥有最多糖果的孩子
     * 实现一种算法，删除单向链表中间的某个节点（即不是第一个或最后一个节点），
     * 假定你只能访问该节点。
     * <p>
     * 给你一个数组 candies 和一个整数 extraCandies ，其中 candies[i] 代表第 i 个孩子拥有的糖果数目。
     * 对每一个孩子，检查是否存在一种方案，将额外的 extraCandies 个糖果分配给孩子们之后，此孩子有 最多 的糖果。注意，允许有多个孩子同时拥有 最多 的糖果数目。
     * <p>
     * 输入：candies = [2,3,5,1,3], extraCandies = 3
     * 输出：[true,true,true,false,true]
     * 解释：
     * 孩子 1 有 2 个糖果，如果他得到所有额外的糖果（3个），那么他总共有 5 个糖果，他将成为拥有最多糖果的孩子。
     * 孩子 2 有 3 个糖果，如果他得到至少 2 个额外糖果，那么他将成为拥有最多糖果的孩子。
     * 孩子 3 有 5 个糖果，他已经是拥有最多糖果的孩子。
     * 孩子 4 有 1 个糖果，即使他得到所有额外的糖果，他也只有 4 个糖果，无法成为拥有糖果最多的孩子。
     * 孩子 5 有 3 个糖果，如果他得到至少 2 个额外糖果，那么他将成为拥有最多糖果的孩子。
     **/
    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        int max = 0;
        for (int candy : candies) {
            if (max < candy) {
                max = candy;
            }
        }
        List<Boolean> list = new ArrayList<>();
        for (int candy : candies) {
            if (candy + extraCandies >= max) {
                list.add(true);
            } else {
                list.add(false);
            }
        }
        return list;
    }

    /**
     * 148. 排序链表
     * 给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。
     *
     * 进阶：
     * 你可以在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序吗？
     **/
    /**
     * 参考：Sort List——经典（链表中的归并排序） https://www.cnblogs.com/qiaozhoulin/p/4585401.html
     * <p>
     * 归并排序法：在动手之前一直觉得空间复杂度为常量不太可能，因为原来使用归并时，都是 O(N)的，
     * 需要复制出相等的空间来进行赋值归并。对于链表，实际上是可以实现常数空间占用的（链表的归并
     * 排序不需要额外的空间）。利用归并的思想，递归地将当前链表分为两段，然后merge，分两段的方
     * 法是使用 fast-slow 法，用两个指针，一个每次走两步，一个走一步，知道快的走到了末尾，然后
     * 慢的所在位置就是中间位置，这样就分成了两段。merge时，把两段头部节点值比较，用一个 p 指向
     * 较小的，且记录第一个节点，然后 两段的头一步一步向后走，p也一直向后走，总是指向较小节点，
     * 直至其中一个头为NULL，处理剩下的元素。最后返回记录的头即可。
     * <p>
     * 主要考察3个知识点，
     * 知识点1：归并排序的整体思想
     * 知识点2：找到一个链表的中间节点的方法
     * 知识点3：合并两个已排好序的链表为一个新的有序链表
     */
    public ListNode sortList(ListNode head) {
        // 如果没有结点/只有一个结点，无需排序，直接返回
        if (head == null || head.next == null) {
            return head;
        }
        // 快慢指针找出中位点
        ListNode slowp = head, fastp = head.next.next, l, r;
        while (fastp != null && fastp.next != null) {
            slowp = slowp.next;
            fastp = fastp.next.next;
        }
        // 对右半部分进行归并排序
        r = sortList(slowp.next);
        // 链表判断结束的标志：末尾节点.next==null
        slowp.next = null;
        // 对左半部分进行归并排序
        l = sortList(head);
        return mergeList(l, r);
    }

    // 合并链表
    private ListNode mergeList(ListNode l, ListNode r) {
        // 临时头节点
        ListNode tmpHead = new ListNode(-100000);
        ListNode p = tmpHead;
        while (l != null && r != null) {
            if (l.val < r.val) {
                p.next = l;
                l = l.next;
            } else {
                p.next = r;
                r = r.next;
            }
            p = p.next;
        }
        p.next = l == null ? r : l;
        return tmpHead.next;
    }


    /**
     * 242. 有效的字母异位词
     * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
     * <p>
     * 示例 1:
     * 输入: s = "anagram", t = "nagaram"
     * 输出: true
     */
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        char[] str1 = s.toCharArray();
        char[] str2 = t.toCharArray();
        Arrays.sort(str1);
        Arrays.sort(str2);
        return Arrays.equals(str1, str2);
    }


    /**
     * 452. 用最少数量的箭引爆气球
     * 在二维空间中有许多球形的气球。对于每个气球，提供的输入是水平方向上，
     * 气球直径的开始和结束坐标。由于它是水平的，所以纵坐标并不重要，
     * 因此只要知道开始和结束的横坐标就足够了。开始坐标总是小于结束坐标。
     * 一支弓箭可以沿着 x 轴从不同点完全垂直地射出。在坐标 x 处射出一支箭，
     * 若有一个气球的直径的开始和结束坐标为 xstart，xend， 且满足  xstart ≤ x ≤ xend，
     * 则该气球会被引爆。可以射出的弓箭的数量没有限制。 弓箭一旦被射出之后，
     * 可以无限地前进。我们想找到使得所有气球全部被引爆，所需的弓箭的最小数量。
     * 给你一个数组 points ，其中 points [i] = [xstart,xend] ，返回引爆所有气球所必须射出的
     * 最小弓箭数。
     * <p>
     * 示例 1:
     * 输入：points = [[10,16],[2,8],[1,6],[7,12]]
     * 输出：2
     * 解释：对于该样例，x = 6 可以射爆 [2,8],[1,6] 两个气球，以及 x = 11 射爆另外两个气球
     */
    public static int findMinArrowShots(int[][] points) {
        //考虑所有气球中右边界位置最靠左的那一个，
        // 那么一定有一支箭的射出位置就是它的右边界（否则就没有箭可以将其引爆了）。
        // 当我们确定了一支箭之后，我们就可以将这支箭引爆的所有气球移除，
        // 并从剩下未被引爆的气球中，再选择右边界位置最靠左的那一个，
        // 确定下一支箭，直到所有的气球都被引爆。
        if (points.length == 0) {
            return 0;
        }
        //通过右边界从小到大排序
        Arrays.sort(points, (point1, point2) -> {
            if (point1[1] > point2[1]) {
                return 1;
            } else if (point1[1] < point2[1]) {
                return -1;
            } else {
                return 0;
            }
        });
        int right = points[0][1];
        int result = 1;
        for (int[] balloon : points) {
            if (balloon[0] > right) {
                right = balloon[1];
                ++result;
            }
        }
        return result;
    }


    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * 222. 完全二叉树的节点个数
     * 完全二叉树的定义如下：在完全二叉树中，
     * 除了最底层节点可能没填满外，其余每层节点数都达到最大值，
     * 并且最下面一层的节点都集中在该层最左边的若干位置。
     * 若最底层为第 h 层，则该层包含 1~ 2h 个节点。
     */
    public int countNodes(TreeNode root) {
        return root == null ? 0 : countNodes(root.left) + countNodes(root.right) + 1;
    }

    /**
     * 剑指 Offer 24. 反转链表.
     * 定义一个函数，输入一个链表的头节点，反转该链表并输出反转后链表的头节点。
     */
    public ListNode reverseList(ListNode head) {
        //递归终止条件是当前节点为空或者下一个节点为空
        if (head == null || head.next == null) {
            return head;
        }
        //递归调用，反转每一个节点
        ListNode currentNode = reverseList(head.next);
        //每一个节点是怎么反转的
        head.next.next = head;
        //为了防止循环链表，需要将head.next设置为null
        head.next = null;
        return currentNode;

    }

    /**
     * 剑指 Offer 24. 反转链表.
     * 定义一个函数，输入一个链表的头节点，反转该链表并输出反转后链表的头节点。
     */
    public ListNode reverseList2(ListNode head) {
/*        我们可以申请两个指针：
        第一个指针叫 pre，最初是指向 null 的
        第二个指针 cur 指向 head，然后不断遍历 cur
        每次迭代到 cur，都将 cur 的 next 指向 pre，然后 pre 和 cur 前进一位。
        都迭代完了(cur 变成 null 了)，pre 就是最后一个节点了。*/

        ListNode pre = null;
        ListNode cur = head;
        ListNode tmp = null;
        while (cur != null) {
            tmp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = tmp;
        }
        return pre;

    }

    /**
     * 164. 最大间距
     * 给定一个无序的数组，找出数组在排序之后，相邻元素之间最大的差值。
     * 如果数组元素个数小于 2，则返回 0。
     */
    public int maximumGap(int[] nums) {
        if (nums.length == 1) {
            return 0;
        }
        Arrays.sort(nums);
        int result = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            int temp = nums[i + 1] - nums[i];
            result = Math.max(result, temp);
        }
        return result;
    }

    /**
     * 771. 宝石与石头
      给定字符串J 代表石头中宝石的类型，和字符串 S代表你拥有的石头。 S 中每个字符代表了一种你拥有的石头的类型，你想知道你拥有的石头中有多少是宝石。
     J 中的字母不重复，J 和 S中的所有字符都是字母。字母区分大小写，因此"a"和"A"是不同类型的石头。

     输入: J = "aA", S = "aAAbbbb"
     输出: 3
     */
    public static int numJewelsInStones(String J, String S) {
        int jewelsCount = 0;
        Set<Character> jewelsSet = new HashSet<>();
        int jewelsLength = J.length(), stonesLength = S.length();
        for (int i = 0; i < jewelsLength; i++) {
            char jewel = J.charAt(i);
            jewelsSet.add(jewel);
        }
        for (int i = 0; i < stonesLength; i++) {
            char stone = S.charAt(i);
            if (jewelsSet.contains(stone)) {
                jewelsCount++;
            }
        }
        return jewelsCount;

    }

    /**
     * 1662. 检查两个字符串数组是否相等
      给你两个字符串数组 word1 和 word2 。如果两个数组表示的字符串相同，返回 true ；否则，返回 false 。
     数组表示的字符串 是由数组中的所有元素 按顺序 连接形成的字符串。

     输入：word1 = ["ab", "c"], word2 = ["a", "bc"]
     输出：true
     解释：
     word1 表示的字符串为 "ab" + "c" -> "abc"
     word2 表示的字符串为 "a" + "bc" -> "abc"
     两个字符串相同，返回 true
     */
    public static boolean arrayStringsAreEqual(String[] word1, String[] word2) {
        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        for (String value : word1) {
            sb1.append(value);
        }
        for (String value : word2) {
            sb2.append(value);
        }
        return Objects.equals(sb1.toString(), sb2.toString());
    }


    /**
     * 747. 至少是其他数字两倍的最大数
     在一个给定的数组nums中，总是存在一个最大元素 。
     查找数组中的最大元素是否至少是数组中每个其他数字的两倍。
     如果是，则返回最大元素的索引，否则返回-1。

     输入: nums = [3, 6, 1, 0]
     输出: 1
     解释: 6是最大的整数, 对于数组中的其他整数,
     6大于数组中其他元素的两倍。6的索引是1, 所以我们返回1.
     */
    public static int dominantIndex(int[] nums) {
        int maxIndex = 0;
        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] > nums[maxIndex]) {
                maxIndex = i;
            }
        }
        for (int i = 0; i < nums.length; ++i) {
            if (maxIndex != i && nums[maxIndex] < 2 * nums[i]) {
                return -1;
            }
        }
        return maxIndex;
    }

    /**
     * 1013. 将数组分成和相等的三个部分
     给你一个整数数组 A，只有可以将其划分为三个和相等的非空部分时才返回 true，否则返回 false。

     形式上，如果可以找出索引 i+1 < j 
     且满足 A[0] + A[1] + ... + A[i] == A[i+1] + A[i+2] + ... + A[j-1] == A[j] + A[j-1] + ... + A[A.length - 1] 
     就可以将数组三等分。


     输入：[0,2,1,-6,6,-7,9,1,2,0,1]
     输出：true
     解释：0 + 2 + 1 = -6 + 6 - 7 + 9 + 1 = 2 + 0 + 1
     */
    public boolean canThreePartsEqualSum(int[] A) {
        int sum = 0;
        for(int i : A){
            sum += i;
        }
        if(sum%3 != 0){
            // 总和不是3的倍数，直接返回false
            return false;
        }

        // 使用双指针,从数组两头开始一起找，节约时间
        int left = 0;
        int leftSum = A[left];
        int right = A.length - 1;
        int rightSum = A[right];

        // 使用left + 1 < right 的原因，防止只能将数组分成两个部分
        // 例如：[1,-1,1,-1]，使用left < right作为判断条件就会出错
        while(left + 1 < right){
            if(leftSum == sum/3 && rightSum == sum/3){
                // 左右两边都等于 sum/3 ，中间也一定等于
                return true;
            }
            if(leftSum != sum/3){
                // left = 0赋予了初值，应该先left++，在leftSum += A[left];
                leftSum += A[++left];
            }
            if(rightSum != sum/3){
                // right = A.length - 1 赋予了初值，应该先right--，在rightSum += A[right];
                rightSum += A[--right];
            }
        }
        return false;
    }




}
