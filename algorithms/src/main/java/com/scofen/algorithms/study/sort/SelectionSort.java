package com.scofen.algorithms.study.sort;

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
public class SelectionSort extends AbstractSort {

    @Override
    public Comparable[] sort(Comparable[] target, boolean asc) {
        if (source.length < 2) {
            return source;
        }
        //从小到大排序
        if (asc){
            for (int outer = 0; outer < source.length; outer ++){
                int minIndex = outer;
                //从原数组第outer位开始找到最小的数，和最外层的起始位置置换
                for (int inner = outer + 1; inner < source.length; inner ++){
                    if (less(source[inner], source[minIndex])){
                        //将最小的数的下标保存
                        minIndex = inner;
                    }
                }
                exchange(source, minIndex, outer);
            }
            return source;
        //从大到小排序
        }else {
            for (int i = 0; i < source.length; i ++){
                int maxIndex = i;
                for (int j = i + 1; j < source.length; j ++){
                    if (! less(source[j], source[maxIndex])){
                        maxIndex = j;
                    }
                }
                exchange(source, maxIndex, i);
            }
            return source;
        }

    }

}
