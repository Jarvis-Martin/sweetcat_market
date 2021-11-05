package com.sweetcat.user_info.infrastructure.service.sms_service;

import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.sweetcat.commons.exception.SmsSendException;
import com.sweetcat.commons.util.SmsUtils;
import org.springframework.stereotype.Service;

import javax.sql.rowset.serial.SerialException;

/**
 * @Author: Coder_Jarvis
 * @Description:
 * @Date: 2021-10-2021/10/26-14:34
 * @Version: 1.0
 */
@Service
public class SmsService {

    public void send(String code) throws ServerException, ClientException {
        SmsUtils.send(code);
    }
}
