package utils;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;

import java.util.HashSet;

/**
 * the class is create by @Author:oweson
 *
 * @Date：2019/1/12 0012 11:31
 */
public class CollectionTest {
    public static void main(String[] args) {
        ImmutableSet<Integer> of = ImmutableSet.of(1, 2, 3);
        for (Integer integer : of) {
            System.out.println(integer);

        }
        HashSet<Integer> hashSet = Sets.newHashSet(1, 2, 3, 4);
        HashSet<Integer> hashSet1 = Sets.newHashSet(0);
        Sets.SetView<Integer> union = Sets.union(hashSet, hashSet1);
        Sets.SetView<Integer> difference = Sets.difference(hashSet, hashSet1);
        
        for (Integer integer : difference) {
            System.out.println("差集合是：" + integer + "  ");

        }
        for (Integer integer : union) {
            System.out.print("并集合是："+integer + " ");

        }
    }
}
