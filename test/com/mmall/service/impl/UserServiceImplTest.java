package com.mmall.service.impl;

import com.github.pagehelper.PageInfo;
import com.mmall.common.ServerResponse;
import com.mmall.dao.UserMapper;
import com.mmall.pojo.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import teststh.BaseClass;

import javax.jws.soap.SOAPBinding;
import java.util.List;

import static org.junit.Assert.*;

/**
 * the class is create by @Author:oweson
 *
 * @Date：2018/11/19 0019 20:15
 */
public class UserServiceImplTest extends BaseClass {
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    UserMapper userMapper;

    @Test
    public void list() {
        List<User> list2 = userMapper.list();
        for (User user : list2) {
            System.out.println(user);

        }
        ServerResponse<PageInfo> list = userService.list(0, 2);
        /**分页数据没问题！！！*/
        //todo controller测试不通过？？？
        System.out.println(list.getData().getTotal());
        System.out.println(list.getData().getList());
        System.out.println(list.getData().isHasNextPage());
        System.out.println(list.getData().getList());
        List list1 = list.getData().getList();
        System.out.println(list1);

    }
}