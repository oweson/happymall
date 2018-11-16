package com.mmall.service;

import com.mmall.common.ServerResponse;
import com.mmall.vo.CountVo;

/**
 * the class is create by @Author:oweson
 *
 * @Date：2018/10/12 0012 20:55
 */
public interface CountNumber {
    ServerResponse<CountVo> count();
}
