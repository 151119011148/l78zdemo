package com.scofen.study.springboot.service;



import com.scofen.study.springboot.domain.Girl;
import com.scofen.study.springboot.enums.ResultEnum;
import com.scofen.study.springboot.exception.GirlException;
import com.scofen.study.springboot.repository.GirlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Create by  GF  in  10:31 2017/9/22
 * Description:
 * Modified  By:
 */
@Service
public class GirlService {
    @Autowired
    private GirlRepository girlRepository;
    @Transactional
    public void insertTwo(){
        Girl girla = new Girl();
        girla.setCupSize("C");
        girla.setAge(18);
        Girl girlb = new Girl();
        girlb.setAge(16);
//        girlb.setCupSize("BBBBBB");
        girlb.setCupSize("B");
        girlRepository.save(girla);
        girlRepository.save(girlb);
    }

    public void getAge(Integer id)throws Exception{
        Optional<Girl> girl = girlRepository.findById(id);
        Integer age = girl.get().getAge();
        if (age < 10){
            throw new GirlException(ResultEnum.PRIMARY_SCHOOL);
        }else if (age > 10 && age < 16){
            throw new GirlException(ResultEnum.MIDDLE_SCHOOL);
        }
    }

    /**
     * 通过id查询一个女人
     * @param id
     * @return
     */
    public Girl findOne(Integer id){
        return girlRepository.findById(id).get();
    }



}
