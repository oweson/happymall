package test.tools;

import com.sun.org.apache.xerces.internal.dom.PSVIAttrNSImpl;
import lombok.Data;
import org.springframework.beans.BeanUtils;

/**
 * the class is create by @Author:oweson
 *
 * @Date：2018/10/29 0029 19:19
 */
@Data
public class Person {
    private Integer ids;
    private Integer age;
    private String name;

    public static void main(String[] args) {
        
        /**属性值复制的测试，
         * 字段地名字必须一样,
         * 不一样会null填充*/
        Student s = new Student();
        Person p = new Person();
        p.setAge(23);
        p.setName("ppx");
        p.setIds(101010);
        BeanUtils.copyProperties(p, s);
        System.out.println(s.toString());
    }
}
