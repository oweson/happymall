package com.mmall.controller.portal;

import com.mmall.common.Const;
import com.mmall.common.ResponseCode;
import com.mmall.common.ServerResponse;
import com.mmall.pojo.User;
import com.mmall.service.IUserService;
import com.sun.corba.se.spi.activation.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("/user/")
public class UserController {


    @Autowired
    private IUserService iUserService;


    /**
     * 1 用户登录
     *
     * @param username
     * @param password
     * @param session
     * @return
     */
    @RequestMapping(value = "login.do", method = RequestMethod.POST)
    /**1  将返回值序列化为json，并且限定提交方式为post*/
    @ResponseBody
    public ServerResponse<User> login(String username, String password, HttpSession session) {
        ServerResponse<User> response = iUserService.login(username, password);
        if (response.isSuccess()) {
            /**仅仅登录，得到对象的状态码等于成功的状态码说明登录成功，吧得到的user数据放到session中*/
            /**session中的key就是常量，value就是user数据信息；*/
            session.setAttribute(Const.CURRENT_USER, response.getData());
        }
        //吧json数据返回给前端；
        return response;
    }

    /**
     * 2 用户登出（退出接口）就是把session中的key给删除
     */
    @RequestMapping(value = "logout.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> logout(HttpSession session) {
        session.removeAttribute(Const.CURRENT_USER);
        /**
         *         session.invalidate();
         *         所有的session失效
         * */
        /**返回成功状态码0；*/
        return ServerResponse.createBySuccess();
    }

    /**
     * 3用户注册接口
     */
    @RequestMapping(value = "register.do", method = RequestMethod.POST)
    @ResponseBody
    /**用对象吧数据进行封装,真正的处理逻辑在service层*/
    public ServerResponse<String> register(User user) {
        return iUserService.register(user);
    }

    /**
     * 4 教研用户名字或者邮箱用于立反馈，输入Input框进行ajax教研，
     * 防止恶心获得注册接口，输入email离开光标，不合适就提示
     */
    @RequestMapping(value = "check_valid.do", method = RequestMethod.POST)
    @ResponseBody
    //传入service进行教研，无论成功与否返回提示信息给前端；
    public ServerResponse<String> checkValid(String str, String type) {
        return iUserService.checkValid(str, type);
    }

    /**
     * 5 获得用户登录信息
     */
    @RequestMapping(value = "get_user_info.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> getUserInfo(HttpSession session) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user != null) {
            //session中的数据不为null,就取出来穿进去返回user信息给前端；
            return ServerResponse.createBySuccess(user);
        }
        //没有得到信息就是为null，用户没有登录
        return ServerResponse.createByErrorMessage("用户未登录,无法获取当前用户的信息");
    }


    @RequestMapping(value = "forget_get_question.do", method = RequestMethod.POST)
    @ResponseBody
    /**6 只记得用户名密码忘记了根据密保重置密码；得到密保问题。。。。。*/
    public ServerResponse<String> forgetGetQuestion(String username) {
        return iUserService.selectQuestion(username);
    }

    /**
     * 6 根据用户的name，问题和答案进行检验用户名字是否是自己的，是否是做正确的；
     */
    @RequestMapping(value = "forget_check_answer.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> forgetCheckAnswer(String username, String question, String answer) {
        return iUserService.checkAnswer(username, question, answer);
    }

    /**
     * 7 更新新的密码带数据库
     */
    @RequestMapping(value = "forget_reset_password.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> forgetRestPassword(String username, String passwordNew, String forgetToken) {
        return iUserService.forgetResetPassword(username, passwordNew, forgetToken);
    }


    /**
     * 8 用户已经登录要重置密码；
     */
    @RequestMapping(value = "reset_password.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> resetPassword(HttpSession session, String passwordOld, String passwordNew) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            /**判断用户书否登录，就是判断sesson中是否拥有用户信息；*/
            return ServerResponse.createByErrorMessage("用户未登录");
        }
        return iUserService.resetPassword(passwordOld, passwordNew, user);
    }

    /**
     * 9 更新用户的信息
     */
    @RequestMapping(value = "update_information.do", method = RequestMethod.POST)
    @ResponseBody
    //user新的用户信息；
    public ServerResponse<User> update_information(HttpSession session, User user) {
        User currentUser = (User) session.getAttribute(Const.CURRENT_USER);
        //从session中的得到用户的信息，判断用户是否登录；
        //只有在登录状态才能更新用户的信息；
        if (currentUser == null) {
            return ServerResponse.createByErrorMessage("用户未登录");
        }
        /**到了这里，用户登陆了；*/
        /**为了防止越权传入从session中得到的当前用户的id；*/
        user.setId(currentUser.getId());
        /**为了防止越权传入从session中得到的当前用户的名字；*/
        user.setUsername(currentUser.getUsername());
        ServerResponse<User> response = iUserService.updateInformation(user);
        //true更新信息成功了；通过比较成功状态码
        //更新session中的信息；
        //response.getData就是user对象的信息；
        if (response.isSuccess()) {
            //要返回的信息需要userName否则session中就没有UserName了；
            //得到当前用户的userName设置进去；进行返回：
            response.getData().setUsername(currentUser.getUsername());
            //            //返回user信息给前端；
            session.setAttribute(Const.CURRENT_USER, response.getData());
        }
        //到了这里就是更新信息失败了；
        return response;
    }

    /**
     * 10 ，修改个人信息的时候首先get得到信息，进行个人信息的回显然后在进行更新；
     * 9获得用户个人信息，首先调用updTE 9F方法；
     */

    @RequestMapping(value = "get_information.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> get_information(HttpSession session) {
        //从session中判断用户是否已经登录；
        User currentUser = (User) session.getAttribute(Const.CURRENT_USER);
        if (currentUser == null) {
            //返回失败状态码10给前端；
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "未登录,需要强制登录status=10");
        }
        return iUserService.getInformation(currentUser.getId());
    }


}
