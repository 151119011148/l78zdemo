package com.scofen.algorithms.study.sort.merge;

import com.scofen.algorithms.study.sort.AbstractSort;

/**
 * Create by  GF  in  22:23 2019/11/10
 * Description:
 * 和选择排序一样，归并排序的性能不受输入数据的影响，但表现比选择排序好的多，
 * 因为始终都是O(n log n）的时间复杂度。代价是需要额外的内存空间。

 归并排序是建立在归并操作上的一种有效的排序算法。
 该算法是采用分治法（Divide and Conquer）的一个非常典型的应用。
 归并排序是一种稳定的排序方法。将已有序的子序列合并，得到完全有序的序列；
 即先使每个子序列有序，再使子序列段间有序。
 若将两个有序表合并成一个有序表，称为2-路归并。

 * Modified  By:
 */
public class MergeSort extends AbstractSort {


    /**
     把长度为n的输入序列分成两个长度为n/2的子序列；
     对这两个子序列分别采用归并排序；
     将两个排序好的子序列合并成一个最终的排序序列
     * @param source
     */
    @Override
    public Comparable[] sort(Comparable[] source) {
        return sort(source, true);
    }

    @Override
    public Comparable[] sort(Comparable[] source, boolean asc) {
        return partition(source, 0, source.length -1, asc);
    }

    private Comparable[] partition(Comparable[] source, int low, int high, boolean asc) {
        if (low == high){
            return new Comparable[] { source[low]};
        }
        int mid = low + (high - low) / 2;
        //分
        Comparable[] leftArr = partition(source, low, mid, asc); //左有序数组
        Comparable[] rightArr = partition(source, mid + 1, high, asc); //右有序数组
        //治
        return merge(leftArr, rightArr, asc);


    }

    private Comparable[] merge(Comparable[] leftArr, Comparable[] rightArr, boolean asc) {
        //治
        //新有序数组
        target = new Comparable[leftArr.length + rightArr.length];
        //双指针
        int m = 0, i = 0, j = 0;
        while (i < leftArr.length && j < rightArr.length) {
            boolean xiaoyu = less(leftArr[i],  rightArr[j]);
            if (asc){
                target[m++] = xiaoyu ? leftArr[i++] : rightArr[j++];
            }else {
                target[m++] = xiaoyu ? rightArr[j++] : leftArr[i++];
            }
            count = statistical(count);
        }
        while (i < leftArr.length){
            target[m++] = leftArr[i++];
            count = statistical(count);
        }
        while (j < rightArr.length){
            target[m++] = rightArr[j++];
            count = statistical(count);
        }
        return target;
    }
/**
 * 算法分析

 最佳情况：T(n) = O(n)  最差情况：T(n) = O(nlogn)  平均情况：T(n) = O(nlogn)
 */

}
