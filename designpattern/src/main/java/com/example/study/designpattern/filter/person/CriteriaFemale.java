package com.scofen.study.designpattern.filter.person;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by  GF  in  10:22 2019/3/14
 * Description:
 * Modified  By:
 */
public class CriteriaFemale implements Criteria {

    @Override
    public List<Person> meetCriteria(List<Person> persons) {
        List<Person> femalePersons = new ArrayList<Person>();
        for (Person person : persons) {
            if(person.getGender().equalsIgnoreCase("FEMALE")){
                femalePersons.add(person);
            }
        }
        return femalePersons;
    }
}
