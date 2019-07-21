package testsql;

/**
 * the class is create by @Author:oweson
 *
 * @Date：2018/8/28 0028 11:08
 */
public class TestAppendSql {
    public static void demo1(String name) {
        name = new StringBuilder().append("%").append(name).append("%").toString();
        System.out.println(name);

    }

    public static void main(String[] args) {
        demo1("ppx");
    }
}
