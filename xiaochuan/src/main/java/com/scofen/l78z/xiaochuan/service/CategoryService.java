package com.scofen.l78z.xiaochuan.service;

import com.google.common.collect.Lists;
import com.scofen.l78z.xiaochuan.common.exception.ResultCode;
import com.scofen.l78z.xiaochuan.common.exception.ServiceException;
import com.scofen.l78z.xiaochuan.controller.response.CategoryVO;
import com.scofen.l78z.xiaochuan.dao.CategoryDao;
import com.scofen.l78z.xiaochuan.dao.dataObject.CategoryDO;
import com.scofen.l78z.xiaochuan.controller.request.CategoryParam;
import com.scofen.l78z.xiaochuan.dao.dataObject.ProductDO;
import org.apache.commons.lang3.StringUtils;
import org.dozer.Mapper;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Stack;
import java.util.UUID;

@Service
public class CategoryService {

    @Resource
    CategoryDao categoryDao;

    @Resource
    Mapper beanMapper;

    public CategoryDO addOne(CategoryParam param) {
        String parentId = param.getParentId();
        int level = 0;
        if (StringUtils.isNotBlank(parentId)){
            CategoryDO parent = this.getParent(parentId);
            level = parent.getLevel() + 1;
        }
        CategoryDO record = beanMapper.map(param, CategoryDO.class);
        record.setLevel(level);
        record.setCategoryId("CategoryId_" + UUID.randomUUID().toString().replace("-", ""));
        record.setIsRemoved(0);
        return categoryDao.save(record);
    }

    public Boolean removeOne(String categoryId) {
        CategoryDO record = this.getOne(categoryId);

        List<CategoryDO> data = Lists.newArrayList();
        Stack<CategoryDO> stack = new Stack<>();
        stack.add(record);
        data.add(record);
        while (!stack.empty()){
            String parentId = stack.pop().getCategoryId();
            this.listChildren(parentId).forEach(item -> {
                stack.add(item);
                data.add(item);
            });
        }
        data.forEach(item -> {
            item.setIsRemoved(1);
            categoryDao.save(item);
        });
        return Boolean.TRUE;
    }

    public Boolean editOne(CategoryParam param) {

        CategoryDO record = this.getOne(param.getCategoryId());
        record.update(beanMapper.map(param, CategoryDO.class));
        record.setIsRemoved(0);
        String parentId = record.getParentId();
        int level = 0;
        if (StringUtils.isNotBlank(parentId)){
            CategoryDO parent = this.getParent(parentId);
            level = parent.getLevel() + 1;
        }
        record.setLevel(level);
        categoryDao.save(record);
        return Boolean.TRUE;
    }

    public CategoryDO getOne(CategoryParam param) {
        Example<CategoryDO> example = Example.of(beanMapper.map(param, CategoryDO.class));
        example.getProbe().setIsRemoved(0);
        return categoryDao.findOne(example).orElseThrow(() -> new ServiceException(ResultCode.USER_NOT_EXIST.getCode(), "current category is invalid！"));
    }

    public CategoryDO getOne(String categoryId) {
        CategoryParam param = new CategoryParam();
        param.setCategoryId(categoryId);
        Example<CategoryDO> example = Example.of(beanMapper.map(param, CategoryDO.class));
        example.getProbe().setIsRemoved(0);
        // TODO: 2024/3/6 递归逻辑
        return categoryDao.findOne(example).orElseThrow(() -> new ServiceException(ResultCode.USER_NOT_EXIST.getCode(), "current category is invalid！"));
    }

    public CategoryDO getParent(String parentId) {
        return this.getOne(parentId);
    }

    public List<CategoryDO> listChildren(String parentId) {
        CategoryParam param = new CategoryParam();
        param.setParentId(parentId);
        return this.list(param);
    }

    public List<CategoryDO> list(CategoryParam param) {

        Example<CategoryDO> example = Example.of(beanMapper.map(param, CategoryDO.class));
        example.getProbe().setIsRemoved(0);
        return categoryDao.findAll(example);
    }




}
