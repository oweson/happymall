package teststh;

import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;

/**
 * the class is create by @Author:oweson
 *
 * @Date：2018/6/13 0013 19:59
 */
public class TestColleactionUtils {
    public static void main(String[] args) {
        //判断是否为null，或者元素的个数为0
        boolean empty = CollectionUtils.isEmpty(new ArrayList());
        System.out.println(empty);
    }
}
