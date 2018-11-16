package teststh;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * the class is create by @Author:oweson
 *
 * @Date：2018/6/17 0017 11:16
 */
public class TestCreatAFileInE {
    public static void main(String[] args) throws IOException {
        String pre = UUID.randomUUID().toString().substring(0, 9).toUpperCase();
        String s = "asas.asasasa.jpg";
        String sux = s.substring(s.lastIndexOf("."));
        File file = new File("E:\\AAAAAAAAAAAAAAAA\\", pre + sux);
        file.createNewFile();
        System.out.println(file.exists() + "存在吗》爱吃饭了");
    }
}
