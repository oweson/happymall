package com.mmall.service;

import com.mmall.common.ServerResponse;
import com.mmall.vo.CountVo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import teststh.BaseClass;


/**
 * the class is create by @Author:oweson
 *
 * @Date：2018/10/12 0012 21:13
 */
public class CountNumberTest extends BaseClass {
    @Autowired
    CountNumber countNumber;

    @Test
    public void count() {
        ServerResponse<CountVo> count = countNumber.count();
        System.out.println(count.toString());
        System.out.println(count.getData().getOrderCount());
    }
}