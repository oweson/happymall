package com.mmall.vo;

import lombok.Data;

/**
 * the class is create by @Author:oweson
 *
 * @Date：2018/10/12 0012 17:19
 */

public class CountVo {
    /**userCount: 123,
     productCount: 456,
     orderCount: 789*/
    private Integer userCount;
    private Integer productCount;
    private Integer orderCount;

    public Integer getUserCount() {
        return userCount;
    }

    public void setUserCount(Integer userCount) {
        this.userCount = userCount;
    }

    public Integer getProductCount() {
        return productCount;
    }

    public void setProductCount(Integer productCount) {
        this.productCount = productCount;
    }

    public Integer getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(Integer orderCount) {
        this.orderCount = orderCount;
    }

    public CountVo(Integer userCount, Integer productCount, Integer orderCount) {
        this.userCount = userCount;
        this.productCount = productCount;
        this.orderCount = orderCount;
    }

    public CountVo() {
    }

    @Override
    public String toString() {
        return "CountVo{" +
                "userCount=" + userCount +
                ", productCount=" + productCount +
                ", orderCount=" + orderCount +
                '}';
    }
}
