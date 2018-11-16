package com.mmall.service;

import com.mmall.common.ServerResponse;
import com.mmall.pojo.Product;

/**
 * the class is create by @Author:oweson
 *
 * @Date：2018/8/25 0025 16:21
 */
public interface MyIProductService {
    public ServerResponse saveOrUpdateProduct(Product product);

    public ServerResponse<String> setSellStatus(Integer ProductId, Integer status);
}
