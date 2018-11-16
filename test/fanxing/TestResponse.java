package fanxing;

import com.mmall.common.ResponseCode;

/**
 * the class is create by @Author:oweson
 *
 * @Date：2018/6/10 0010 21:58
 */
public class TestResponse {
    public static void main(String[] args) {
        /**a=1000hahahahaha*/
        int a = 1000;

        int code1 = ResponseCode.SUCCESS.getCode();
        a++;
        System.out.println(a);
        String desc = fanxing.ResponseCode.SUCCESS.getDesc();
        System.out.println(code1 + "    " + desc);

        int code = com.mmall.common.ResponseCode.SUCCESS.getCode();
        com.mmall.common.ResponseCode success = ResponseCode.SUCCESS;
        System.out.println(success);
        System.out.println(code);
    }
}
