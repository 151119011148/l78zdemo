package com.scofen.study.designpattern.emptyObject;

/**
 * Create by  GF  in  16:16 2019/3/14
 * Description:
 * Modified  By:
 */
public class CustomerFactory {

    public static final String[] names = {"Rob", "Joe", "Julie"};

    public static AbstractCustomer getCustomer(String name){
        for (int i = 0; i < names.length; i++) {
            if (names[i].equalsIgnoreCase(name)){
                return new RealCustomer(name);
            }
        }
        return new NullCustomer();
    }
}
