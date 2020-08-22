package com.scofen.study.springboot.domain;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Create by  GF  in  22:38 2017/9/21
 * Description:
 * Modified  By:
 */
@Entity
@Data

public class Girl {
    @Id
    @GeneratedValue

    private Integer id;
    private String cupSize;
    @Min(value = 18,message = "未成年不准入内")
    private Integer age;
    @NotNull(message = "金额必传")
    private Double money;

    public Girl() {
    }
}
