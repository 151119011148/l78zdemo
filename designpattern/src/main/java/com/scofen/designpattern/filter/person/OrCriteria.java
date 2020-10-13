package com.scofen.designpattern.filter.person;

import java.util.List;

/**
 * Create by  GF  in  10:24 2019/3/14
 * Description:
 * Modified  By:
 */
public class OrCriteria implements Criteria {

    private Criteria criteria;
    private Criteria otherCriteria;

    public OrCriteria(Criteria criteria, Criteria otherCriteria) {
        this.criteria = criteria;
        this.otherCriteria = otherCriteria;
    }

    @Override
    public List<Person> meetCriteria(List<Person> persons) {
        List<Person> firstCriteriaItems = criteria.meetCriteria(persons);
        List<Person> otherCriteriaItems = otherCriteria.meetCriteria(persons);

        //取并集
/*        for (Person person : otherCriteriaItems) {
            if(!firstCriteriaItems.contains(person)){
                firstCriteriaItems.add(person);
            }
        }*/
        firstCriteriaItems.removeAll(otherCriteriaItems);
        firstCriteriaItems.addAll(otherCriteriaItems);

        return firstCriteriaItems;
    }
}
