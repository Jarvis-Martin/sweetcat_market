import lombok.Getter;
import lombok.Setter;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/23-21:13
 * @version: 1.0
 */
@Getter
@Setter
public class A {
    private Integer id;
    private String name;

    public A(Integer id) {
        this.id = id;
    }
}
