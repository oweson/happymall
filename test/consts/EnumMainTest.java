package consts;

/**
 * the class is create by @Author:oweson
 *
 * @Date：2019/1/8 0008 18:32
 */
public class EnumMainTest {
    public static void main(String[] args) {
        System.out.println(EnumTest.SUCCESS.getCode());
        EnumTest.SUCCESS.setCode(2100);
        System.out.println(EnumTest.SUCCESS.getCode());
    }
}
