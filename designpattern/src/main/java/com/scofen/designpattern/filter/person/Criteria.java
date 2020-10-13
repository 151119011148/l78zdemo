package com.scofen.designpattern.filter.person;

import java.util.List;

/**
 * Create by  GF  in  10:20 2019/3/14
 * Description:
 * 为标准（Criteria）创建一个接口
 * Modified  By:
 */
public interface Criteria {
    public List<Person> meetCriteria(List<Person> persons);
}
