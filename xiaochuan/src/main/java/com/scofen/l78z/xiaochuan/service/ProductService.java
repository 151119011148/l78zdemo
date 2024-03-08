package com.scofen.l78z.xiaochuan.service;

import com.scofen.l78z.xiaochuan.common.exception.ResultCode;
import com.scofen.l78z.xiaochuan.common.exception.ServiceException;
import com.scofen.l78z.xiaochuan.controller.request.ProductQueryParam;
import com.scofen.l78z.xiaochuan.controller.response.ProductVO;
import com.scofen.l78z.xiaochuan.dao.ProductDao;
import com.scofen.l78z.xiaochuan.dao.dataObject.ProductDO;
import com.scofen.l78z.xiaochuan.dao.dataObject.UserDO;
import org.apache.commons.lang3.StringUtils;
import org.dozer.Mapper;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

    public ProductDO addOne(ProductDO data) {
        data.setProductId("Product_Id_" + UUID.randomUUID().toString().replace("-", ""));
        return productDao.save(data);
    }

    public Boolean removeOne(String productId) {
        ProductDO record = this.getById(productId);
        record.setIsRemoved(1);
        productDao.save(record);
        return Boolean.TRUE;
    }

    public ProductDO editOne(ProductDO update) {
        ProductDO record = this.getById(update.getProductId());
        record.update(update);
        record.setIsRemoved(0);
        return productDao.save(record);
    }


    public ProductDO getById(String productId) {
        ProductVO param = new ProductVO();
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
        if (StringUtils.isNotEmpty(productId)){
            return this.findById(productId);
        }
        if (StringUtils.isNotEmpty(searchKey)){
            return productDao.findTitleByFuzzy(searchKey);
        }
        return null;
    }

    private List<ProductDO> findById(String productId) {
        ProductDO param = new ProductDO();
        param.setProductId(productId);
        Example<ProductDO> example = Example.of(param);
        example.getProbe().setIsRemoved(0);
        return productDao.findAll(example);
    }


    public Page<ProductDO> page(ProductQueryParam param) {
        PageRequest pageRequest = new PageRequest(param.getPageIndex(), param.getPageSize());
        //查询购物车商品信息
        if (StringUtils.equals(param.getQueryKey(), "in_basket")){
            return null;
        }
        ProductDO condition = new ProductDO();
        //条件分页查询
        if (StringUtils.equals(param.getQueryKey(), "category_id")){
            condition.setCategoryId(param.getQueryValue());
        }
        Example<ProductDO> example = Example.of(condition);
        example.getProbe().setIsRemoved(0);

        Page<ProductDO> pageResult = productDao.findAll(example, pageRequest);
        return pageResult;
    }
}
