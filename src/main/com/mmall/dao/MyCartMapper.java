package com.mmall.dao;

import com.mmall.pojo.Cart;

import java.util.List;

/**
 * the class is create by @Author:oweson
 *
 * @Date：2018/9/4 0004 13:28
 */
public interface MyCartMapper {
    List<Cart> selectCartByUserId(Integer id);

}
