package com.scofen.study.designpattern.prototype.deep;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Create by  GF  in  19:59 2018/8/14
 * Description: 猴子
 * Modified  By:
 */
@Data
public class Monkey implements Serializable{

    protected int height;

    protected int weight;

    protected Date birthday;

    transient int transientValue;



}
