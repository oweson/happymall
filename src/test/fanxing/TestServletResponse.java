package fanxing;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;

/**
 * the class is create by @Author:oweson
 *
 * @Date：2018/6/10 0010 22:03
 */
public class TestServletResponse {
    public static void main(String[] args) {
        boolean notEmpty1 = CollectionUtils.isNotEmpty(new ArrayList(100));
        System.out.println(notEmpty1);
        boolean notEmpty = StringUtils.isNotEmpty("");
        System.out.println(notEmpty);
        com.mmall.common.ServerResponse<String> bySuccess = com.mmall.common.ServerResponse.createBySuccess();
        com.mmall.common.ServerResponse<String > bySuccessMessage = com.mmall.common.ServerResponse.createBySuccessMessage("true........");
        System.out.println(bySuccessMessage.getMsg()+bySuccessMessage.getStatus());
        int status = bySuccess.getStatus();
        System.out.println(status);
        System.out.println(bySuccess);
    }
}
