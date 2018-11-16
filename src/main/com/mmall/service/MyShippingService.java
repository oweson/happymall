package com.mmall.service;

import com.github.pagehelper.PageInfo;
import com.mmall.common.ServerResponse;
import com.mmall.pojo.Shipping;

/**
 * the class is create by @Author:oweson
 *
 * @Date：2018/9/4 0004 16:13
 */
public interface MyShippingService {
    ServerResponse add(Integer userId, Shipping shipping);

    ServerResponse del(Integer userId, Integer shippingId);

    ServerResponse update(Integer userId, Shipping shipping);

    ServerResponse selectDetails(Integer userId, Integer shippingId);

    ServerResponse<PageInfo> showShippingList(Integer userId, Integer pageNum, Integer pageSize);


}
