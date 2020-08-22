package com.scofen.study.designpattern.iterator;

/**
 * Create by  GF  in  14:05 2019/3/14
 * Description:
 * 创建实现了 Container 接口的实体类。
 * 该类有实现了 Iterator 接口的内部类 NameIterator。
 * Modified  By:
 */
public class NameRepository implements Container {

    private String names[] = {"Robert" , "John" ,"Julie" , "Lora"};

    @Override
    public Iterator getIterator() {
        return new NameIterator();
    }

    private class NameIterator implements Iterator {

        int index;

        @Override
        public boolean hasNext() {
            return index < names.length;
        }

        @Override
        public Object next() {
            if(this.hasNext()){
                return names[index++];
            }
            return null;
        }
    }
}
