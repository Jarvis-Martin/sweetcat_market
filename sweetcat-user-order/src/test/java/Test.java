import lombok.Getter;
import lombok.Setter;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-12-2021/12/2-18:39
 * @version: 1.0
 */
public class Test {
    public static void main(String[] args) {
        A a = new A("123");
        try {

            A b = a.clone();
            System.out.println(b.getA());
            b.setA("789");
            System.out.println(b.getA());
            System.out.println(a.getA());
            System.out.println(a.equals(b));
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }

    @Getter
    @Setter
    static class A implements Cloneable{
        public String a;

        public A(String a) {
            this.a = a;
        }

        @Override
        protected A clone() throws CloneNotSupportedException {
            return ((A) super.clone());
        }
    }
}
