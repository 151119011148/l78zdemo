package com.scofen.l78z.xiaochuan.controller.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserParam implements Serializable {

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
