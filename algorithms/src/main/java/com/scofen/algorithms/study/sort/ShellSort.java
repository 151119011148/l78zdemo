package com.scofen.algorithms.study.sort;

/**
 * @author 高锋
 * @className: SelectionSort
 * @description:
 * 希尔排序(Shell's Sort)是插入排序的一种又称“缩小增量排序”（Diminishing Increment Sort），
 * 是直接插入排序算法的一种更高效的改进版本。希尔排序是非稳定排序算法。
 * 该方法因 D.L.Shell 于 1959 年提出而得名
 *
 * 希尔排序是把记录按下标的一定增量分组，
 * 对每组使用直接插入排序算法排序；
 * 随着增量逐渐减少，每组包含的关键词越来越多，当增量减至 1 时，
 * 整个文件恰被分成一组，算法便终止。

 */
public class ShellSort extends AbstractSort {

    @Override
    public Comparable[] sort(Comparable[] target, boolean asc) {
        if (source.length < 2) {
            return source;
        }
        int N = source.length;
        int h = 1;
        while (h < N/3){
            h = 3* h + 1;//1,4,13,40,121,364,1093
        }
        while (h >= 1){
            for (int i = h; i < N; i ++){
                for (int j = i; j >= h && less(source[j], source[j - h]);  j -= h){
                    exchange(source, j, j - h);
                }
            }
            h = h/3;
        }
        return source;
    }

}
