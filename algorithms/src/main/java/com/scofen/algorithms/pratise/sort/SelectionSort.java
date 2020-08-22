package com.scofen.algorithms.pratise.sort;

/**
 * @author 高锋
 * @className: SelectionSort
 * @description:
 * 表现最稳定的排序算法之一，因为无论什么数据进去都是O(n2)的时间复杂度，
 * 所以用到它的时候，数据规模越小越好。唯一的好处可能就是不占用额外的内存空间
 * 了吧。理论上讲，选择排序可能也是平时排序一般人想到的最多的排序方法了吧。
 *
 * 选择排序(Selection-sort)是一种简单直观的排序算法。
 * 它的工作原理：首先在未排序序列中找到最小（大）元素，
 * 存放到排序序列的起始位置，然后，
 * 再从剩余未排序元素中继续寻找最小（大）元素，
 * 然后放到已排序序列的末尾。以此类推，直到所有元素均排序完毕。

 */
public class SelectionSort extends Sort {
    @Override
    public Integer[] sort(Integer[] source) {
        if (source.length < 2)
            return source;
        int count  = 0;
        for (int outer = 0; outer < source.length; outer ++){
            int minIndex = outer;
            for (int inner = outer; inner < source.length; inner ++){
                if (source[minIndex] > source[inner]){ //找到最小的数
                    minIndex = inner;//将最小的数的下标保存
                    count = statistical(count);                }
            }
            int tmp = source[minIndex];
            source[minIndex] = source[outer];
            source[outer] = tmp;
        }
        return source;
    }
}
