package test.tools;

import org.apache.commons.lang.StringUtils;

/**
 * the class is create by @Author:oweson
 *
 * @Date：2018/8/23 0023 16:17
 */
public class TestStringUtilsEmpty {
    public void demo1() {
        Student s = new Student(21, "ppx");
        /**把信息清空，不会nullpointexception*/
        s.setName(StringUtils.EMPTY);
        System.out.println(s.getName() + "jjj");


    }

    public static void main(String[] args) {
        new TestStringUtilsEmpty().demo1();
        Student s = new Student(21, "ppx");


    }
}
