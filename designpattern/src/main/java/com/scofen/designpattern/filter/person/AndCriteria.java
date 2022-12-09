package com.scofen.designpattern.filter.person;

import java.util.List;

/**
 * @Description: TODO
 * @Author gaofeng
 * @Date 7/9/22 7:48 PM
 **/
public class AndCriteria implements Criteria {

    public AndCriteria(Criteria single, Criteria male) {

    }

    @Override
    public List<Person> meetCriteria(List<Person> persons) {
        return null;
    }
}