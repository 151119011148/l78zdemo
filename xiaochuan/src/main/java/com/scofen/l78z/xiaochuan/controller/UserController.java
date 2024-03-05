package com.scofen.l78z.xiaochuan.controller;

import com.scofen.l78z.xiaochuan.common.exception.ResultCode;
import com.scofen.l78z.xiaochuan.common.model.Result;
import com.scofen.l78z.xiaochuan.request.CategoryParam;
import com.scofen.l78z.xiaochuan.request.UserParam;
import com.scofen.l78z.xiaochuan.response.CategoryResponse;
import com.scofen.l78z.xiaochuan.response.Response;
import com.scofen.l78z.xiaochuan.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description: TODO
 * @Author gaofeng
 * @Date 2023/9/3 9:54 AM
 **/
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    UserService userService;


    /**
     * 通过 userId 修改用户详情
     *
     * @return
     */
    @PutMapping("/{userId}")
    public Result<List<CategoryResponse>> edit(@PathVariable String userId, @RequestParam UserParam userParam) {
        userService.editOne(userParam);
        return new Result<>().withData(Boolean.TRUE);
    }

    /**
     * 登录
     *
     * @param
     * @return
     */
    @PostMapping("/login")
    public Response<Boolean> login(@RequestParam UserParam param) {

        if (StringUtils.isEmpty(param.getName())){
            return new Response<>(ResultCode.LOGIN_PASSWORD_IS_NULL.getCode(), "userName is invalid!");
        }
        if (StringUtils.isEmpty(param.getPassword())){
            return new Response<>(ResultCode.LOGIN_PASSWORD_IS_NULL.getCode(), "password is invalid!");
        }
        Boolean result = userService.login(param);
        return new Response<>().withData(result);
    }



}
