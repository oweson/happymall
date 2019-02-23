package utils;

import com.google.common.primitives.Chars;
import com.google.common.primitives.Ints;

import java.util.List;

/**
 * the class is create by @Author:oweson
 *
 * @Date：2019/2/23 0023 19:20
 */
public class GuavaIntTest {
    public static void main(String[] args) {
        List<Integer> list = Ints.asList(1, 2, 3);
        List<Character> list1 = Chars.asList('a', 'b');
        for (Integer integer : list) {
            System.out.println(integer);

        }
    }
}
