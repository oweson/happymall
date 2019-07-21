package teststh;

import lombok.Data;
import org.apache.commons.lang.StringUtils;

/**
 * the class is create by @Author:oweson
 *
 * @Date：2018/6/12 0012 20:19
 */
@Data
public class TestStringUtils {
    int age;
    String name;
    public static void main(String[] args) {

        TestStringUtils t = new TestStringUtils();
        t.setName("ppx");
        t.setName(" ");
        //""和" "都不会打印什么
        //没有复制会打印null
           t.setName("ppx");
        t.setName(StringUtils.EMPTY);//=""
        System.out.println(t.getName());

    }
}
