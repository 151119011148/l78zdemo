package com.scofen.algorithms.pratise.sort;

/**
 * @author 高锋
 * @className: InsertionSort
 * @description:
 * 插入排序（Insertion-Sort）的算法描述是一种简单直观的排序算法。
 * 它的工作原理是通过构建有序序列，对于未排序数据，在已排序序列中从后向前扫描，
 * 找到相应位置并插入。
 *
 * 插入排序在实现上，通常采用in-place排序（即只需用到O(1)的额外空间的排序），
 * 因而在从后向前扫描过程中，需要反复把已排序元素逐步向后挪位，
 * 为最新元素提供插入空间。
 */
public class InsertionSort extends Sort {

    /**
     从第一个元素开始，该元素可以认为已经被排序；
     取出下一个元素，在已经排序的元素序列中从后向前扫描；
     如果该元素（已排序）大于新元素，将该元素移到下一位置；
     重复步骤3，直到找到已排序的元素小于或者等于新元素的位置；
     将新元素插入到该位置后；
     重复步骤2~5。
     **/
    @Override
    public Integer[] sort(Integer[] source) {

        if (source.length < 2)
            return source;
        int currentValue;
        int count = 0;
        for (int i = 0; i < source.length; i ++){
            currentValue =  source[i + 1];
            int preIndex = i;
            while (currentValue < source[preIndex]){
                source[preIndex + 1] = source[preIndex];
                preIndex --;
                count = statistical(count);
            }
            source[preIndex + 1] = currentValue;
        }
        System.out.println("Bubble sorting complexity is : " + count);
        return source;
    }

    /**
     最佳情况：T(n) = O(n)   最坏情况：T(n) = O(n2)   平均情况：T(n) = O(n2)
     **/

}
