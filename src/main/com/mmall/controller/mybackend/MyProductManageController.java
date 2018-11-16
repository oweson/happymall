package com.mmall.controller.mybackend;

import com.mmall.common.Const;
import com.mmall.common.ResponseCode;
import com.mmall.common.ServerResponse;
import com.mmall.pojo.Product;
import com.mmall.pojo.User;
import com.mmall.service.IUserService;
import com.mmall.service.MyProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpSession;
import javax.swing.*;
import java.io.Console;
import java.security.PublicKey;

/**
 * the class is create by @Author:oweson
 *
 * @Date：2018/9/5 0005 14:37
 */
@Controller
@RequestMapping("/product/manage/cheng")
public class MyProductManageController {
    @Autowired
    private IUserService iUserService;
    @Autowired
    private MyProductService myProductService;

    @RequestMapping("/list.do")
    @ResponseBody
    public ServerResponse listProduct(HttpSession session,
                                      @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                      @RequestParam(value = "pageSize", defaultValue = "6") Integer pageSize) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "需要登录才可以登录后台操作....");
        }
        if (iUserService.checkAdminRole(user).isSuccess()) {
            //填充业务
            return myProductService.MyListProduct(pageNum, pageSize);
        } else {
            return ServerResponse.createByErrorMessage("无权限操作");
        }


    }

    @RequestMapping("/setStatus.do")
    @ResponseBody
    /** 2 修改商品的销售状态*/
    public ServerResponse setStatus(HttpSession session, Integer productId, Integer status) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "需要登录才可以登录后台操作....");
        }
        if (iUserService.checkAdminRole(user).isSuccess()) {
            //填充业务
            return myProductService.setStatus(productId, status);
        } else {
            return ServerResponse.createByErrorMessage("无权限操作");
        }
    }

    @RequestMapping("/search.do")
    @ResponseBody
    /** 3 根据商品的id和商品的name进行搜索*/
    public ServerResponse setSearchProduct(HttpSession session, String productName, Integer productId,
                                           @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                           @RequestParam(value = "pageSize", defaultValue = "6") Integer pageSize) {

        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "需要登录才可以登录后台操作....");
        }
        if (iUserService.checkAdminRole(user).isSuccess()) {
            //填充业务
            return myProductService.mySearchProduct(productName, productId, pageNum, pageSize);
        } else {
            return ServerResponse.createByErrorMessage("无权限操作");
        }
    }

    /**
     * 4 更新保存
     */
    @RequestMapping("/saveOrUpdate.do")
    @ResponseBody
    public ServerResponse saveOrUpdateOneProduct(Product product, HttpSession session) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "需要登录才可以登录后台操作....");
        }
        if (iUserService.checkAdminRole(user).isSuccess()) {
            return myProductService.saveOrUpdate(product);
            //填充业务
        } else {
            return ServerResponse.createByErrorMessage("无权限操作");
        }


    }

    /**
     * 5 查看某个产品的详情
     */
    @RequestMapping("/getDetails.do")
    @ResponseBody
    public ServerResponse getOneProductDetails(HttpSession session, Integer productId) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "需要登录才可以登录后台操作....");
        }
        if (iUserService.checkAdminRole(user).isSuccess()) {
            //填充业务
            return myProductService.getOneProductDetails(productId);
        } else {
            return ServerResponse.createByErrorMessage("无权限操作");
        }


    }


}
