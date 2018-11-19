package com.mmall.controller.mybackend;

import com.github.pagehelper.PageInfo;
import com.mmall.common.Const;
import com.mmall.common.ResponseCode;
import com.mmall.common.ServerResponse;
import com.mmall.pojo.User;
import com.mmall.service.ICategoryService;
import com.mmall.service.IUserService;
import com.mmall.service.MyICategoryservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * the class is create by @Author:oweson
 *
 * @Date：2018/8/25 0025 10:04
 */
@Controller
@RequestMapping("my/manager/category")

public class MyCategoryManageController {

    @Autowired
    private IUserService userService;
    @Autowired
    private MyICategoryservice myICategoryservice;
    @Autowired
    private ICategoryService iCategoryService;

    @RequestMapping("/testPage")
    @ResponseBody
    public ServerResponse page() {
        ServerResponse<PageInfo> product = iCategoryService.getCategory();
        return product;


    }

    /**
     * 1 增加分类,后台的操作，组要判断登录，是否管理员
     * ，传入category_name,parent_id默认为0
     */
    @RequestMapping("add_category.do")
    @ResponseBody

    public ServerResponse addCategory(HttpSession session, String categoryName, @RequestParam(value = "parentId",
            defaultValue = "0") int parentId) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "需要登录才能添加");

        }
        /**判断是不是管理员，校验方法在userService
         * 把user传过去*/
        if (userService.myCheckAdminRole(user).isSuccess()) {
            /**参数合法处理逻辑*/
            return myICategoryservice.addCategory(categoryName, parentId);

        } else {
            return ServerResponse.createByErrorMessage("无权限操作，需要管理员权限才可以");
        }


    }

    /**
     * 2 修改分类
     */
    @RequestMapping("update_categoryName.do")
    @ResponseBody
    public ServerResponse setCategoryName(HttpSession session, String categoryName, Integer categoryId) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "需要登录才能添加");

        }
        /**判断是不是管理员，校验方法在userService
         * 把user传过去*/
        if (userService.myCheckAdminRole(user).isSuccess()) {
            /**参数合法处理逻辑*/
            // myICategoryservice.addCategory(categoryName, parentId);
            /**更新操作*/
            return myICategoryservice.updateCategoryName(categoryName, categoryId);


        } else {
            return ServerResponse.createByErrorMessage("无权限操作，需要管理员权限才可以");
        }

    }

    /**
     * 3 查询子节点
     */
    @RequestMapping("getCategory.do")
    @ResponseBody
    public ServerResponse getChildrenParelelCategoryId(HttpSession session, @RequestParam(value = "categoryId", defaultValue = "0") Integer categoryId) {

        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "需要登录才能添加");

        }
        /**判断是不是管理员，校验方法在userService
         * 把user传过去*/
        if (userService.myCheckAdminRole(user).isSuccess()) {
            /**参数合法处理逻辑*/
            return myICategoryservice.getChildrenParelelCategoryId(categoryId);

        } else {
            return ServerResponse.createByErrorMessage("无权限操作，需要管理员权限才可以");
        }

    }


}
