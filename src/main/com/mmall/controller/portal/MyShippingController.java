package com.mmall.controller.portal;

import com.mmall.common.Const;
import com.mmall.common.ResponseCode;
import com.mmall.common.ServerResponse;
import com.mmall.pojo.Shipping;
import com.mmall.pojo.User;
import com.mmall.service.MyShippingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;
import org.springframework.jdbc.support.nativejdbc.OracleJdbc4NativeJdbcExtractor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

/**
 * the class is create by @Author:oweson
 *
 * @Date：2018/9/4 0004 18:02
 */
@Controller
@RequestMapping("/chengcart")
public class MyShippingController {
    @Autowired
    private MyShippingService myShippingService;

    @RequestMapping("/ppx.do")
    @ResponseBody
    public Object ppx(HttpRequest request) {
        Map<String, Object> map = new HashMap();
        HttpMethod method = request.getMethod();
        URI uri = request.getURI();
        HttpHeaders headers = request.getHeaders();
        map.put("1", method);
        map.put("2", uri);
        map.put("3", headers);
        return map;


    }

    @RequestMapping("/add.do")
    @ResponseBody
    public ServerResponse add(HttpSession session, Shipping shipping) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);

        /**判断是否登录,session中得到，是不是本人，到了这里一定已经登录*/
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        }
        return myShippingService.add(user.getId(), shipping);


    }

    @RequestMapping("/del.do")
    @ResponseBody
    public ServerResponse del(HttpSession session, Integer shippingId) {

        /**需要用户的id,删除的是那个用户下的id*/
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        }

        return myShippingService.del(user.getId(), shippingId);

    }

    @RequestMapping("/select.do")
    @ResponseBody
    public ServerResponse selectOne(HttpSession session, Integer shippingId) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        }
        return myShippingService.selectDetails(user.getId(), shippingId);
    }

    @RequestMapping("/update.do")
    @ResponseBody
    public ServerResponse updateOne(HttpSession session, Shipping shipping) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        }
        return myShippingService.update(user.getId(), shipping);

    }

    @RequestMapping("/list.do")
    @ResponseBody
    public ServerResponse listShipping(HttpSession session,
                                       @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                       @RequestParam(value = "pageSize", defaultValue = "2") Integer pageSize) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        }
        return myShippingService.showShippingList(user.getId(), pageNum, pageSize);


    }
}
