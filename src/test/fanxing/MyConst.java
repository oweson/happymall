package fanxing;

import lombok.Data;

/**
 * the class is create by @Author:oweson
 *
 * @Date：2018/6/25 0025 16:23
 */

public enum MyConst {
    SUCCESS(1, "SUCCESS"), ERROR(0, "ERROR");
    private final Integer code;
    private final String desc;


    MyConst(Integer code, String desc) {
        this.code = code;
        this.desc = desc;

    }


    public Integer getCode() {
        return code;
    }


    public String getDesc() {
        return desc;
    }


}
