package com.scofen.study.springboot.controller;


import com.scofen.study.springboot.utils.Result;
import com.scofen.study.springboot.utils.ResultUtil;
import com.scofen.study.springboot.domain.Girl;
import com.scofen.study.springboot.repository.GirlRepository;
import com.scofen.study.springboot.service.GirlService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Create by  GF  in  9:24 2017/9/22
 * Description:
 * Modified  By:
 */

@RestController
@Slf4j
public class GirlController {
    @Autowired
    private GirlRepository girlRepository;
    @Autowired
    private GirlService girlService;


    /**
     *查询所有女生
     * @return
     */
    @GetMapping(value = "/findAll")
    public List<Girl> girlList(){
        log.info("findAll");
        return girlRepository.findAll();
    }

    /**
     * 添加一个女生
     * @param girl
     * @return
     */
    @PostMapping(value = "/addOne")
    public Result<Girl> addOne(@Valid Girl girl, BindingResult bindingResult){

        if (bindingResult.hasErrors()){
            //return null;
            return ResultUtil.error(1,bindingResult.getFieldError().getDefaultMessage());
        }
        girl.setAge(girl.getAge());
        girl.setCupSize(girl.getCupSize());
        return ResultUtil.success(girlRepository.save(girl));
    }

    /**
     * 查询一个女生
     * @param id
     * @return
     */
    @PostMapping(value = "/findOne/{id}")
    public Girl findOne(@PathVariable(value = "id") Integer id){
        return girlRepository.findById(id).get();
    }

    /**
     * 更新一个女生
     * @param id
     * @param cupSize
     * @param age
     * @return
     */
    @PutMapping(value = "/updateOne/{id}")
    public Girl updateOne(@PathVariable(value = "id") Integer id,
                          @RequestParam(value = "cupSize") String cupSize,
                          @RequestParam(value = "age") Integer age){
        Girl girl = new Girl();
        girl.setId(id);
        girl.setAge(age);
        girl.setCupSize(cupSize);
        return girlRepository.save(girl);
    }

    /**
     * 删除一个
     * @param id
     */
    @DeleteMapping(value = "/deleteOne/{id}")
    public void deleteOne(@PathVariable(value = "id") Integer id) {

        girlRepository.deleteById(id);
    }
    //根据年龄查询
    @GetMapping(value = "findByAge/{age}")
    public List<Girl> findByAge(@PathVariable(value = "age") Integer age){
        return girlRepository.findByAge(age);
    }

    @PostMapping(value = "/addTwo")
    public void addTwo(){
        girlService.insertTwo();
    }

    @GetMapping(value = "/getAge/{id}")
    public void getAge(@PathVariable(value = "id") Integer myId)throws Exception{
        girlService.getAge(myId);

    }
}
