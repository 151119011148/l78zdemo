package com.scofen.study.springboot.controller;

import com.scofen.study.springboot.properties.GirlProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Create by  GF  in  21:40 2017/9/21
 * Description:
 * Modified  By:
 */
@RestController
public class HelloController {

/*    @Value("${cupSize}")
    private String cupSize;
    @Value("${age}")
    private String age;
    @Value("${content}")
    private String content;*/

    @Autowired
    private GirlProperties girlProperties;


    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public String say(){
//            return "hello world!" + cupSize;
//            return content;
        return girlProperties.getCupSize() + girlProperties.getAge();
    }
    @GetMapping(value = {"/hi/{id}","hh/{id}"})
    public String say2(@PathVariable Integer id){
 //       return girlProperties.getCupSize() + girlProperties.getAge();
        return "id:" + id;
    }
    @GetMapping(value = {"/say"})
    public String say3(@RequestParam(value = "id",required = false,defaultValue = "1") Integer myId){
        //       return girlProperties.getCupSize() + girlProperties.getAge();
        return "id:" + myId;
    }



}
