package com.processexample.process.controller;

import com.processexample.process.bean.DO.User;
import com.processexample.process.bean.response.Result;
import com.processexample.process.dao.repository.UserRepository;
import com.processexample.process.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Create by  GF  in  15:56 2018/6/20
 * Description:
 * Modified  By:
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;
    /**
     * 添加一个user
     * @param user
     * @return
     */
    @PostMapping(value = "/addOne")
    public Result<User> addOne(@Valid User user, BindingResult bindingResult){

        if (bindingResult.hasErrors()){
            //return null;
            return ResultUtil.error(1,bindingResult.getFieldError().getDefaultMessage());
        }
        return ResultUtil.success(userRepository.save(user));
    }
}
