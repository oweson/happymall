package teststh;

/**
 * the class is create by @Author:oweson
 *
 * @Date：2018/10/12 0012 17:03
 */
public class ThisTest {
    public void say(){
        System.out.println("hello");
    }
    public void sleep(){
        this.say();
    }
    public  void look(){
        say();
    }

    public static void main(String[] args) {
        ThisTest thisTest = new ThisTest();
        thisTest.sleep();
        thisTest.look();

    }
}
