package com.scofen.algorithms.pratise.sort;

import lombok.Data;

/**
 * @author 高锋
 * @className: Sort
 * @description: TODO
 * @date 2019/11/716:53
 */
@Data
public abstract class Sort {

    //时间复杂度
    private int count ;

    public abstract Integer[]  sort(Integer[] source);

    int statistical(int init){
        if (init < 1){
            count =  1;
        }else {
            count = count + 1;
        }
        return count;
    };

}
