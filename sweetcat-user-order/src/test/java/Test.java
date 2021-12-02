import java.util.HashSet;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-12-2021/12/2-18:39
 * @version: 1.0
 */
public class Test {
    public static void main(String[] args) {
        A a = new A("111");
        A a1 = new A("111");
        HashSet<A> as = new HashSet<>();
        as.add(a);
        as.add(a1);
        System.out.println(as);
    }

    static class A {
        public String a;

        public A(String a) {
            this.a = a;
        }
    }
}
