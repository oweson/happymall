package utils;

import com.google.common.base.Joiner;
import com.google.common.base.Strings;
import com.google.common.collect.*;
import org.apache.commons.collections.CollectionUtils;

import java.lang.reflect.Constructor;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * the class is create by @Author:oweson
 *
 * @Date：2019/1/10 0010 21:18
 */
public class GuavaTest {
    public static void main(String[] args) {
        ArrayList<Object> objects = Lists.newArrayList();
        HashSet<Object> objects1 = Sets.newHashSet();
        HashMap<Object, Object> objectObjectHashMap = Maps.newHashMap();
        boolean nullOrEmpty = Strings.isNullOrEmpty("");
        System.out.println(nullOrEmpty);
        List<Object> objectList = Collections.synchronizedList(objects);
        boolean equalCollection = CollectionUtils.isEqualCollection(objectList, objects1);
        System.out.println(equalCollection);
        ConcurrentHashMap<String, Integer> stringIntegerConcurrentHashMap = new ConcurrentHashMap<>();
        /**创建不可变集合
         先理解什么是immutable(不可变)对象

         1.在多线程操作下，是线程安全的。

         2.所有不可变集合会比可变集合更有效的利用资源。

         3.中途不可改变*/
        ImmutableList<Integer> of = ImmutableList.of(1, 2, 3);
        ImmutableSet<Integer> of1 = ImmutableSet.of(1, 2, 3);
        ImmutableMap<Integer, Integer> of2 = ImmutableMap.of(1, 1, 2, 3);
        HashMultiset<Object> objects2 = HashMultiset.create();
        objectList.add(1);
        objectList.add(1);
        objectList.add(1);
        String join = Joiner.on("_").join(objectList);
        System.out.println(join);
        String lover = Strings.repeat("lover", 2);
        System.out.println(lover);
        Constructor<?>[] constructors = Strings.class.getConstructors();
        Constructor<?>[] clone = constructors.clone();



    }
}
