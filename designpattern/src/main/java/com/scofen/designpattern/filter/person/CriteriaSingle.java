package com.scofen.designpattern.filter.person;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by  GF  in  10:22 2019/3/14
 * Description:
 * Modified  By:
 */
public class CriteriaSingle implements Criteria {

    @Override
    public List<Person> meetCriteria(List<Person> persons) {
        List<Person> singlePersons = new ArrayList<Person>();
        for (Person person : persons) {
            if(person.getMaritalStatus().equalsIgnoreCase("SINGLE")){
                singlePersons.add(person);
            }
        }
        return singlePersons;
    }
}
