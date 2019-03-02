package com.mmall.dao;

import com.mmall.pojo.PayInfo;
/**
 * the class is create by @Author:oweson
 *
 * @Date：2018/9/5 0005 14:39
 */
public interface PayInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PayInfo record);

    int insertSelective(PayInfo record);

    PayInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PayInfo record);

    int updateByPrimaryKey(PayInfo record);
}