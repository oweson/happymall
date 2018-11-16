package consts;

/**
 * the class is create by @Author:oweson
 *
 * @Date：2018/8/29 0029 17:00
 */
public class TestConst {
    public interface Dog {
        Integer a = 100;
    }

    public enum Cat {
        BUAUTY(1, "GOOD"), UGLY(0, "BAD");
        private Integer code;
        private String desc;

        Cat(Integer code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }

    public static void main(String[] args) {
        System.out.println(Cat.BUAUTY.getCode());
        System.out.println(Dog.a);
    }
}
