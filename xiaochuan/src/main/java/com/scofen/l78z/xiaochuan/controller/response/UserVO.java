package com.scofen.l78z.xiaochuan.controller.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserVO implements  Serializable {

    /**
     *
     */
    private String userId;

    /**
     *
     */
    private String name;

    /**
     *
     */
    private String password;
}
