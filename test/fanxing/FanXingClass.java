package fanxing;

import lombok.Data;

/**
 * the class is create by @Author:oweson
 *
 * @Date：2018/6/25 0025 16:17
 */
@Data

public class FanXingClass<T> {
    private String msg;
    private T t;
    private Integer code;

    public FanXingClass(Integer code) {
        this.code = code;
    }

    public FanXingClass(String msg) {
        this.msg = msg;
    }

    public FanXingClass(String msg, Integer code) {
        this.msg = msg;
        this.code = code;
    }

    public FanXingClass(String msg, T t, Integer code) {
        this.msg = msg;
        this.t = t;
        this.code = code;
    }

    public static <T> FanXingClass<T> createBySuccess() {
        return new FanXingClass<T>(MyConst.SUCCESS.getCode());
    }

    public static <T> FanXingClass<T> createError(String msg) {
        return new FanXingClass<T>(msg, MyConst.ERROR.getCode());
    }

    public static void main(String[] args) {
        FanXingClass<Object> bySuccess = createBySuccess();
        Integer code = bySuccess.getCode();
        System.out.println(code);
        System.out.println("--------------------------------");
        FanXingClass<Object> error = createError("eroor.............");
        Integer code1 = error.getCode();
        String msg = error.getMsg();
        System.out.println(code1 + "   " + msg);
    }
}
