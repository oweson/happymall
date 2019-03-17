package com.mmall.service;

import com.github.pagehelper.PageInfo;
import com.mmall.common.ServerResponse;
import com.mmall.pojo.Product;
import com.mmall.vo.ProductDetailVo;

/**
 * the class is create by @Author:oweson
 *
 * @Date：2018/8/25 0025 16:21
 */
public interface IProductService {


    /**
     * 1 保存或者更新商品
     */
    ServerResponse saveOrUpdateProduct(Product product);

    /**
     * 2 设置商品的状态
     */
    ServerResponse<String> setSaleStatus(Integer productId, Integer status);

    /**
     * 3 管理商品
     */

    ServerResponse<ProductDetailVo> manageProductDetail(Integer productId);

    /**
     * 4 后台查询商品的列表
     */
    ServerResponse<PageInfo> getProductList(int pageNum, int pageSize);

    /**
     * 5 后台搜索商品根据商品的名字
     */
    ServerResponse<PageInfo> searchProduct(String productName, Integer productId, int pageNum, int pageSize);

    /**
     * 6 获得商品的详情
     */
    ServerResponse<ProductDetailVo> getProductDetail(Integer productId);

    /**
     * 7 关键字搜素商品分页
     */
    ServerResponse<PageInfo> getProductByKeywordCategory(String keyword, Integer categoryId, int pageNum, int pageSize, String orderBy);


}
