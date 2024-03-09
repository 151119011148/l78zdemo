package com.scofen.l78z.xiaochuan.controller;

import com.scofen.l78z.xiaochuan.common.exception.ResultCode;
import com.scofen.l78z.xiaochuan.controller.request.ProductQueryParam;
import com.scofen.l78z.xiaochuan.controller.request.UserParam;
import com.scofen.l78z.xiaochuan.controller.response.Pager;
import com.scofen.l78z.xiaochuan.controller.response.ProductVO;
import com.scofen.l78z.xiaochuan.controller.response.Response;
import com.scofen.l78z.xiaochuan.controller.response.UserVO;
import com.scofen.l78z.xiaochuan.dao.dataObject.ProductDO;
import com.scofen.l78z.xiaochuan.dao.dataObject.UserDO;
import com.scofen.l78z.xiaochuan.service.ProductService;
import org.apache.commons.lang3.StringUtils;
import org.dozer.Mapper;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


/**
 * @Description: TODO
 * @Author gaofeng
 * @Date 2023/9/3 9:54 AM
 **/
@RestController
@RequestMapping("/product")
public class ProductController extends BaseController {

    @Resource
    ProductService productService;


    /**
     * 新增 商品
     *
     * @param param
     * @return
     */
    @PostMapping("")
    public Response<ProductVO> add(@RequestBody ProductVO param) {
        ProductDO data = productService.addOne(ProductVO.convert2(param));
        return new Response<>()
                .withData(ProductVO.read4(data))
                .withErrorMsg("添加商品成功");
    }

    /**
     * 通过 productService 删除商品
     *
     * @param productId
     * @return
     */
    @DeleteMapping("/{productId}")
    public Response<Boolean> removeOne(@PathVariable String productId) {
        productService.removeOne(productId);
        return new Response<>()
                .withData(Boolean.TRUE)
                .withErrorMsg("删除商品成功");
    }


    /**
     * 通过 productId或者searchKey 模糊查询商品列表
     *
     * @param productId
     * @param searchKey
     * @return
     */
    @GetMapping("")
    public Response<List<ProductVO>> search(@RequestParam(value = "", required = false) String productId,
                                            @RequestParam(value = "", required = false) String searchKey) {
        if (StringUtils.isAllEmpty(productId, searchKey)) {
            return new Response<>(ResultCode.PARAM_ERROR.getCode(), "productId and searchKey is null!");
        }
        List<ProductVO> data = productService.search(productId, searchKey);
        return new Response<>().withData(data);
    }

    /**
     * 通过 ProductQueryParam 查询商品列表
     *
     * @param param
     * @return
     */
    @GetMapping("/page")
    public Response<List<ProductVO>> page(ProductQueryParam param) {
        if (StringUtils.isEmpty(param.getQueryKey())){
            return new Response<>(ResultCode.PARAM_ERROR.getCode(), "queryKey is not null!");
        }
        if (Objects.isNull(param.getPageIndex()) || Objects.isNull(param.getPageSize())){
            return new Response<>(ResultCode.PARAM_ERROR.getCode(), "page is not null!");
        }
        Integer pageIndex = param.getPageIndex();
        param.setPageIndex(pageIndex - 1);
        if (StringUtils.equals(param.getQueryKey(), "in_basket")){
            param.setQueryValue(getIp());
        }
        Page<ProductVO> pageResult = productService.page(param);
        Pager pager = new Pager(pageIndex, param.getPageSize(), pageResult.getTotalElements());
        return new Response<>()
                .withPager(pager)
                .withData(pageResult.getContent());
    }


    /**
     * 通过 productId 修改商品信息
     *
     * @return
     */
    @PutMapping("/{productId}")
    public Response<ProductVO> edit(@PathVariable String productId, @RequestBody ProductVO param) {
        ProductDO product = productService.editOne(ProductVO.convert2(param));
        return new Response<>()
                .withData(ProductVO.read4(product))
                .withErrorMsg("修改信息成功");
    }
}
