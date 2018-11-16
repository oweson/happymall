package teststh;

/**
 * the class is create by @Author:oweson
 *
 * @Date：2018/6/14 0014 14:39
 */
public class TestDoubleValue {
    public static void main(String[] args) {

        Double aDouble = Double.valueOf("100");
        double v = aDouble.doubleValue();
        System.out.println(v);//返回原始类型
        System.out.println(aDouble.equals(210));//返回包装类型的数据类型；

    }
}
