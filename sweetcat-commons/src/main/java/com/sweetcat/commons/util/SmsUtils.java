package com.sweetcat.commons.util;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import org.springframework.beans.factory.annotation.Value;

/**
 * @Author: Coder_Jarvis
 * @Description:
 * @Date: 2021-10-2021/10/26-12:28
 * @Version: 1.0
 */
public class SmsUtils {

    @Value("${access-key-id}")
    private static String accessKeyId;
    @Value("${access-secret}")
    private static String accessSecret;

    public static void send(String code) throws ServerException, ClientException {
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "<accessKeyId>", "<accessSecret>");
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.ap-southeast-1.aliyuncs.com");
        request.setSysVersion("2018-05-01");
        request.setSysAction("SendMessageWithTemplate");
        request.putQueryParameter("RegionId", "cn-hangzhou");

        CommonResponse response = client.getCommonResponse(request);
        System.out.println(response.getData());

    }
}
