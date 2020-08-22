package com.scofen.algorithms.pratise.sort;

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
public class QuickSort extends Sort {


    /**
     * 快速排序使用分治法来把一个串（list）分为两个子串（sub-lists）。具体算法描述如下：
     * <p>
     * 从数列中挑出一个元素，称为 “基准”（pivot）；
     * 重新排序数列，所有元素比基准值小的摆放在基准前面，所有元素比基准值大的摆在基准的
     * 后面（相同的数可以到任一边）。在这个分区退出之后，该基准就处于数列的中间位置。
     * 这个称为分区（partition）操作；
     * 递归地（recursive）把小于基准值元素的子数列和大于基准值元素的子数列排序。
     */
    @Override
    public Integer[] sort(Integer[] source) {

        return source;
    }

    Integer[] sort(Integer[] source, int start, int end) {

        int smallIndex = partition(source, start, end);    //划分数组并获得比较元素位置

        if (smallIndex > start) {
            sort(source, start, smallIndex - 1);    //对比较元素左边进行排序
        }

        if (smallIndex < end) {
            sort(source, smallIndex + 1, end);    //对比较元素右边进行排序
        }
        return source;
    }

    private int partition(Integer[] source, int start, int end) {

        int pivot = source[start];    //将该数组第一个元素设置为比较元素
        int smallIndex = start;    //指向数组头的指针
        int j = end;    //指向数组尾的指针
        while (smallIndex < j) {
            while (smallIndex < j && source[j] >= pivot){

                j--;    //从右至左找到第一个小于比较元素的数
            }
            while (smallIndex < j && source[smallIndex] <= pivot){

                   /*需要注意的是，这里的j--与i++的顺序不可以调换！如果调换了顺序，i会走过头，
                   以至于将后面较大的元素交换到数组开头*/
                smallIndex++;    //从左至右找到第一个大于比较元素的数
            }

            //将大数与小数交换
            if (smallIndex != j)
                swap(source, smallIndex, j);
        }
        swap(source, start, smallIndex);    //将比较元素交换到期望位置
        return smallIndex;    //返回比较元素的位置
    }

    private void swap(Integer[] source, int a, int b) {
        int tmp = source[a];
        source[a] = source[b];
        source[b] = tmp;
        setCount(getCount() + 1);
    }

}
