package com.scofen.study.springboot.enums;

public enum ResultEnum {
    UNKOWNW_ERROR(-1,"未知错误"),
    SUCCESS(0,"成功"),
    PRIMARY_SCHOOL(100,"还在读小学"),
    MIDDLE_SCHOOL(101,"还在读初中"),
    ;

    private Integer code;
    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
