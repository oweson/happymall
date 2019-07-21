package teststh;

/**
 * the class is create by @Author:oweson
 *
 * @Date：2018/6/14 0014 17:40
 */
public class TestIndexOf {
    public static void main(String[] args) {
        String s = "ab nm  , , ,login.jsp";
        /**查找子串在string 第一次出现的下表，找不到返回-1*/
        int login = s.indexOf("login.jsp");
        System.out.println(login);
    }
}
