package com.processexample.process.controller;

import com.processexample.process.bean.DO.User;
import com.processexample.process.dao.repository.UserRepository;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {

    @Autowired
    UserRepository repository;

    @Test
    public void addOne() throws Exception {
        User user = User.builder()
                .age(17)
                .name("gaofeng")
                .phoneNumber("15111901148")
                .sex("1")
                .build();



    }




}