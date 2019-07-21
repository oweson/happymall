package consts;

/**
 * the class is create by @Author:oweson
 *
 * @Date：2019/1/8 0008 18:11
 */
public enum EnumTest {
    SUCCESS(0,"成功"),ERROR(1,"ds");

    private  Integer code;
    private  String message;

    EnumTest( Integer code,String message) {
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return message;
    }



    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
