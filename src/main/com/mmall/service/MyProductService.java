package com.mmall.service;

import com.github.pagehelper.PageInfo;
import com.mmall.common.ServerResponse;
import com.mmall.pojo.Product;
import com.mmall.vo.ProductDetailVo;

/**
 * the class is create by @Author:oweson
 *
 * @Date：2018/9/5 0005 14:38
 */
public interface MyProductService {
    ServerResponse<PageInfo> MyListProduct(Integer pageNum, Integer pageSize);

    ServerResponse<String> setStatus(Integer productId, Integer status);

    ServerResponse<ProductDetailVo> getOneProductDetails(Integer productId);

    ServerResponse saveOrUpdate(Product product);

    ServerResponse<PageInfo> mySearchProduct(String productName, Integer productId,
                                             Integer pageNum, Integer pageSize);


}
