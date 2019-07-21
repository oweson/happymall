package utils;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;

/**
 * the class is create by @Author:oweson
 *
 * @Date：2018/10/12 0012 16:08
 */
public class CollectionsTest {
    public static void say(){
        boolean empty = CollectionUtils.isEmpty(Arrays.asList(1, 2, 3, 4, 5));
        boolean notBlank = StringUtils.isNotBlank("");
        boolean notBlank02 = StringUtils.isNotBlank(" ");
        System.out.println(notBlank+"  "+notBlank02);
        System.out.println(empty);
    }
    public static void main(String[] args) {
        say();

    }
}
