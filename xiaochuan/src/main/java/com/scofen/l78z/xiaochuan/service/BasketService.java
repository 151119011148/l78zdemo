package com.scofen.l78z.xiaochuan.service;

import com.alibaba.fastjson.JSON;
import com.scofen.l78z.xiaochuan.common.exception.ResultCode;
import com.scofen.l78z.xiaochuan.common.exception.ServiceException;
import com.scofen.l78z.xiaochuan.controller.request.BasketParam;
import com.scofen.l78z.xiaochuan.dao.dataObject.BasketDO;
import com.scofen.l78z.xiaochuan.dao.BasketDao;
import org.dozer.Mapper;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Service
public class BasketService {

    @Resource
    BasketDao basketDao;

    @Resource
    Mapper beanMapper;

    public Boolean add(BasketParam param) {
        BasketDO record = this.get(param.getVisitId());
        if (Objects.isNull(record)) {
            basketDao.save(beanMapper.map(param, BasketDO.class));
        } else {
            Set<String> productIds = new HashSet<>((List<String>) JSON.parse(record.getProductIds()));
            productIds.addAll(param.getProductIds());
            record.setProductIds(JSON.toJSONString(productIds));
            basketDao.save(record);
        }
        return true;
    }


    public Boolean remove(BasketParam param) {
        BasketDO record = this.get(param.getVisitId());
        if (Objects.isNull(record)) {
            throw new ServiceException(ResultCode.DATA_NOT_EXIST.getCode(), "current basket is invalid！");
        } else {
            Set<String> productIds = new HashSet<>((List<String>) JSON.parse(record.getProductIds()));
            productIds.removeAll(param.getProductIds());
            record.setProductIds(JSON.toJSONString(productIds));
            basketDao.save(record);
            
        }
        return true;
    }

    public BasketDO get(String visitId) {
        BasketDO query = new BasketDO();
        query.setVisitId(visitId);
        Example<BasketDO> example = Example.of(query);
        return basketDao.findOne(example).orElse(null);
    }


}
