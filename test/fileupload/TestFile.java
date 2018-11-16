package fileupload;

import java.io.File;
import java.util.UUID;

/**
 * the class is create by @Author:oweson
 *
 * @Date：2018/8/28 0028 12:57
 */
public class TestFile {
    public static void main(String[] args) {
        String fileNmae = "aaaa.aaaa.aaaa.jpg";
        System.out.println(fileNmae.lastIndexOf("."));
        String substring = fileNmae.substring(fileNmae.lastIndexOf(".") + 1);
        String path = "e:/aaaa";
        File f = new File(path);
        System.out.println(f.exists());
        if (!f.exists()) {
            f.setWritable(true);
            f.mkdirs();
        }
        String ss = UUID.randomUUID().toString().substring(0, 9).toString() + substring;
        System.out.println(ss);


    }
}
