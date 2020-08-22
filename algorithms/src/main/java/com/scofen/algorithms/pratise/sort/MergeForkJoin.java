package com.scofen.algorithms.pratise.sort;

import lombok.Getter;

import java.util.Arrays;
import java.util.concurrent.RecursiveTask;

/**
 * Create by  GF  in  21:34 2019/12/8
 * Description:使用Fork/Join运行归并算法
 * Modified  By:
 */
public class MergeForkJoin {

    /**
     * 单个排序的子任务
     * @author yinwenjie
     */
    static class MyTask extends RecursiveTask<Integer[]> {

        @Getter
        private Integer source[];

        public MyTask(Integer source[]) {
            this.source = source;
        }


        @Override
        protected Integer[] compute() {
            int sourceLen = source.length;
            // 如果条件成立，说明任务中要进行排序的集合还不够小
            if(sourceLen > 2) {
                int midIndex = sourceLen / 2;
                // 拆分成两个子任务
                MyTask task1 = new MyTask(Arrays.copyOf(source, midIndex));
                task1.fork();
                MyTask task2 = new MyTask(Arrays.copyOfRange(source, midIndex , sourceLen));
                task2.fork();
                // 将两个有序的数组，合并成一个有序的数组
                Integer result1[] = task1.join();
                Integer result2[] = task2.join();
                Integer mer[] = joinInts(result1 , result2);
                return mer;
            }
            // 否则说明集合中只有一个或者两个元素，可以进行这两个元素的比较排序了
            else {
                // 如果条件成立，说明数组中只有一个元素，或者是数组中的元素都已经排列好位置了
                if(sourceLen == 1
                        || source[0] <= source[1]) {
                    return source;
                } else {
                    Integer targetp[] = new Integer[sourceLen];
                    targetp[0] = source[1];
                    targetp[1] = source[0];
                    return targetp;
                }
            }
        }

        private Integer[] joinInts(Integer array1[] , Integer array2[]) {
            Integer destInts[] = new Integer[array1.length + array2.length];
            int array1Len = array1.length;
            int array2Len = array2.length;
            int destLen = destInts.length;

            // 只需要以新的集合destInts的长度为标准，遍历一次即可
            for(int index = 0 , array1Index = 0 , array2Index = 0 ; index < destLen ; index++) {
                int value1 = array1Index >= array1Len?Integer.MAX_VALUE:array1[array1Index];
                int value2 = array2Index >= array2Len?Integer.MAX_VALUE:array2[array2Index];
                // 如果条件成立，说明应该取数组array1中的值
                if(value1 < value2) {
                    array1Index++;
                    destInts[index] = value1;
                }
                // 否则取数组array2中的值
                else {
                    array2Index++;
                    destInts[index] = value2;
                }
            }

            return destInts;
        }


    }

}
