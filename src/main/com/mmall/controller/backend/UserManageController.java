package com.mmall.controller.backend;

import com.mmall.common.Const;
import com.mmall.common.ServerResponse;
import com.mmall.pojo.User;
import com.mmall.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("/manage/user")
public class UserManageController {

    @Autowired
    private IUserService iUserService;

    /**
     * 1 后端管理员的登录,
     */

    @RequestMapping(value = "login.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> login(String username, String password, HttpSession session) {
        /**1 登陆后得到响应的对象信息；*/
        ServerResponse<User> response = iUserService.login(username, password);
        if (response.isSuccess()) {
            /** 2 登录成功，得到返回的数据*/
            User user = response.getData();
            /** 3 获得用户的角色，并且判断角色是不是管理的角色；*/
            if (user.getRole() == Const.Role.ROLE_ADMIN) {
                /** 4 说明登录的是管理员，把用户信息放入session*/
                session.setAttribute(Const.CURRENT_USER, user);
                return response;
            } else {
                return ServerResponse.createByErrorMessage("不是管理员,无法登录");
            }
        }
        /** 5 没有成功直接返回提示信息；*/
        return response;
    }

}
