package com.mmall.service.impl;

import com.mmall.common.Const;
import com.mmall.common.ServerResponse;
import com.mmall.common.TokenCache;
import com.mmall.dao.UserMapper;
import com.mmall.pojo.User;
import com.mmall.service.IUserService;
import com.mmall.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Created by geely
 */
@Service("iUserService")
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    /**1 用户登录根据密码用户名和密码进行登录，返回搜影响的行数；*/
    public ServerResponse<User> login(String username, String password) {
        int resultCount = userMapper.checkUsername(username);
        if (resultCount == 0) {
            //，检查用户名是不是存在，没有找到对应的用户返回错误
            return ServerResponse.createByErrorMessage("用户名不存在");
        }
//用户名存在，那用户的密码进行加密，返回加密后的密码；
        String md5Password = MD5Util.MD5EncodeUtf8(password);
        //根据密码和用户名进行登录操作；，返回user对象；
        User user = userMapper.selectLogin(username, md5Password);
        //进行null判断；如果是null,说明没有找到，既然用户名存在，那么一定是密码错误
        if (user == null) {
            return ServerResponse.createByErrorMessage("密码错误");
        }
/**用户存在查询成功，吧得到的密码置空防止抓包，
 * 其余的信息返回，提示登录状态成功；*/

        user.setPassword(org.apache.commons.lang3.StringUtils.EMPTY);
        return ServerResponse.createBySuccess("登录成功", user);
    }


    /**
     * 2用户注册，传入user对象
     */
    public ServerResponse<String> register(User user) {
        /**注册之前对用户的名字和邮箱进行唯一性的校验*/
        /**进行代码复用，分别传入username和类型进行检室复用的是第三个方法
         * 校验name*/
        ServerResponse validResponse = this.checkValid(user.getUsername(), Const.USERNAME);
        if (!validResponse.isSuccess()) {
            //分别校验name和email username没有成功，返回信息
            return validResponse;
        }
        /**
         * 校验邮箱，checkvalid是通的方法,this为了区分还是加上，*/
        validResponse = this.checkValid(user.getEmail(), Const.EMAIL);
        if (!validResponse.isSuccess()) {
            //email没有成功
            return validResponse;
        }
        //设置用户权限为普通管理员
        user.setRole(Const.Role.ROLE_CUSTOMER);
        //MD5加密,防止扒裤！！！
        user.setPassword(MD5Util.MD5EncodeUtf8(user.getPassword()));
        //插入数据返回受影响的行数；大于==0就是没有成功
        int resultCount = userMapper.insert(user);

        if (resultCount == 0) {
            /**因为db回出现问题和其他的问题*/
            return ServerResponse.createByErrorMessage("注册失败");
        }
        /**能够走这就成了*/
        return ServerResponse.createBySuccessMessage("注册成功");
    }

    /**
     * 3 教研用户名字或者邮箱,非静态的，非重写的，this,()调用
     */
    public ServerResponse<String> checkValid(String str, String type) {
        /*
        对类型进行null判断，不为null，程序在网下走
        isnotblankd对空格进行判断空格为false；
        ## 1 **notblank回过滤空格，notempty不会**
        */
        if (org.apache.commons.lang3.StringUtils.isNotBlank(type)) {
            //开始校验
            if (Const.USERNAME.equals(type)) {
                //如果参数很多可以使用switch语句就两个参数没有必要
                //这里是username对类型进行判断，与常量进行比对，是username还是email
                int resultCount = userMapper.checkUsername(str);
                //返回受影响的行数，大于0说明用户在数据库中已经存在；提示返回信息给前端
                if (resultCount > 0) {
                    return ServerResponse.createByErrorMessage("用户名已存在");
                }
            }
            if (Const.EMAIL.equals(type)) {
                //类别为email进行受影响行数的判断
                int resultCount = userMapper.checkEmail(str);
                if (resultCount > 0) {
                    return ServerResponse.createByErrorMessage("email已存在");
                }
            }
        } else {
            //连个类别都不是参数错误；
            return ServerResponse.createByErrorMessage("参数错误");
        }
        //邮箱和用户名都没有被使用，教研成功
        return ServerResponse.createBySuccessMessage("校验成功");
    }

    /**
     * 4 根据用户名查找用户的密保问题
     */
    public ServerResponse selectQuestion(String username) {

        ServerResponse validResponse = this.checkValid(username, Const.USERNAME);
        if (validResponse.isSuccess()) {
            //用户不存在
            return ServerResponse.createByErrorMessage("用户不存在");
        }
        //根据用户名子查找用户的密保问题；
        String question = userMapper.selectQuestionByUsername(username);
        if (org.apache.commons.lang3.StringUtils.isNotBlank(question)) {
            //如果问题不为null说明成功了，找到了密保问题
            return ServerResponse.createBySuccess(question);
        }
        //没有找到哪里出了问题！！！可能用户根本就没有设置密保问题
        return ServerResponse.createByErrorMessage("找回密码的问题是空的");
    }

    /**
     * 5 验证用户的问题答案；
     */
    public ServerResponse<String> checkAnswer(String username, String question, String answer) {
        //根据用户的名字，密保问题，密保答案进行教研
        int resultCount = userMapper.checkAnswer(username, question, answer);
        //教研成功，说明问题及问题答案是这个用户的,并且是正确的

        if (resultCount > 0) {
            //生成宇宙之间都不会重复的字符串；
            String forgetToken = UUID.randomUUID().toString();
//本地缓存吧token放进来了；
            TokenCache.setKey(TokenCache.TOKEN_PREFIX + username, forgetToken);
            //吧token进行返回；
            return ServerResponse.createBySuccess(forgetToken);
        }
        //答案不对；
        return ServerResponse.createByErrorMessage("问题的答案错误");
    }


    /**
     * 6 重置密码；
     */
    public ServerResponse<String> forgetResetPassword(String username, String passwordNew, String forgetToken) {
        //对密码进行null判断；
        if (org.apache.commons.lang3.StringUtils.isBlank(forgetToken)) {
            return ServerResponse.createByErrorMessage("参数错误,token需要传递");
        }
        /**防止重置别人的密码*/
        ServerResponse validResponse = this.checkValid(username, Const.USERNAME);
        if (validResponse.isSuccess()) {
            //复用注册方法，用户不存在；
            return ServerResponse.createByErrorMessage("用户不存在");
        }
        //根据key取出缓存中的token；
        String token = TokenCache.getKey(TokenCache.TOKEN_PREFIX + username);
        if (org.apache.commons.lang3.StringUtils.isBlank(token)) {
            return ServerResponse.createByErrorMessage("token无效或者过期");
        }
//程序走到这里各种null教研已经通过；
        if (org.apache.commons.lang3.StringUtils.equals(forgetToken, token)) {
            //吧新的密码进行加密；
            String md5Password = MD5Util.MD5EncodeUtf8(passwordNew);
            //吧新的密码更新到数据库中；返回受用想的行数；
            int rowCount = userMapper.updatePasswordByUsername(username, md5Password);
//因为各种原因数据库的底层可能会出现问题；
            //受影响的行数大于0,书名更新密码成功了；
            if (rowCount > 0) {
                //提示前端密码修改成功；
                return ServerResponse.createBySuccessMessage("修改密码成功");
            }
        } else {
            return ServerResponse.createByErrorMessage("token错误,请重新获取重置密码的token");
        }
        //修改失败；
        return ServerResponse.createByErrorMessage("修改密码失败");
    }

    /**
     * 7用户已经登录，重置密码；
     */
    public ServerResponse<String> resetPassword(String passwordOld, String passwordNew, User user) {
        /**防止横向越权,要校验一下这个用户的旧密码,一定要指定是这个用户.
         * 因为我们会查询一个count(1),如果不指定id,那么结果就是true啦count>0;
         * 防止自己不在别人在自己的电脑修改密码;*/
        int resultCount = userMapper.checkPassword(MD5Util.MD5EncodeUtf8(passwordOld), user.getId());
        if (resultCount == 0) {
            return ServerResponse.createByErrorMessage("旧密码错误");
        }

        user.setPassword(MD5Util.MD5EncodeUtf8(passwordNew));
        int updateCount = userMapper.updateByPrimaryKeySelective(user);
        if (updateCount > 0) {
            return ServerResponse.createBySuccessMessage("密码更新成功");
        }
        //密码更新失败；
        return ServerResponse.createByErrorMessage("密码更新失败");
    }

    /**
     * 8 更新用户的信息，会传递email question answer....更换
     */
    public ServerResponse<User> updateInformation(User user) {
        //username是不能被更新的
        //email也要进行一个校验,校验新的email是不是已经存在,并且存在的email如果相同的话,不能是我们当前的这个用户的.
        //如果是当前的用户的会逻辑错误！！！如果用户的email不比那话的话就会存在email包错；
        //select email from user where userid!=user_id;新的教研email方法；
        int resultCount = userMapper.checkEmailByUserId(user.getEmail(), user.getId());
        if (resultCount > 0) {
            return ServerResponse.createByErrorMessage("email已存在,请更换email再尝试更新");
        }
        //进行更新信息，只是采用新的Userupdate对象；因为只是更新部分字段，其他的有的不允许更新；
        //有的没有必要更新；比如username就不允许更新；
        //感觉然并卵username???
        User updateUser = new User();
        //这样username就不会更新了；
        updateUser.setId(user.getId());
        updateUser.setEmail(user.getEmail());
        updateUser.setPhone(user.getPhone());
        updateUser.setQuestion(user.getQuestion());
        updateUser.setAnswer(user.getAnswer());
        //只是更新不为null的字段；

        int updateCount = userMapper.updateByPrimaryKeySelective(updateUser);
        if (updateCount > 0) {
            //更新成功吧user信息放入进行返回;
            //这个时候的updateUser信息中是只有更新的那几个字段，返回给前端的信息中是没有UserNmae的！！！
            return ServerResponse.createBySuccess("更新个人信息成功", updateUser);
        }
        return ServerResponse.createByErrorMessage("更新个人信息失败");
    }


    /**
     * 9 修改个人信息前的个人信息的回显
     */
    public ServerResponse<User> getInformation(Integer userId) {
        //userId是session中得到的当前用户的userId
        User user = userMapper.selectByPrimaryKey(userId);
        if (user == null) {
            //找不到当前的用户
            return ServerResponse.createByErrorMessage("找不到当前用户");
        }
        /**找到了当前的用户，要把密码信息置空返回给前端；*/
        user.setPassword(org.apache.commons.lang3.StringUtils.EMPTY);
        return ServerResponse.createBySuccess(user);

    }


    //backend

    /**
     * 校验是否是管理员
     *
     * @param user
     * @return
     */
    public ServerResponse checkAdminRole(User user) {
        /**因为role是Integer,所以intValue()*/
        if (user != null && user.getRole().intValue() == Const.Role.ROLE_ADMIN) {
            /**在interface里面的变量都是public static final 的。所以你可以这样写：
             public static final int i=10;
             或则
             int i=10；（可以省略掉一部分）*/
            return ServerResponse.createBySuccess();
        }
        return ServerResponse.createByError();
    }

    /**
     * 自己的校验是不是管理员
     */

    public ServerResponse myCheckAdminRole(User user) {
        if (user != null && user.getRole().intValue() == Const.Role.ROLE_ADMIN) {
            return ServerResponse.createBySuccess();
        }
        return ServerResponse.createByError();
    }


}
