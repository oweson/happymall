package com.mmall.vo;

import lombok.Data;

/**
 * the class is create by @Author:oweson
 *
 * @Date：2018/10/12 0012 17:19
 */
@Data
public class CountVo {
    /**
     * userCount: 123,
     * productCount: 456,
     * orderCount: 789
     */
    private Integer userCount;
    private Integer productCount;
    private Integer orderCount;


}
