package test.tools;

import com.google.common.base.Splitter;
import org.junit.Test;

import java.util.List;

/**
 * the class is create by @Author:oweson
 *
 * @Date：2018/9/3 0003 8:17
 */
public class TestSplitInGuava {
    public static void main(String[] args) {
        try {
            int a = 100;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
    }

    @Test
    public void testDemo1() {
        if (1 == 1) {

        }
        String s = "hello,ppx,let,go";
        List<String> list = Splitter.on(",").splitToList(s);
        for (String s1 : list) {
            System.out.println(s1);

        }

    }
}
