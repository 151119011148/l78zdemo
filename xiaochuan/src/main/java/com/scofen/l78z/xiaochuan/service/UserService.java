package com.scofen.l78z.xiaochuan.service;

import com.scofen.l78z.xiaochuan.common.exception.ResultCode;
import com.scofen.l78z.xiaochuan.common.exception.ServiceException;
import com.scofen.l78z.xiaochuan.controller.request.CategoryParam;
import com.scofen.l78z.xiaochuan.dao.UserDao;
import com.scofen.l78z.xiaochuan.dao.dataObject.CategoryDO;
import com.scofen.l78z.xiaochuan.dao.dataObject.UserDO;
import com.scofen.l78z.xiaochuan.controller.request.UserParam;
import org.dozer.Mapper;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserService{

    @Resource
    UserDao userDao;

    @Resource
    Mapper beanMapper;

    public UserDO login(UserParam param) {

        Example<UserDO> example = Example.of(beanMapper.map(param, UserDO.class));
        example.getProbe().setIsRemoved(0);
        UserDO user = userDao.findOne(example)
                .orElseThrow(() -> new ServiceException(ResultCode.USER_NOT_EXIST.getCode(), "current user is invalid！"));
        return user;
    }


    public UserDO editOne(UserParam param) {
        UserDO record = this.getOne(param.getUserId());
        record.update(beanMapper.map(param, UserDO.class));
        record.setIsRemoved(0);
        return userDao.save(record);
    }

    public UserDO getOne(String userId) {
        UserParam param = new UserParam();
        param.setUserId(userId);
        Example<UserDO> example = Example.of(beanMapper.map(param, UserDO.class));
        example.getProbe().setIsRemoved(0);
        return userDao.findOne(example).orElseThrow(() -> new ServiceException(ResultCode.USER_NOT_EXIST.getCode(), "current user is invalid！"));
    }
}
