package com.scofen.study.designpattern.filter.person;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by  GF  in  10:21 2019/3/14
 * Description:
 * 创建实现了 Criteria 接口的实体类
 * Modified  By:
 */
public class CriteriaMale implements Criteria {

    @Override
    public List<Person> meetCriteria(List<Person> persons) {
        List<Person> malePersons = new ArrayList<Person>();
        for (Person person : persons) {
            if(person.getGender().equalsIgnoreCase("MALE")){
                malePersons.add(person);
            }
        }
        return malePersons;
    }
}
