package top.sun521.test;

import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;

/**
 * the class is create by @Author:oweson
 *
 * @Date：2018/6/25 0025 15:43
 */
public class Testgoava {
    public static void main(String[] args) {
        int i = CollectionUtils.maxSize(new ArrayList());
        System.out.println(i);
        boolean empty = CollectionUtils.isEmpty(new ArrayList());
        System.out.println(empty);
    }
}
