package com.mmall.service;

import com.mmall.common.ServerResponse;
import com.mmall.dao.OrderItemMapper;
import com.mmall.pojo.OrderItem;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import teststh.BaseClass;

import java.util.List;

import static org.junit.Assert.*;

/**
 * the class is create by @Author:oweson
 *
 * @Date：2018/11/1 0001 15:46
 */
public class IOrderServiceTest extends BaseClass {
    @Autowired
    private IOrderService iOrderService;
    @Autowired
    OrderItemMapper orderItemMapper;
    @Test
    public void findError(){
        List<OrderItem> byOrderNo = orderItemMapper.getByOrderNo(1492091083720L);
        System.out.println(byOrderNo);

    }

    @Test
    public void pay() {
    }

    @Test
    public void aliCallback() {
    }

    @Test
    public void queryOrderPayStatus() {

        System.out.println();
    }

    @Test
    public void createOrder() {
        ServerResponse order = iOrderService.createOrder(1, 1);
        Object data = order.getData();
        System.out.println(order.getMsg());
    }

    @Test
    public void cancel() {
    }

    @Test
    public void getOrderCartProduct() {
    }

    @Test
    public void getOrderDetail() {
    }

    @Test
    public void getOrderList() {
    }

    @Test
    public void manageList() {
    }

    @Test
    public void manageDetail() {
    }

    @Test
    public void manageSearch() {
    }

    @Test
    public void manageSendGoods() {
    }
}