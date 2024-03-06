package com.scofen.l78z.xiaochuan.controller;

import com.scofen.l78z.xiaochuan.controller.request.ProductQueryParam;
import com.scofen.l78z.xiaochuan.controller.response.ProductVO;
import com.scofen.l78z.xiaochuan.controller.response.Response;
import com.scofen.l78z.xiaochuan.dao.dataObject.ProductDO;
import com.scofen.l78z.xiaochuan.service.ProductService;
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
@RequestMapping("")
public class ProductController extends BaseController {

    @Resource
    ProductService productService;

    @Resource
    Mapper beanMapper;

    /**
     * 新增 商品
     *
     * @param param
     * @return
     */
    @PostMapping("/product")
    public Response<ProductVO> add(@RequestParam ProductVO param) {
        ProductDO data = productService.addOne(param);
        return new Response<>().withData(ProductVO.read4(data));
    }

    /**
     * 通过 productService 删除商品
     *
     * @param productId
     * @return
     */
    @DeleteMapping("/product/{productId}")
    public Response<Boolean> removeOne(@PathVariable String productId) {
        productService.removeOne(productId);
        return new Response<>().withData(Boolean.TRUE);
    }


    /**
     * 通过 productId和searchKey 模糊查询商品列表
     *
     * @param productId
     * @param searchKey
     * @return
     */
    @GetMapping("/product")
    public Response<List<ProductVO>> search(@RequestParam(value = "", required = false) String productId,
                                   @RequestParam(value = "", required = false) String searchKey) {
        List<ProductDO> data = productService.search(productId, searchKey);
        return new Response<>().withData(data
                .parallelStream()
                .map(ProductVO::read4)
                .collect(Collectors.toList()));
    }

    /**
     * 通过 ProductQueryParam 查询商品列表
     *
     * @param param
     * @return
     */
    @GetMapping("/products")
    public Response<List<ProductVO>> page(@RequestParam ProductQueryParam param) {
        List<ProductDO> data = productService.page(param);
        return new Response<>().withData(data
                .parallelStream()
                .map(ProductVO::read4)
                .collect(Collectors.toList()));
    }


}
