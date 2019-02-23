package utils;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

import java.util.ArrayList;

/**
 * the class is create by @Author:oweson
 *
 * @Date：2019/2/23 0023 19:31
 */
public class GuavaListImmutableTest {
    public static void main(String[] args) {
        ArrayList<Integer> list = Lists.newArrayList();
        list.add(1);
        list.add(2);
        ImmutableList<Integer> copyOf = ImmutableList.copyOf(list);
        list.remove(1);
        System.out.println(copyOf);
        System.out.println(list);
    }
}
