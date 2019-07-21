package fanxing;

import com.google.common.collect.Lists;
import lombok.Data;

import java.util.ArrayList;

//<editor-fold desc="Description">
//region Description

/**
 * the class is create by @Author:oweson
 *
 * @Date：2018/6/11 0011 17:49
 */
@Data
/**生成get  and set方法*/
public class ServerResponseTest<T> {
    int status;
    String msg;
    /**
     * data可以是任意的数据
     */
    T data;

    public ServerResponseTest() {
        //1
    }

    public ServerResponseTest(int status) {
        //1返回状态
        this.status = status;
    }

    public ServerResponseTest(int status, T data) {
        //2返回状态和数据
        this.status = status;
        this.data = data;
    }

    public ServerResponseTest(int status, String msg, T data) {
        //3 状态，提示信息，数据
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public ServerResponseTest(int status, String msg) {
        //4状态，提示信息；
        this.status = status;
        this.msg = msg;
    }

    public static <T> ServerResponseTest createSuccess() {
        //1成功返回成功状态码
        return new ServerResponseTest(ResponseCode.SUCCESS.getCode());
    }

    public static <T> ServerResponseTest createBySuccessMessage(String msg) {
        //2创建成功返回成功状态码和提示信息
        return new ServerResponseTest(ResponseCode.SUCCESS.getCode(), msg);
    }

    public static <T> ServerResponseTest createBySuccess(T data) {
        return new ServerResponseTest(ResponseCode.SUCCESS.getCode(), data);

    }

    public static void main(String[] args) {

        ArrayList<Integer> integers = Lists.newArrayList(1, 2, 3, 4, 5, 6);
        ServerResponseTest bySuccess = createBySuccess(integers);
        System.out.println(bySuccess.getData().toString());
        ServerResponseTest success = createSuccess();
        ServerResponseTest you_are_right = createBySuccessMessage("you are right");
        System.out.println(success.getStatus());
        System.out.println(you_are_right.getMsg());
       /* System.out.println(new ServerResponseTest<String>());
        System.out.println(new ServerResponseTest<String>().getData());*/
    }

}

