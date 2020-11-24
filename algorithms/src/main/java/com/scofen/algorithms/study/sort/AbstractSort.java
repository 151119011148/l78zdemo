package com.scofen.algorithms.study.sort;

import lombok.Data;

import java.util.Arrays;

/**
 * @author 高锋
 * @className: AbstractSort
 * @description: TODO
 * @date 2019/11/716:53
 */
@Data
public abstract class AbstractSort<T extends Comparable> implements Sort<T> {

    //时间复杂度
    public int count = 0;

    public T[] source;

    public T[] target;

    public void setSource(T[] source) {
        this.source = source;
        this.target = Arrays.copyOf(source, source.length);
    }

    @Override
    public boolean less(T a, T b){
        return a.compareTo(b) < 0;
    }

    @Override
    public void exchange(T[] target, int i, int j){
        T a = target[i];
        target[i] = target[j];
        target[j] = a;
        count = statistical(count);
    }


    @Override
    public boolean isSorted(T[] t){
       return isSorted(t, true);
    }

    public boolean isSorted(T[] t, boolean asc){
        if(asc){
            for (int i = 1; i < t.length; i ++){
                if (less(t[i], t[i - 1])){
                    return false;
                }
            }
            return true;
        }else {
            for (int i = 1; i < t.length; i ++){
                if (! less(t[i], t[i - 1])){
                    return false;
                }
            }
            return true;
        }

    }

    @Override
    public T[] reverse(T[] t){

        if (isSorted(t)){
            for (int i = 0; i < t.length/2 ; i ++){
                exchange(t, i, t.length - 1 - i);
            }
        }
        return t;
    }

    @Override
    public T[] sort(T[] target){
        setSource(target);
        return sort(target, true);
    }

    @Override
    public int statistical(int init){
        if (init < 1){
            count =  1;
        }else {
            count = count + 1;
        }
        return count;
    };

}
