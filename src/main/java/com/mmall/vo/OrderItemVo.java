package com.mmall.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderItemVo {
    /**
     * 订单项
     */
    private Long orderNo;

    private Integer productId;

    private String productName;
    private String productImage;

    private BigDecimal currentUnitPrice;

    private Integer quantity;

    private BigDecimal totalPrice;

    private String createTime;


}
