package fanxing;

/**
 * the class is create by @Author:oweson
 *
 * @Date：2018/6/11 0011 18:07
 */
public enum ResponseCode {
    SUCCESS(1,"SUCCESS"),ERROR(2,"ERROR");
    int code;
    String desc;

    ResponseCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
