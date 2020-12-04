package com.scofen.algorithms.study.sort;

/**
 * @author 高锋
 * @className: Sort
 * @description: TODO
 * @date 2020/10/1613:38
 */
public interface Sort<T extends Comparable> {

    public boolean less(T a, T b);

    public void exchange(T[] target, int i, int j);

    public boolean isSorted(T[] t);

    public int statistical(int init);

    public T[] reverse(T[] target);

    public T[] sort(T[] target);

    public T[] sort(T[] target, boolean asc);

    public int rank(T t);

}
