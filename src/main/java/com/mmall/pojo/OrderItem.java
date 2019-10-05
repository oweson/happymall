package com.mmall.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class OrderItem {
    // com.mmall.pojo.OrderItem
    public OrderItem() {

    }

    private Integer id;
    /**
     * 1 一个订单项归属一个订单，一个订单对应多个订单项
     */
    private Long orderNo;
    /**
     * 2 商品的id，一个订单项关于具体哪个商品的具体信息
     */
    private Integer productId;
    /**
     * 3 某个订单项的主人
     */
    private Integer userId;

    private String productName;

    private String productImage;

    private BigDecimal currentUnitPrice;

    private Integer quantity;

    private BigDecimal totalPrice;

    private Date createTime;

    private Date updateTime;


}