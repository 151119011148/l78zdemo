package com.scofen.l78z.xiaochuan.service;

import com.alibaba.fastjson.JSON;
import com.scofen.l78z.xiaochuan.common.exception.ResultCode;
import com.scofen.l78z.xiaochuan.common.exception.ServiceException;
import com.scofen.l78z.xiaochuan.controller.request.BasketParam;
import com.scofen.l78z.xiaochuan.dao.MailDao;
import com.scofen.l78z.xiaochuan.dao.dataObject.BasketDO;
import com.scofen.l78z.xiaochuan.dao.dataObject.MailDO;
import org.dozer.Mapper;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Service
public class MailService {

    @Resource
    MailDao mailDao;

    @Resource
    Mapper beanMapper;

    public MailDO add(BasketParam param) {
        MailDO record = this.get(param.getVisitId());
        if (Objects.isNull(record)) {
            record = beanMapper.map(param, MailDO.class);
            record.setProductIds(JSON.toJSONString(param.getProductIds()));
            record.setIsRemoved(0);
            mailDao.save(record);
        } else {
            Set<String> productIds = new HashSet<>((List<String>) JSON.parseObject(record.getProductIds(), List.class));
            productIds.addAll(param.getProductIds());
            record.setProductIds(JSON.toJSONString(productIds));
            mailDao.save(record);
        }
        return record;
    }


    public MailDO remove(BasketParam param) {
        MailDO record = this.get(param.getVisitId());
        if (Objects.isNull(record)) {
            throw new ServiceException(ResultCode.DATA_NOT_EXIST.getCode(), "current basket is invalid！");
        } else {
            Set<String> productIds = new HashSet<>((List<String>) JSON.parse(record.getProductIds()));
            productIds.removeAll(param.getProductIds());
            record.setProductIds(JSON.toJSONString(productIds));
            mailDao.save(record);
        }
        return record;
    }

    public MailDO get(String email) {
        MailDO query = new MailDO();
        query.setIsRemoved(0);
        Example<MailDO> example = Example.of(query);
        return mailDao.findOne(example).orElse(null);
    }


}
