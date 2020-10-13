package com.scofen.algorithms.pratise.sort;

/**
 * @author 高锋
 * @className: BubbleSort
 * @description:
 * 冒泡排序是一种简单的排序算法。它重复地走访过要排序的数列，
 * 一次比较两个元素，如果它们的顺序错误就把它们交换过来。走访数列的工作
 * 是重复地进行直到没有再需要交换，也就是说该数列已经排序完成。
 * 这个算法的名字由来是因为越小的元素会经由交换慢慢“浮”到数列的顶端。
 * @date 2019/11/716:49
 */
public class BubbleSort extends Sort {

    /**
     * @author 高锋
     * @description
     * 算法描述
     * 比较相邻的元素。如果第一个比第二个大，就交换它们两个；
     * 对每一对相邻元素作同样的工作，从开始第一对到结尾的最后一对，这样在最后的元素应该会是最大的数；
     * 针对所有的元素重复以上的步骤，除了最后一个；
     * 重复步骤1~3，直到排序完成。
     * @Date 17:05 2019/11/7
     * @Param [source]
     * @return java.util.List<java.lang.Integer>
     **/
    @Override
    public Integer[] sort(Integer[] source) {
        if (source.length < 2)
            return source;
        int count = 0;
        for (int outer = 0; outer < source.length; outer ++){
            for (int inner = 0; inner < source.length - 1 - outer; inner ++){
                if (source[inner] > source[inner + 1]){
                    int tmp = source[inner + 1];
                    source[inner + 1] = source[inner];
                    source[inner] = tmp;
                    count = statistical(count);
                }
            }
        }
        return source;
    }

    /**
     算法分析
     最佳情况：T(n) = O(n)   最差情况：T(n) = O(n2)   平均情况：T(n) = O(n2)
     **/
}