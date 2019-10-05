package com.mmall.dao.test;

import com.mmall.dao.MmallOrderItemMapper;
import com.mmall.dao.OrderItemMapper;
import com.mmall.pojo.OrderItem;
import com.mmall.pojo.OrderItem02;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import utils.TestBase;

/**
 * the class is create by @Author:oweson
 *
 * @Date：2019/10/5 15:07
 */
public class OrderItem02Test extends TestBase {
    @Autowired
    MmallOrderItemMapper mmallOrderItemMapper;
    @Autowired
    OrderItemMapper orderItemMapper;
    @Test
    public void selectById(){
        OrderItem02 orderItem02 = mmallOrderItemMapper.selectByPrimaryKey(113);
        System.out.println(orderItem02);

    }
    @Test
    public void demo2(){
        OrderItem orderItem = orderItemMapper.selectByPrimaryKey(113);
        System.out.println(orderItem);

    }
}
