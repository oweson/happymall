package com.mmall.dao;

import com.mmall.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 当你使用了使用@Param注解来声明参数时，如果使用 #{} 或 ${} 的方式都可以。
 * 当你不使用@Param注解来声明参数时，必须使用使用 #{}方式。如果使用 ${} 的方式，会报错。
 * paramMap由于value是Object，所以我前面提到的问题，传子类的特定属性也可以通过就可以解释清楚了，mybats的@param接收的参数根本就是object，是在运行时确定它的类型，所以使用@param可以偷懒，直接写成List<People> getAllPeople(@Param("vo") Object vo,@Param("id")Object id)，也是正常工作，但是不建议这样操作，会增加代码的阅读困难；
 * 用注解来简化xml配置的时候,@Param注解的作用是给参数命名,参数命名后就能根据名字得到参数值,正确的将参数传入sql语句中
 */
public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    /**
     * 1 验证用户名是否存在，保证唯一性
     */
    int checkUsername(String username);

    /**
     * 2 验证用户邮箱是否存在，保证唯一性
     */


    int checkEmail(String email);

    /**
     * 3 多个参数的时候要使用@param注解，sql里面的语句要对应（）l里面的名字；防止不一样
     */
    User selectLogin(@Param("username") String username, @Param("password") String password);

    /**
     * 4 根据姓名查找哦密保问题
     */
    String selectQuestionByUsername(String username);

    /**
     * 5 验证密保是否正确
     */

    int checkAnswer(@Param("username") String username, @Param("question") String question, @Param("answer") String answer);

    /**
     * 6 根据用户名更新密码，返回受影响的行数
     */
    int updatePasswordByUsername(@Param("username") String username, @Param("passwordNew") String passwordNew);

    int checkPassword(@Param(value = "password") String password, @Param("userId") Integer userId);

    int checkEmailByUserId(@Param(value = "email") String email, @Param(value = "userId") Integer userId);

    int count();

    List<User> list();
}