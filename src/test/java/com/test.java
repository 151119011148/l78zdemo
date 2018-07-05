package com;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Create by  GF  in  15:56 2018/6/28
 * Description:
 * 
 * Modified  By:
 */
public class test {
    public static void main(String args[]){

        List<BigDecimal> list = Arrays.asList( new BigDecimal(1),
                new BigDecimal(2),
                new BigDecimal(3),
                new BigDecimal(2),
                new BigDecimal(5),
                new BigDecimal(4),
                new BigDecimal(6));

        System.out.println(list);

        Comparator<BigDecimal> bigDecimalComparator = BigDecimal :: compareTo;

        List<BigDecimal> newList = list.stream()
                .sorted(bigDecimalComparator.reversed())
                .collect(Collectors.toCollection(ArrayList::new));
        System.out.println(newList);


        list.sort(( o1,  o2) -> o1.compareTo(o2));
        System.out.println(list);

    }
}
