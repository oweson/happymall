package teststh; /**
 * the class is create by @Author:oweson
 *
 * @Date：2018/6/10 0010 16:46
 */
/*用来配置spring junit启动加载配置*/

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 配置spring和junit整合，junit启动时加载springIOC容器 spring-test,junit
 */

@RunWith(SpringJUnit4ClassRunner.class)
// 告诉junit spring配置文件
@ContextConfiguration({ "classpath:applicationContext.xml" })
public class BaseClass {
}
