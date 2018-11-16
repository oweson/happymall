package top.sun520.dao;

import com.mmall.dao.UserMapper;
import com.mmall.pojo.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * the class is create by @Author:oweson
 *
 * @Date：2018/6/11 0011 20:59
 */
public class UserMapperTest extends BaseClass {
    @Autowired
    UserMapper userMapper;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void count() {
        int count = userMapper.count();
        System.out.println(count);


    }

    @Test
    public void deleteByPrimaryKey() {
    }

    @Test
    public void insert() {
    }

    @Test
    public void insertSelective() {
    }

    @Test
    public void selectByPrimaryKey() {
        User user = userMapper.selectByPrimaryKey(1);
        System.out.println(user);

    }

    @Test
    public void updateByPrimaryKeySelective() {
    }

    @Test
    public void updateByPrimaryKey() {
    }

    @Test
    public void checkUsername() {
    }

    @Test
    public void checkEmail() {
    }

    @Test
    public void selectLogin() {
    }

    @Test
    public void selectQuestionByUsername() {
    }

    @Test
    public void checkAnswer() {
    }

    @Test
    public void updatePasswordByUsername() {
    }

    @Test
    public void checkPassword() {
    }

    @Test
    public void checkEmailByUserId() {
    }
}