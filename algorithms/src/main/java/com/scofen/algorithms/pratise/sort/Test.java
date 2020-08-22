package com.scofen.algorithms.pratise.sort;


import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

/**
 * @author 高锋
 * @className: Test
 * @description: TODO
 * @date 2019/11/716:50
 */
public class Test {

    private static Integer[] init(){
        Set<Integer> set = Sets.newHashSet();
        for (int i = 0; i< 100; i ++){
            set.add((int)(Math.random()*1000));
        }
        return set.toArray(new Integer[0]);
    }
    private static Integer[] source = init();

    @org.junit.Test
    public void bubbleSortTest(){
        BubbleSort bubble = new BubbleSort();
        System.out.println(Lists.newArrayList(bubble.sort(source)));
        System.out.println("bubble sorting complexity is : " + bubble.getCount());
    }

    @org.junit.Test
    public void selectionSortTest(){
        SelectionSort selection = new SelectionSort();
        System.out.println(Lists.newArrayList(selection.sort(source)));
        System.out.println("selection sorting complexity is : " + selection.getCount());
    }

    @org.junit.Test
    public void insertionSortTest(){
        InsertionSort insertion = new InsertionSort();
        System.out.println(Lists.newArrayList(insertion.sort(source)));
        System.out.println("insertion sorting complexity is : " + insertion.getCount());
    }

    @org.junit.Test
    public void quickSortTest(){
        QuickSort quick = new QuickSort();
        System.out.println(Lists.newArrayList(quick.sort(source, 0, source.length - 1)));
        System.out.println("quick sorting complexity is : " + quick.getCount());
    }

    @org.junit.Test
    public void mergeSortTest(){
        MergeSort merge = new MergeSort();
        System.out.println(Lists.newArrayList(merge.sort(source)));
        System.out.println("merge sorting complexity is : " + merge.getCount());
    }

    @org.junit.Test
    public void mergeSortTest2(){
        ForkJoinPool pool = new ForkJoinPool();
        MergeForkJoin.MyTask task = new MergeForkJoin.MyTask(source);
        ForkJoinTask<Integer[]> taskResult = pool.submit(task);
        try {
            taskResult.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace(System.out);
        }
        List list = Lists.newArrayList(task.getSource());
        System.out.println(list);
    }

}
