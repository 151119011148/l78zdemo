package com.scofen.designpattern.adapter.login;


import com.scofen.designpattern.utils.Result;
import com.scofen.designpattern.utils.ResultUtil;

/**
 * Create by  GF  in  19:48 2018/11/19
 * Description:
 * Modified  By:
 */
public class LoginService {

    /**
     * 注册和登录方法已经用了很久，很稳定
     * @param userName
     * @param password
     * @return
     */
    public Result regist(String userName, String password){
        return ResultUtil.success();

    }
    public Result login(String userName, String password){
        return ResultUtil.success();

    }

}
