package fanxing;

/**
 * the class is create by @Author:oweson
 *
 * @Date：2018/6/11 0011 17:40
 */
public class FanxingFunTest<T> {
    public static <T> int add(T t){
        if(t instanceof String){
            String s = t instanceof String ? ((String) t) : null;
            s+="hello,world";
            System.out.println(s);


        }
        else if(t instanceof Integer){
            int sum=100;
            System.out.println(t+" "+sum);
        }
        return 100;
    }
    public    <T> T funs(T t){
        return t;

    }
    public static void main(String[] args) {
        int add = add(100);
        int ppx = add("ppx");
        System.out.println(add+ppx);
        FanxingFunTest<Integer> fanxingFunTest = new FanxingFunTest();
        Integer funs = fanxingFunTest.funs(100);
        System.out.println(funs);
        FanxingFunTest<String> fanxingFunTest1 = new FanxingFunTest();
        String funs1 = fanxingFunTest.funs("100");
        System.out.println(funs1);


    }
}
