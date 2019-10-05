package com.mmall.dao;

import com.mmall.pojo.OrderItem02;

/**
 * 订单测试出现问题；
 * GeneratorUI生成的代码！！！
 */
public interface MmallOrderItemMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OrderItem02 record);

    int insertSelective(OrderItem02 record);

    OrderItem02 selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OrderItem02 record);

    int updateByPrimaryKey(OrderItem02 record);
}