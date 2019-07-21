package com.mmall.service.impl;

import com.mmall.common.ServerResponse;
import com.mmall.dao.OrderMapper;
import com.mmall.dao.ProductMapper;
import com.mmall.dao.UserMapper;
import com.mmall.service.CountNumber;
import com.mmall.vo.CountVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * the class is create by @Author:oweson
 *
 * @Date：2018/10/12 0012 20:55
 */
@Service
public class CountNumberImpl implements CountNumber {
    /**
     * userCount: 123,
     * productCount: 456,
     * orderCount: 789
     */
    @Autowired
    UserMapper userMapper;
    @Autowired
    ProductMapper productMapper;
    @Autowired
    OrderMapper orderMapper;


    @Override
    public ServerResponse<CountVo> count() {
        CountVo countVo = new CountVo();
        int user = userMapper.count();
        int product = productMapper.count();
        int order = orderMapper.count();
        countVo.setUserCount(user);
        countVo.setProductCount(product);
        countVo.setOrderCount(order);
        return ServerResponse.createBySuccess(countVo);
    }
}
