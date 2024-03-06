package com.scofen.l78z.xiaochuan.controller;

import com.scofen.l78z.xiaochuan.controller.request.CategoryParam;
import com.scofen.l78z.xiaochuan.controller.response.CategoryVO;
import com.scofen.l78z.xiaochuan.controller.response.Response;
import com.scofen.l78z.xiaochuan.dao.dataObject.CategoryDO;
import com.scofen.l78z.xiaochuan.service.CategoryService;
import com.scofen.l78z.xiaochuan.util.TreeUtils;
import org.apache.commons.lang3.StringUtils;
import org.dozer.Mapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description: TODO
 * @Author gaofeng
 * @Date 2023/9/3 9:54 AM
 **/
@RestController
@RequestMapping("/category")
public class CategoryController extends BaseController {

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
    public Response<CategoryVO> add(@RequestBody CategoryParam categoryParam) {
        CategoryVO data = beanMapper.map(categoryService.addOne(categoryParam), CategoryVO.class);
        return new Response<>().withData(data);
    }

    /**
     * 通过 categoryId 删除类目
     *
     * @param categoryId
     * @return
     */
    @DeleteMapping("/{categoryId}")
    public Response<Boolean> removeOne(@PathVariable String categoryId) {
        categoryService.removeOne(categoryId);
        return new Response<>()
                .withData(Boolean.TRUE)
                .withErrorMsg("删除信息成功");
    }


    /**
     * 通过 categoryId 查询类目详情
     *
     * @param categoryId
     * @return
     */
    @GetMapping("/{categoryId}")
    public Response<CategoryVO> get(@PathVariable String categoryId) {
        CategoryDO data = categoryService.getOne(categoryId);
        return new Response<>().withData(beanMapper.map(data, CategoryVO.class));
    }

    /**
     * 查询类目列表
     *
     * @return
     */
    @GetMapping("/list")
    public Response<List<CategoryVO>> list() {
        List<CategoryDO> records = categoryService.list(new CategoryParam());
        List<CategoryVO> data = records
                .parallelStream()
                .map(category -> beanMapper.map(category, CategoryVO.class))
                .collect(Collectors.toList());
        return new Response<>().withData(TreeUtils.buildTree(data, category -> StringUtils.isEmpty(category.getParentId())));
    }

    /**
     * 通过 categoryId 修改类目详情
     *
     * @return
     */
    @PutMapping("/{categoryId}")
    public Response<List<CategoryVO>> edit(@PathVariable String categoryId, @RequestBody CategoryParam categoryParam) {
        categoryParam.setCategoryId(categoryId);
        categoryService.editOne(categoryParam);
        return new Response<>()
                .withData(Boolean.TRUE)
                .withErrorMsg("修改信息成功");
    }



}
