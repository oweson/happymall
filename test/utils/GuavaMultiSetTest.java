package utils;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;

import java.util.Arrays;

/**
 * the class is create by @Author:oweson
 *
 * @Date：2019/2/23 0023 19:36
 */
public class GuavaMultiSetTest {
    public static void main(String[] args) {
        Multiset<Object> string = HashMultiset.create();
        String s="p,p,q,";
        final boolean b = string.addAll(Arrays.asList(1, 1, 1, 2, 3, 4));
    }
}
