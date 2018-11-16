package com.mmall.dao;

import com.mmall.pojo.User;
import org.apache.ibatis.annotations.Param;

/**
 * the class is create by @Author:oweson
 *
 * @Date：2018/9/9 0009 19:57
 */
public interface MyUserMapper {
    /**
     * 1 登录
     */
    User login(@Param("username") String username, @Param("password") Integer password);
    /** 2 注册*/
    /**
     * 3 检验用户名字是否有效
     */
    User check_Valied(@Param("str") String str, @Param("type") String type);

    int checkName(String name);

    int checkEmail(String email);


}
