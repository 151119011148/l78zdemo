package com.scofen.l78z.xiaochuan.service;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.scofen.l78z.xiaochuan.common.exception.ResultCode;
import com.scofen.l78z.xiaochuan.common.exception.ServiceException;
import com.scofen.l78z.xiaochuan.controller.request.ProductQueryParam;
import com.scofen.l78z.xiaochuan.controller.response.CategoryVO;
import com.scofen.l78z.xiaochuan.controller.response.ProductVO;
import com.scofen.l78z.xiaochuan.dao.BasketDao;
import com.scofen.l78z.xiaochuan.dao.ProductDao;
import com.scofen.l78z.xiaochuan.dao.dataObject.BasketDO;
import com.scofen.l78z.xiaochuan.dao.dataObject.CategoryDO;
import com.scofen.l78z.xiaochuan.dao.dataObject.ProductDO;
import org.apache.commons.lang3.StringUtils;
import org.dozer.Mapper;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Nullable;
import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Resource
    CategoryService categoryService;

    @Resource
    ProductDao productDao;

    @Resource
    BasketDao basketDao;


    @Resource
    Mapper beanMapper;

    public ProductDO addOne(ProductDO data) {
        data.setProductId("Product_Id_" + UUID.randomUUID().toString().replace("-", ""));
        return productDao.save(data);
    }

    public Boolean removeOne(String productId) {
        ProductDO record = this.get(productId);
        record.setIsRemoved(1);
        productDao.save(record);
        return Boolean.TRUE;
    }

    public ProductDO editOne(ProductDO update) {
        ProductDO record = this.get(update.getProductId());
        record.update(update);
        record.setIsRemoved(0);
        return productDao.save(record);
    }

    private ProductDO get(String productId) {
        ProductVO param = new ProductVO();
        param.setProductId(productId);
        Example<ProductDO> example = Example.of(beanMapper.map(param, ProductDO.class));
        example.getProbe().setIsRemoved(0);
        ProductDO product = productDao.findOne(example).orElseThrow(() -> new ServiceException(ResultCode.USER_NOT_EXIST.getCode(), "current product is invalid！"));
        return product;
    }


    /**
     * 模糊搜索
     *
     * @param productId
     * @param searchKey
     * @return
     */
    public List<ProductVO> search(@Nullable String productId, @Nullable String searchKey) {
        if (StringUtils.isNotEmpty(productId)) {
            return this.findById(productId)
                    .parallelStream()
                    .map(this::fillInfo)
                    .collect(Collectors.toList());
        }
        if (StringUtils.isNotEmpty(searchKey)) {
            return productDao.findTitleByFuzzy(searchKey)
                    .parallelStream()
                    .map(this::fillInfo)
                    .collect(Collectors.toList());
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


    public Page<ProductVO> page(ProductQueryParam param) {
        PageRequest pageRequest = new PageRequest(param.getPageIndex(), param.getPageSize());
        //查询购物车商品信息
        if (StringUtils.equals(param.getQueryKey(), "in_basket")) {
            BasketDO basketParam = new BasketDO();
            basketParam.setVisitId(param.getQueryValue());
            Example<BasketDO> example = Example.of(basketParam);
            List<ProductVO> result = basketDao.findOne(example)
                    .map(basket -> ((List<String>) JSON.parse(basket.getProductIds()))
                            .parallelStream()
                            .map(productId -> this.findOneById(productId))
                            .collect(Collectors.toList()))
                    .orElse(Lists.newArrayList());
            return new PageImpl(result);
        }
        ProductDO condition = new ProductDO();
        //条件分页查询
        if (StringUtils.equals(param.getQueryKey(), "category_id")) {
            condition.setCategoryId(param.getQueryValue());
        }
        Example<ProductDO> example = Example.of(condition);
        example.getProbe().setIsRemoved(0);

        Page<ProductDO> pageRecord = productDao.findAll(example, pageRequest);

        Page<ProductVO> pageResult = new PageImpl(pageRecord.getContent().parallelStream()
                .map(this::fillInfo)
                .collect(Collectors.toList()), pageRecord.getPageable(), pageRecord.getTotalElements());
        return pageResult;
    }

    private ProductVO fillInfo(ProductDO product) {
        ProductVO result = ProductVO.read4(product);
        if (StringUtils.isNotEmpty(product.getCategoryId())){
            CategoryDO category = categoryService.findByCategoryIdIn(Lists.newArrayList(product.getCategoryId())).get(0);
            result.setCategory(beanMapper.map(category, CategoryVO.class));
        }
        if (StringUtils.isNotEmpty(product.getRelatedCategoryId())){
            result.setRelatedCategory(categoryService
                    .findByCategoryIdIn((List<String>) JSON.parseObject(product.getRelatedCategoryId(), List.class))
                    .stream()
                    .map(category -> beanMapper.map(category, CategoryVO.class))
                    .collect(Collectors.toList()));
        }
        return result;
    }

    public ProductVO findOneById(String productId) {
        ProductVO param = new ProductVO();
        param.setProductId(productId);
        Example<ProductDO> example = Example.of(beanMapper.map(param, ProductDO.class));
        example.getProbe().setIsRemoved(0);
        ProductDO record = productDao.findOne(example).orElseThrow(() -> new ServiceException(ResultCode.USER_NOT_EXIST.getCode(), "current product is invalid！"));
        return this.fillInfo(record);
    }


}
