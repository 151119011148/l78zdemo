package com.scofen.algorithms.study.sort.quick;

import com.scofen.algorithms.study.sort.AbstractSort;

/**
 * Create by  GF  in  9:40 2019/11/10
 * Description:
 * 通过一趟排序将待排记录分隔成独立的两部分，其中一部分记录的关键字均比另一部分的关键字小，
 * 则可分别对这两部分记录继续进行排序，以达到整个序列有序。
 * <p>
 * 快速排序是一个“交换类”的排序，以军训排队为例，教官说：“第一个同学出列，其他人以他为中心，
 * 比他矮的全排到他的左边，比他高的全排他右边。”这就是一趟快速排序。
 * 可以看出，一趟快速排序是以一个“枢轴”为中心，将序列分成两部分，枢轴的一边全是比它小
 * （或者小于等于）的，另一边全是比他大（或者大于等于）的
 */
public class QuickSort extends AbstractSort {


    /**
     * 快速排序使用分治法来把一个串（list）分为两个子串（sub-lists）。具体算法描述如下：
     * <p>
     * 从数列中挑出一个元素，称为 “基准”（pivot）；
     * 重新排序数列，所有元素比基准值小的摆放在基准前面，所有元素比基准值大的摆在基准的
     * 后面（相同的数可以到任一边）。在这个分区退出之后，该基准就处于数列的中间位置。
     * 这个称为分区（partition）操作；
     * 递归地（recursive）把小于基准值元素的子数列和大于基准值元素的子数列排序。
     * @param source
     */
    @Override
    public Comparable[] sort(Comparable[] source) {
//        StdRandom.shuffle(source);
        return sort(source, true);
    }

    @Override
    public Comparable[] sort(Comparable[] source, boolean asc) {
     sort(source, 0, source.length - 1, asc);
        return source;
    }


    void sort(Comparable[] source, int start, int end, boolean asc) {
        if (end <= start) return;
        //划分数组并获得基准元素位置
        int j = partition(source, start, end, asc);
        //对比较元素左边进行排序
        sort(source, start, j - 1, asc);
        //对比较元素右边进行排序
        sort(source, j + 1, end, asc);
    }

    /**
     * @author 高锋
     * @description 得到一个基准元素的下标。左边和右边已经处理好
     * @Date 17:08 2020/11/17
     * @Param [source, lo, hi, asc]
     * @return int
     **/
    private int partition(Comparable[] source, int lo, int hi, boolean asc) {
        //指向数组头的指针
        //指向数组尾的指针
        int i = lo;
        int j = hi + 1;
        Comparable v = source[lo];    //将该数组第一个元素设置为比较元素
        while (true) {
            //从左至右找到第一个大于比较元素的数
            while (less(source[++ i], v)){
               if (i == hi){
                   break;
               }
            }
            //从右至左找到第一个小于比较元素的数
            while (less(v, source[-- j])){
                if (j == lo){
                    break;
                }
            }

            if (i >= j){
                break;
            }
            //将大数与小数交换
            exchange(source, i, j);
        }
        //将比较元素交换到期望位置
        exchange(source, lo, j);
        //返回比较元素的位置
        return j;
    }



}
