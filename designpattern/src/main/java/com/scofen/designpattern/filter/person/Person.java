package com.scofen.designpattern.filter.person;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Create by  GF  in  10:20 2019/3/14
 * Description:
 * 创建一个类，在该类上应用标准
 * Modified  By:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    private String name;
    private String gender;
    private String maritalStatus;
}
