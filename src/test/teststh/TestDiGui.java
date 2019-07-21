package teststh;

/**
 * the class is create by @Author:oweson
 *
 * @Date：2018/6/13 0013 13:53
 */
public class TestDiGui {

    public static int digui(int p){
        if(p<0){
            return 0 ;
        }
        return p*digui(p-1);
    }
    public static int Factorial(int n) {
        if (n < 0) {
            System.out.println("无效输入，请重新输入！");
            return 0;
        } else if (n == 1 || n == 0) {
            return 1;
        } else
            return n * Factorial(n - 1);
    }
    public static int getAddNum(int num) {
        if (num == 0) {
            return num;
        } else
            return num + getAddNum(num - 1);
    }
    public static void main(String[] args) {
        int addNum = getAddNum(10);
        System.out.println(addNum);
        int factorial = Factorial(3);
        System.out.println(factorial);
        int digui = digui(10);
        System.out.println(digui);

    }
}
