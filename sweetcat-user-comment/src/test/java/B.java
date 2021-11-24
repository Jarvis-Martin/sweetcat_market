import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/23-21:14
 * @version: 1.0
 */
@Getter
@Setter
@ToString
public class B extends A{
    private String Bname;
    public B(Integer id) {
        super(id);
    }

    public static <C extends A> C fun() {
        return (C) new B(123);
    }

    public static void main(String[] args) {
        System.out.println(B.fun().getId());
    }

}
