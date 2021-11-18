import com.google.gson.Gson;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/18-12:45
 * @version: 1.0
 */
public class Main {
    public static void main(String[] args) {
        Parent children = new Children();
        Gson gson = new Gson();
        children.setParentStr("parent string");
        System.out.println(gson.toJson(children));
    }
}
