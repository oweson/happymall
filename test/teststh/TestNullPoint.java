package teststh;

/**
 * the class is create by @Author:oweson
 *
 * @Date：2018/6/12 0012 16:08
 */
public class TestNullPoint {
    public static void main(String[] args) {
        String a=null;
        if("abc".equalsIgnoreCase(a)){
            /**这样写可以避免nullpointexception;*/
            System.out.println("hjhsjASHksaaSa");
        }
        if (a.equalsIgnoreCase("abs")){

        }
    }
}
