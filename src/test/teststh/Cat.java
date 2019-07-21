package teststh;

import com.mmall.dao.UserMapper;
import com.mmall.pojo.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * the class is create by @Author:oweson
 *
 * @Date：2018/6/10 0010 16:49
 */
public class Cat extends BaseClass {
    @Autowired
    UserMapper userMapper;
    @Test
    public void  testSB(){
        System.out.println("i'm tired..............");
    }
    @Test
    public void testUser(){
        User user = userMapper.selectByPrimaryKey(1);
        System.out.println(user);


    }
}
