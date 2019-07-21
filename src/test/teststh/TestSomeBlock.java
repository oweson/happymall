package teststh;

/**
 * the class is create by @Author:oweson
 *
 * @Date：2018/6/13 0013 14:42
 */
public class TestSomeBlock {
    static {
        System.out.println("i m static");
    }
    {
        System.out.println("im.................");
    }
    private TestSomeBlock(){
        System.out.println("im constroct");
    }

    public static void main(String[] args) {
        /**运行顺序static代码块>代码块>构造代码块*/
        /**static代码块仅仅一次；空的代码块每次都会运行*/
        new TestSomeBlock();
        new TestSomeBlock();
    }
}
