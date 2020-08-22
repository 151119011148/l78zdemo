package com.scofen.study.designpattern.adapter.login;


import com.scofen.study.designpattern.utils.Result;

/**
 * Create by  GF  in  19:53 2018/11/19
 * Description:稳定的方法不要去动，直接继承下来
 * Modified  By:
 */
public class LoginForThirdService extends LoginService {

    public Result loginForQQ(String id) {
        //1.id设为全局唯一
        //2.密码默认EMPTY
        //3.调用原来的注册
        Result result = super.regist(id, "EMPTY");
        //4.调用原来的登录
         result = super.login(id, "EMPTY");
        return result;
    }

    public Result loginForWechat(String id) {
        return null;
    }

    public Result loginForToken(String token) {
        return null;
    }

    public Result loginForTelephone(String phoneNumber, String code) {
        return null;
    }

}
