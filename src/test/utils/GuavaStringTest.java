package utils;

import com.google.common.base.Splitter;

import java.util.Iterator;

/**
 * the class is create by @Author:oweson
 *
 * @Date：2019/2/23 0023 19:23
 */
public class GuavaStringTest {
    public static void main(String[] args) {
        Splitter.on(',')

                .trimResults()  //移除结果字符串的前导空白和尾部空白

                .omitEmptyStrings() //从结果中自动忽略空字符串

                .limit(2)  //限制拆分出的字符串数量

                .split("alex,,45,   tianshan road.,201301");
        Iterable<String> split = Splitter.on(",")
                .trimResults().omitEmptyStrings().limit(2).split("ppx01  ,1,2,     ,  9" );
        Iterator<String> iterator = split.iterator();
        while (iterator.hasNext()) {
            String next = iterator.next();
            System.out.println(next);
        }

    }
}
