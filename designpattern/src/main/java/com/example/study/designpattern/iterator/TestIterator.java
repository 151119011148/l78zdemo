package com.scofen.study.designpattern.iterator;

/**
 * Create by  GF  in  14:07 2019/3/14
 * Description:
 * Modified  By:
 */
public class TestIterator {

    public static void main(String[] args) {
        NameRepository namesRepository = new NameRepository();

        for(Iterator iterator = namesRepository.getIterator(); iterator.hasNext();){
            String name = (String)iterator.next();
            System.out.println("Name : " + name);
        }

        Iterator iterator = namesRepository.getIterator();
        while (iterator.hasNext()){
            String name = (String)iterator.next();
            System.out.println("Name : " + name);
        }


    }

}
