package com.mmall.dao;

import com.mmall.pojo.Shipping;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * the class is create by @Author:oweson
 *
 * @Date：2018/9/4 0004 16:34
 */
public interface MyShippingMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Shipping record);

    int insertSelective(Shipping record);

    Shipping selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Shipping record);

    int updateByPrimaryKey(Shipping record);

    int deleteByShippingIdUserId(@Param("userId") Integer userId, @Param("shippingId") Integer shippingId);

    int updateByShipping(Shipping record);

    Shipping selectByShippingIdUserId(@Param("userId") Integer userId, @Param("shippingId") Integer shippingId);

    List<Shipping> selectByUserId(@Param("userId") Integer userId);

    /**
     * 自己的方法
     */
    // int insertShipping(Shipping shipping);
    int delShipping(@Param("userId") Integer userId, @Param("shippingId") Integer shippingId);

    Shipping selectOneShipping(@Param("userId") Integer userId, @Param("shippingId") Integer shippingId);

    /**
     * id已经存在
     */
    int updateShipping(Shipping shipping);

    List<Shipping> selectShippingList(@Param("userId") Integer userId);
}
