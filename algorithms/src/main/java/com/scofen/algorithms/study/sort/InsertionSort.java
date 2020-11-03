package com.scofen.algorithms.study.sort;

/**
 * @author 高锋
 * @className: InsertionSort
 * @description: 插入排序（Insertion-AbstractSort）的算法描述是一种简单直观的排序算法。
 * 它的工作原理是通过构建有序序列，对于未排序数据，在已排序序列中从后向前扫描，
 * 找到相应位置并插入。
 * <p>
 * 插入排序在实现上，通常采用in-place排序（即只需用到O(1)的额外空间的排序），
 * 因而在从后向前扫描过程中，需要反复把已排序元素逐步向后挪位，
 * 为最新元素提供插入空间。
 */
public class InsertionSort extends AbstractSort {

    /**
     * 从第一个元素开始，该元素可以认为已经被排序；
     * 取出下一个元素，在已经排序的元素序列中从后向前扫描；
     * 如果该元素（已排序）大于新元素，将该元素移到下一位置；
     * 重复步骤3，直到找到已排序的元素小于或者等于新元素的位置；
     * 将新元素插入到该位置后；
     * 重复步骤2~5。
     */
    @Override
    public Comparable[] sort(Comparable[] target, boolean asc) {
        if (source.length < 2) {
            return source;
        }
        //从小到大排序
        if (asc) {
            for (int outer = 1; outer < source.length; outer++) {
                //将a[i]插入到a[i-1], a[i-2] ...
                for (int inner = outer; inner > 0 && less(source[inner], source[inner - 1]); inner--) {
                    //交换位置
                    exchange(source, inner, inner - 1);
                }
            }
            return source;
            //从大到小排序
        } else {
            for (int outer = 1; outer < source.length; outer++) {
                //将a[i]插入到a[i-1], a[i-2] ...
                for (int inner = 1; inner <= outer; inner++) {
                    if (!less(source[inner], source[inner - 1])) {
                        //交换位置
                        exchange(source, inner, inner - 1);
                    }
                }
            }
            return source;
        }
    }

    /**
     最佳情况：T(n) = O(n)   最坏情况：T(n) = O(n2)   平均情况：T(n) = O(n2)
     **/

}
