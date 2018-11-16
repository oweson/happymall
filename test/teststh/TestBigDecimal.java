package teststh;

import org.junit.Test;

import java.math.BigDecimal;

/**
 * the class is create by @Author:oweson
 *
 * @Date：2018/6/14 0014 14:21
 */
public class TestBigDecimal {
    @Test
    public void test01() {
        /**精度丢失*/
        System.out.println(0.01 + 0.05);
        System.out.println(1.0 - 0.21);
    }

    @Test
    public void test02() {
        /**精度更乱*/
        BigDecimal bigDecimal = new BigDecimal(0.01);
        BigDecimal bigDecimal1 = new BigDecimal(0.02);
        System.out.println(bigDecimal.add(bigDecimal1));
    }

    @Test
    public void test03() {
        /**进行精度计算的时候一定要使用string的构造器*/
        BigDecimal bigDecimal = new BigDecimal("0.01");
        BigDecimal bigDecimal1 = new BigDecimal("0.01");
        System.out.println(bigDecimal.add(bigDecimal1));
    }

    public static BigDecimal div(double a, double b) {
        BigDecimal bigDecimal = new BigDecimal(Double.toString(a));
        BigDecimal bigDecimal2 = new BigDecimal(Double.toString(b));
        return bigDecimal.divide(bigDecimal2, 2, BigDecimal.ROUND_HALF_UP);
    }

    public static void main(String[] args) {
        BigDecimal div = div(100.01090909, 10.01);
        System.out.println(div);
    }
}
