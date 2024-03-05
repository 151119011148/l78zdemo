package com.scofen.l78z.xiaochuan.wuliu.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.scofen.l78z.xiaochuan.common.constant.ConnectorServiceEnum;
import com.scofen.l78z.xiaochuan.common.datasource.AccessDao;
import com.scofen.l78z.xiaochuan.common.datasource.dataObject.AccessDO;
import com.scofen.l78z.xiaochuan.common.exception.ServiceException;
import com.scofen.l78z.xiaochuan.dianshang.manager.Manager;
import com.scofen.l78z.xiaochuan.wuliu.controller.WuLiuParam;
import org.apache.commons.lang3.StringUtils;
import org.dozer.Mapper;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description: TODO
 * @Author gaofeng
 * @Date 2023/9/3 10:58 AM
 **/
@Service
public class WuliuService implements ApplicationContextAware {

    @Resource
    AccessDao accessDao;

    @Resource
    Mapper beanMapper;
    private static ApplicationContext applicationContext;


    public List<JSONObject> trace(WuLiuParam param) {
        preHandle(param);
        Example<AccessDO> example = Example.of(beanMapper.map(param, AccessDO.class));
        AccessDO access = accessDao.findOne(example)
                .orElseThrow(() -> new ServiceException(""));

        Manager manager = getBean(ConnectorServiceEnum.getServiceName(param.getConnectorKey()));
        if (manager == null) {
            throw new ServiceException("service is invalid!");
        }
        return (List<JSONObject>) manager.handle("trace", param, JSON.parseObject(access.getAccessInfo()));
    }

    private void preHandle(WuLiuParam param) {
        if (StringUtils.isNotEmpty(param.getConnectorKey())){
            return;
        }
        String logistics_name = param.getLogisticsName();
//        if (StringUtils.isEmpty(logistics_name) || LogisticsEnum.get()){
//
//        }

    }


    /**
     * 实现ApplicationContextAware接口，主义applicationContext到静态变量中
     *
     * @param applicationContext
     * @throws BeansException
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        WuliuService.applicationContext = applicationContext;
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
