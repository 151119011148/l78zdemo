package com.scofen.designpattern.filter.person;

import java.util.List;

/**
 * @Description: TODO
 * @Author gaofeng
 * @Date 7/9/22 7:49 PM
 **/
public class OrCriteria implements Criteria {
    public OrCriteria(Criteria single, Criteria female) {

    }

    @Override
    public List<Person> meetCriteria(List<Person> persons) {
        return null;
    }
}