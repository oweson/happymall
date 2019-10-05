package com.mmall.pojo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * mmall_order_item
 *
 * @author
 */
@Data
public class OrderItem02 implements Serializable {
    /**
     * 订单子表id
     */
    private Integer id;

    private Integer userId;

    private Long orderNo;

    /**
     * 商品id
     */
    private Integer productId;

    /**
     * 商品名称
     */
    private String productName;

    /**
     * 商品图片地址
     */
    private String productImage;

    /**
     * 生成订单时的商品单价，单位是元,保留两位小数
     */
    private BigDecimal currentUnitPrice;

    /**
     * 商品数量
     */
    private Integer quantity;

    /**
     * 商品总价,单位是元,保留两位小数
     */
    private BigDecimal totalPrice;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;


}