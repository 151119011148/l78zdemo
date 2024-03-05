package com.scofen.l78z.xiaochuan.controller;

import com.alibaba.fastjson.JSONObject;
import com.scofen.l78z.xiaochuan.common.model.Result;
import com.scofen.l78z.xiaochuan.dao.dataObject.CategoryDO;
import com.scofen.l78z.xiaochuan.request.CategoryParam;
import com.scofen.l78z.xiaochuan.response.CategoryResponse;
import com.scofen.l78z.xiaochuan.service.CategoryService;
import com.scofen.l78z.xiaochuan.util.TreeUtils;
import com.scofen.l78z.xiaochuan.wuliu.controller.WuLiuParam;
import com.scofen.l78z.xiaochuan.wuliu.service.WuliuService;
import org.apache.commons.lang3.StringUtils;
import org.dozer.Mapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

import static com.scofen.l78z.xiaochuan.common.constant.ConnectorServiceEnum.KUAI_DI_100;

/**
 * @Description: TODO
 * @Author gaofeng
 * @Date 2023/9/3 9:54 AM
 **/
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Resource
    CategoryService categoryService;

    @Resource
    Mapper beanMapper;

    /**
     * 新增类目
     *
     * @param categoryParam
     * @return
     */
    @PostMapping("")
    public Result<CategoryResponse> add(@RequestParam CategoryParam categoryParam) {
        categoryService.addOne(categoryParam);
        return new Result<>().withData(Boolean.TRUE);
    }

    /**
     * 通过 categoryId 删除类目
     *
     * @param categoryId
     * @return
     */
    @DeleteMapping("/{categoryId}")
    public Result<CategoryResponse> add(@PathVariable String categoryId) {
        categoryService.removeOne(categoryId);
        return new Result<>().withData(Boolean.TRUE);
    }


    /**
     * 通过 categoryId 查询类目详情
     *
     * @param categoryId
     * @return
     */
    @GetMapping("/{categoryId}")
    public Result<CategoryResponse> get(@PathVariable String categoryId) {
        CategoryDO data = categoryService.getOne(categoryId);
        return new Result<>().withData(beanMapper.map(data, CategoryResponse.class));
    }

    /**
     * 查询类目列表
     *
     * @return
     */
    @GetMapping("/list")
    public Result<List<CategoryResponse>> list() {
        List<CategoryDO> records = categoryService.list(new CategoryParam());
        List<CategoryResponse> data = records
                .parallelStream()
                .map(category -> beanMapper.map(category, CategoryResponse.class))
                .collect(Collectors.toList());
        return new Result<>().withData(TreeUtils.buildTree(data, category -> StringUtils.isEmpty(category.getParentId())));
    }

    /**
     * 通过 categoryId 修改类目详情
     *
     * @return
     */
    @PutMapping("/{categoryId}")
    public Result<List<CategoryResponse>> edit(@PathVariable String categoryId, @RequestParam CategoryParam categoryParam) {
        categoryParam.setCategoryId(categoryId);
        categoryService.editOne(categoryParam);
        return new Result<>().withData(Boolean.TRUE);
    }



}
