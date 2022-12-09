package com.scofen.designpattern.adapter.login;

import com.scofen.designpattern.utils.Result;

/**
 * @Description: TODO
 * @Author gaofeng
 * @Date 7/9/22 12:01 PM
 **/
public abstract class LoginService {

    public abstract Result loginForQQ(String id);

    public Result loginForWechat(String id) {
        return null;
    }

    public Result loginForToken(String token) {
        return null;
    }

    public Result loginForTelephone(String phoneNumber, String code) {
        return null;
    }

    protected Result regist(String id, String empty){
        return null;
    }

    protected  Result login(String id, String empty){
        return null;
    }
}
