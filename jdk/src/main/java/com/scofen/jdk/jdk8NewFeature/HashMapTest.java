package com.scofen.jdk.jdk8NewFeature;

import java.util.HashMap;

/**
 * Create by  GF  in  14:33 2019/1/14
 * Description:
 * 为何HashMap重写equals方法需同时重写hashCode方法？
 * Modified  By:
 */
public class HashMapTest {
    private static class Person{
        int idCard;
        String name;

        public Person(int idCard, String name) {
            this.idCard = idCard;
            this.name = name;
        }
        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()){
                return false;
            }
            Person person = (Person) o;
            //两个对象是否等值，通过idCard来确定
            return this.idCard == person.idCard;
        }

    }

    public static void main(String []args){
        HashMap<Person,String> map = new HashMap<>();
        Person origKey = new Person(1234,"乔峰");
        //put到hashmap中去
        System.out.println("origKey的hash值为：" + origKey.hashCode());
        map.put(origKey,"天龙八部");
        //get取出，从逻辑上讲应该能输出“天龙八部”
        Person newKey = new Person(1234,"萧峰");
        System.out.println("newKey的hash值为："  + newKey.hashCode());
        System.out.println("newKey的结果:"+map.get(newKey));
        System.out.println("origKey的结果:"+map.get(origKey));
    }
}
