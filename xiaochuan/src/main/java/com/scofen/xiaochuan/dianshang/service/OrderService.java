package com.scofen.xiaochuan.dianshang.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.scofen.xiaochuan.common.datasource.AccessDao;
import com.scofen.xiaochuan.common.datasource.dataObject.AccessDO;
import com.scofen.xiaochuan.common.constant.ConnectorServiceEnum;
import com.scofen.xiaochuan.common.exception.ServiceException;
import com.scofen.xiaochuan.dianshang.controller.OrderParam;
import com.scofen.xiaochuan.dianshang.manager.Manager;
import lombok.extern.slf4j.Slf4j;
import org.dozer.Mapper;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import static com.scofen.xiaochuan.common.exception.ResultCode.DATA_NOT_EXIST;

/**
 * @Description: TODO
 * @Author gaofeng
 * @Date 2023/9/3 10:58 AM
 **/
@Service
@Slf4j
public class OrderService implements ApplicationContextAware {

    @Resource
    AccessDao accessDao;

    @Resource
    Mapper beanMapper;
    private static ApplicationContext applicationContext;


    public List<JSONObject> detailOrder(OrderParam param) {

        Example<AccessDO> example = Example.of(beanMapper.map(param, AccessDO.class));
        AccessDO access = accessDao.findOne(example)
                .orElseThrow(() -> new ServiceException(DATA_NOT_EXIST.getCode(), "current accessKey is invalid！"));

        Manager manager = getBean(ConnectorServiceEnum.getServiceName(param.getConnectorKey()));
        if (manager == null) {
            throw new ServiceException("service is invalid!");
        }
        return (List<JSONObject>) manager.handle("detailOrder", param.getOrderId(), JSON.parseObject(access.getAccessInfo()));
    }


    /**
     * 实现ApplicationContextAware接口，主义applicationContext到静态变量中
     *
     * @param applicationContext
     * @throws BeansException
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        OrderService.applicationContext = applicationContext;
    }


    /**
     * 从静态变量beanFactory中取得Bean, 自动转型为所赋值对象的类型.
     */
    public static <T> T getBean(String beanName) {
        if (applicationContext.containsBean(beanName)) {
            return (T) applicationContext.getBean(beanName);
        } else {
            return null;
        }

    }
}
