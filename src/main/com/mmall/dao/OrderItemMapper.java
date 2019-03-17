package com.mmall.dao;

import com.mmall.pojo.OrderItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * the class is create by @Author:oweson
 *
 * @Date：2018/9/5 0005 14:39
 */
public interface OrderItemMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OrderItem record);

    int insertSelective(OrderItem record);

    OrderItem selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OrderItem record);

    int updateByPrimaryKey(OrderItem record);

    /**
     * 校验某个用户下，根据一个订单号查询订单下面的订单项的集合
     */
    List<OrderItem> getByOrderNoUserId(@Param("orderNo") Long orderNo, @Param("userId") Integer userId);

    /**
     * 根据订单号查询订单项的集合
     */
    List<OrderItem> getByOrderNo(@Param("orderNo") Long orderNo);


    void batchInsert(@Param("orderItemList") List<OrderItem> orderItemList);


}