package teststh;

/**
 * the class is create by @Author:oweson
 *
 * @Date：2018/6/14 0014 13:33
 */
public class TestSubString {
    public static void main(String[] args) {
        String a = "a.c.b.apj";
        /**截取上传文件的后缀名*/
        /**从后面开始截取*/
        String substring = a.substring(a.lastIndexOf(".") + 1);
        System.out.println(substring);

    }
}
