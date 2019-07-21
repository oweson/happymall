package utils;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.primitives.Ints;

import java.util.List;

/**
 * the class is create by @Author:oweson
 *
 * @Date：2019/1/12 0012 11:46
 */
public class StringTest {
    public static void main(String[] args) {
        Joiner joiner = Joiner.on(";").skipNulls();
        String join = joiner.join("hello", "worl");
        System.out.println(join);
        String join1 = Joiner.on(",").join("ppx", "asasas");
        System.out.println(join1);
        System.out.println("-------------------------");
        Iterable<String> split = Splitter.on(",").omitEmptyStrings().trimResults().split(" 1,2,3,,  ");
        for (String s : split) {
            System.out.print(s);
            
        }
        List<Integer> list = Ints.asList(1, 2, 3);
        for (Integer integer : list) {
            System.out.println(integer);

        }

    }
}
