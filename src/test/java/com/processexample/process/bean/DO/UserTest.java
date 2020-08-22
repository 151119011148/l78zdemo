package com.processexample.process.bean.DO;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {

    @Test
    public  void testyyyyMMddHHmmss() {
        String regex = "^(?:(?!0000)[0-9]{4}(?:(?:0[1-9]|1[0-2])(?:0[1-9]|1[0-9]|2[0-8])|(?:0[13-9]|1[0-2])(?:29|30)|(?:0[13578]|1[02])-31)|(?:[0-9]{2}(?:0[48]|[2468][048]|[13579][26])|(?:0[48]|[2468][048]|[13579][26])00)0229)((0[0-9])|(1[0-9])|(2[0-3]))[0-5][0-9][0-5][0-9]{1}$";
        for (int i = 1990; i <= 2120; i++) {
            String dateString = i + "0229235959";
            System.out.println(dateString + " isLegalDate = " + dateString.matches(regex));
        }
    }

}