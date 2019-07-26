package com.mmall.controller.portal;

import com.github.pagehelper.PageInfo;
import com.mmall.common.ServerResponse;
import com.mmall.service.IProductService;
import com.mmall.vo.ProductDetailVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/product/")
public class ProductController {

    @Autowired
    private IProductService iProductService;


    /*
        @RequestMapping(value = "/{productId}", method = RequestMethod.GET)
    */
    @RequestMapping("detail.do")
    @ResponseBody
    public ServerResponse<ProductDetailVo> detail(@RequestParam("productId") Integer productId) {
        return iProductService.getProductDetail(productId);
    }

    /**
     * 2 实现restful
     * keyword/{keyword}进行制定！！！------keyword定位了list方法！！！
     * http://localhost:9090//product/keyword/%E6%89%8B%E6%9C%BA/100012/1/2/price_dsc
     */
    @RequestMapping(value = "keyword/{keyword}/{categoryId}/{pageNum}/{pageSize}/{orderBy}",
            method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<PageInfo> list(@PathVariable(value = "keyword") String keyword,
                                         @PathVariable(value = "categoryId") Integer categoryId,
                                         @PathVariable(value = "pageNum") Integer pageNum,
                                         @PathVariable(value = "pageSize") Integer pageSize,

                                         @PathVariable(value = "orderBy") String orderBy) {
        /*为商品的分页参数设置默认值*/
        if (pageNum == null) {
            pageNum = 1;

        }
        if (pageSize == null) {
            pageSize = 10;
        }
        if (StringUtils.isBlank(orderBy)) {
            /**默认价格低的上浮*/
            orderBy = "price_asc";
        }

        return iProductService.getProductByKeywordCategory(keyword, categoryId, pageNum, pageSize, orderBy);
    }


}
