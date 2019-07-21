package utils;


import com.google.common.math.IntMath;
import com.google.zxing.common.detector.MathUtils;

/**
 * the class is create by @Author:oweson
 *
 * @Date：2019/2/23 0023 19:43
 */
public class GuavaMathTest {
    public static void main(String[] args) {
        System.out.println(1000000000*2100000000);
        /**防止数据的溢出；*/
        int i = IntMath.checkedMultiply(10000000, 2100000000);
        System.out.println(i);

    }
}
