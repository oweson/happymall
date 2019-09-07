package com.mmall.service;

import com.github.pagehelper.PageInfo;
import com.mmall.common.ServerResponse;
import com.mmall.pojo.Shipping;

/**
 * the class is create by @Author:oweson
 *
 * @Date：2018/9/4 0004 16:13
 */

public interface IShippingService {
    /**
     * 1 添加收货地址
     */

    ServerResponse add(Integer userId, Shipping shipping);

    /**
     * 2 删除收货地址
     */
    ServerResponse<String> del(Integer userId, Integer shippingId);

    /**
     * 3 更新收货地址
     */
    ServerResponse update(Integer userId, Shipping shipping);

    /**
     * 4 查看收货地址详情
     */
    ServerResponse<Shipping> select(Integer userId, Integer shippingId);

    /**
     * 5 收货地址分页列表显示
     */
    ServerResponse<PageInfo> list(Integer userId, int pageNum, int pageSize);


}
