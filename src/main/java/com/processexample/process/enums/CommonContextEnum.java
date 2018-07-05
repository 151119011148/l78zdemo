package com.processexample.process.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public enum CommonContextEnum {

    START(Short.parseShort("0")),
    END(Short.parseShort("0")),
    ;

    @Getter
    @Setter
    private Short  index;


}
