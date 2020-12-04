package com.scofen.algorithms.study.sort.heap;

import com.scofen.algorithms.study.sort.AbstractSort;

/**
 * @author 高锋
 * @className: HeapSort
 * @description: 堆排序是利用堆这种数据结构而设计的一种排序算法，堆排序是一种选择排序，
 * 它的最坏，最好，平均时间复杂度均为O(nlogn)，它也是不稳定排序。
 * @date 2020/11/1717:16
 */
public class HeapSort extends AbstractSort {


    /**
     * @return java.lang.Comparable[]
     * @description a.将无需序列构建成一个堆，根据升序降序需求选择大顶堆或小顶堆;
     * b.将堆顶元素与末尾元素交换，将最大元素"沉"到数组末端;
     * c.重新调整结构，使其满足堆定义，然后继续交换堆顶元素与当前末尾元素，反复执行调整+交换步骤，直到整个序列有序。
     * @Date 17:30 2020/11/17
     * @Param [target, asc]
     **/
    @Override
    public Comparable[] sort(Comparable[] target, boolean asc) {
        //1.构建大顶堆
        createHeap(target);
        //2.调整堆结构(交换堆顶元素与末尾元素)
        for (int j = target.length - 1; j > 0; j--) {
            //将堆顶元素与末尾元素进行交换
            exchange(target, 0, j);
            //重新对堆进行调整
            adjustHeap(target, 0, j);
        }
        return target;
    }

    private void createHeap(Comparable[] target) {
        for (int i = target.length / 2 - 1; i >= 0; i--) {
            //从最后一个非叶子结点从下至上，从右至左调整结构
            adjustHeap(target, i, target.length);
        }
    }

    /**
     * @return void
     * @description 调整大顶堆（仅是调整过程，建立在大顶堆已构建的基础上
     * 从最后一个非叶子结点从下至上，从右至左调整结构
     * @Date 17:17 2020/11/17
     * @Param [target, i, j]
     **/
    private void adjustHeap(Comparable[] target, int i, int length) {
        //先取出当前元素i（父节点）
        Comparable father = target[i];
        //从i结点的左子结点开始，也就是2i+1处开始
        for (int k = i * 2 + 1; k < length; k = k * 2 + 1) {
            //如果右子节点小于总长度&&左子结点小于右子结点，k指向右子结点。没毛病
            if (k + 1 < length && less(target[k], target[k + 1])) {
                k++;
            }
            //如果（左|右）子节点大于父节点，将子节点值赋给父节点（不用进行交换）
            if (less(father, target[k])) {
                target[i] = target[k];
                i = k;
            } else {
                break;
            }
        }
        //将temp值放到最终的位置
        target[i] = father;
    }


}
