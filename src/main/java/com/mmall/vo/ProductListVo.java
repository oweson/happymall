package com.mmall.vo;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class ProductListVo {
    /*** "id": 2,
     "categoryId": 2,
     "name": "oppo R8",
     "subtitle": "oppo促销进行中",
     "mainImage": "mainimage.jpg",
     "status":1,
     "price": 2999.11*/

    private Integer id;
    private Integer categoryId;

    private String name;
    private String subtitle;
    private String mainImage;
    private BigDecimal price;

    private Integer status;
    /**
     * 不需要返回，其他的要用
     */

    private String imageHost;

    
}
