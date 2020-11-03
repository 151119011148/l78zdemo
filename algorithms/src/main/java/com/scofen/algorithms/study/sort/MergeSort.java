package com.scofen.algorithms.study.sort;

import java.util.Arrays;

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
        if (source.length < 2) {
            return source;
        }
        int middle = source.length/2;
        Comparable[] left = Arrays.copyOfRange(source, 0, middle);
        Comparable[] right = Arrays.copyOfRange(source, middle, source.length);
        return merge(sort(left), sort(right));
    }

    @Override
    public Comparable[] sort(Comparable[] target, boolean asc) {
        return new Comparable[0];
    }

    private Comparable[] merge(Comparable[] left, Comparable[] right) {
        Comparable[] result = new Comparable[left.length + right.length];
        for (int index = 0, i = 0, j = 0; index < result.length; index ++){
            if (i >= left.length){
                result[index] = right[j ++];
            }else if (j >= right.length){
                result[index] = right[i ++];
            }else if (left[i].compareTo(right[j]) > 0){
                result[index] = right[j ++];
            }else {
                result[index] = right[i ++];
            }
        }
        return result;
    }
/**
 * 算法分析

 最佳情况：T(n) = O(n)  最差情况：T(n) = O(nlogn)  平均情况：T(n) = O(nlogn)
 */

}
