package com.scofen.l78z.xiaochuan.service;

import com.scofen.l78z.xiaochuan.common.exception.ResultCode;
import com.scofen.l78z.xiaochuan.common.exception.ServiceException;
import com.scofen.l78z.xiaochuan.controller.request.CategoryParam;
import com.scofen.l78z.xiaochuan.controller.request.ProductParam;
import com.scofen.l78z.xiaochuan.controller.request.ProductQueryParam;
import com.scofen.l78z.xiaochuan.dao.ProductDao;
import com.scofen.l78z.xiaochuan.dao.dataObject.CategoryDO;
import com.scofen.l78z.xiaochuan.dao.dataObject.ProductDO;
import org.dozer.Mapper;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import javax.annotation.Nullable;
import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

@Service
public class ProductService {

    @Resource
    ProductDao productDao;

    @Resource
    Mapper beanMapper;

    public ProductDO addOne(ProductParam param) {
        ProductDO record = beanMapper.map(param, ProductDO.class);
        record.setCategoryId("Product_Id_" + UUID.randomUUID().toString().replace("-", ""));
        return productDao.save(record);
    }

    public Boolean removeOne(String productId) {
        ProductDO record = this.getOne(productId);
        record.setIsRemoved(1);
        productDao.save(record);
        return Boolean.TRUE;
    }

    public Boolean editOne(ProductParam param) {
        ProductDO record = beanMapper.map(param, ProductDO.class);
        productDao.save(record);
        return Boolean.TRUE;
    }

    public ProductDO getOne(ProductParam param) {
        Example<ProductDO> example = Example.of(beanMapper.map(param, ProductDO.class));
        example.getProbe().setIsRemoved(0);
        return productDao.findOne(example).orElseThrow(() -> new ServiceException(ResultCode.USER_NOT_EXIST.getCode(), "current product is invalid！"));
    }

    public ProductDO getOne(String productId) {
        ProductParam param = new ProductParam();
        param.setProductId(productId);
        Example<ProductDO> example = Example.of(beanMapper.map(param, ProductDO.class));
        example.getProbe().setIsRemoved(0);
        return productDao.findOne(example).orElseThrow(() -> new ServiceException(ResultCode.USER_NOT_EXIST.getCode(), "current product is invalid！"));
    }

    /**
     * 模糊搜索
     * @param productId
     * @param searchKey
     * @return
     */
    public List<ProductDO> search(@Nullable String productId, @Nullable String searchKey) {
        return null;
    }


    public List<ProductDO> page(ProductQueryParam param) {
        return null;
    }
}
