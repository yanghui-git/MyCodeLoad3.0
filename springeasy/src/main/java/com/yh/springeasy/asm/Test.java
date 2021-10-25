

/**
 * https://zhuanlan.zhihu.com/p/94498015?utm_source=wechat_timeline
 *
 * asm 技术初探
 */
public class Test {

    {
        System.out.println(6666);
    }

    private int num1 = 1;
    public static int NUM1 = 100;
    public int func(int a,int b){
        return add(a,b);
    }
    public int add(int a,int b) {
        return a+b+num1;
    }
    public int sub(int a, int b) {
        return a-b-NUM1;
    }

    public static void main(String[] args) {
        System.out.println(new Test().add(3,5));
    }
}