package com.scofen.designpattern.emptyObject;

/**
 * Create by  GF  in  16:16 2019/3/14
 * Description:
 * Modified  By:
 */
public class NullCustomer extends AbstractCustomer {

    @Override
    public String getName() {
        return "Not Available in Customer Database";
    }

    @Override
    public boolean isNil() {
        return true;
    }
}
