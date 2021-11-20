import com.baidu.aip.contentcensor.AipContentCensor;
import com.baidu.aip.util.Util;
import org.json.JSONObject;

import java.io.IOException;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/19-22:02
 * @version: 1.0
 */
public class TestBaiduApi {
    public static final String APP_ID = "25200179";
    public static final String API_KEY = "bguxn8QsfDzhPNL15j6un8P5";
    public static final String SECRET_KEY = "cF1lE79S2SpREd2YOP7MHBgg0DG00vQs";

    public static void main(String[] args) throws IOException {
        AipContentCensor client = new AipContentCensor(APP_ID, API_KEY, SECRET_KEY);
        // 调用接口
        String imgPath = "C:\\Users\\86152\\Pictures\\Camera Roll\\asset1.jpg";
        byte[] bytes = Util.readFileByBytes(imgPath);
        JSONObject response = client.imageCensorUserDefined(bytes, null);
        System.out.println(response.toString());
    }
}
