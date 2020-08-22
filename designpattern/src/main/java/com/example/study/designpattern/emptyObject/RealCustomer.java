package com.scofen.study.designpattern.emptyObject;

/**
 * Create by  GF  in  16:15 2019/3/14
 * Description:
 * Modified  By:
 */
public class RealCustomer extends AbstractCustomer {

    public RealCustomer(String name) {
        super(name);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean isNil() {
        return false;
    }
}
