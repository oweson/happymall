package com.mmall.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cart {
    private Integer id;
    /**
     * 1 购物车的主人
     */
    private Integer userId;
    /**
     * 2 购物车的商品
     */
    private Integer productId;

    private Integer quantity;

    private Integer checked;

    private Date createTime;

    private Date updateTime;


}