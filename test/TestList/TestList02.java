package TestList;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * the class is create by @Author:oweson
 *
 * @Date：2018/11/1 0001 15:11
 */
public class TestList02 {
    public static void main(String[] args) {
        ArrayList<Integer> list = Lists.newArrayList();
        list.add(1);
        list.add(1);
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()){
            Integer next = iterator.next();
            System.out.println(next);
        }
    }
}
