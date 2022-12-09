package com.scofen.designpattern.filter.person;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Description: TODO
 * @Author gaofeng
 * @Date 7/9/22 1:06 PM
 **/
@Data
@AllArgsConstructor
public class Person {

    private String name;
    private String gender;
    private String maritalStatus;
}
