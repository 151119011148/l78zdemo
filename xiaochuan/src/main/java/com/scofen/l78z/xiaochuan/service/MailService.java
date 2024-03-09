package com.scofen.l78z.xiaochuan.service;

import com.scofen.l78z.xiaochuan.controller.request.MailParam;
import com.scofen.l78z.xiaochuan.dao.MailDao;
import com.scofen.l78z.xiaochuan.dao.dataObject.MailDO;
import org.dozer.Mapper;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MailService {

    @Resource
    MailDao mailDao;

    @Resource
    Mapper beanMapper;

    public void send(MailParam param) {
        this.add(param);
        // TODO: 2024/3/9 发送邮件
    }

    public MailDO add(MailParam param) {
        MailDO record = beanMapper.map(param, MailDO.class);
        record.setIsRemoved(0);
        mailDao.save(record);
        return record;
    }


    public List<MailDO> findAllByEmail(String email) {
        MailDO query = new MailDO();
        query.setIsRemoved(0);
        Example<MailDO> example = Example.of(query);
        return mailDao.findAll(example);
    }



}
