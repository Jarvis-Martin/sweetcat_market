package com.sweetcat.commons.responsedto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: Coder_Jarvis
 * @Description:
 * @Date: 2021-10-2021/10/15-20:46
 * @Version: 1.0
 */
@Data
public class ResponseDTO implements Serializable {
    private String errorCode;
    private String errorMessage;
    private String tip;
    private Object data;

    public ResponseDTO(String errorCode, String errorMessage, String tip, Object data) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.tip = tip;
        this.data = data;
    }
}
