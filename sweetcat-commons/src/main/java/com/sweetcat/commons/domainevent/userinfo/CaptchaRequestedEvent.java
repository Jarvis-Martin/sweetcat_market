package com.sweetcat.commons.domainevent.userinfo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

/**
 * @Author: Coder_Jarvis
 * @Description:
 * @Date: 2021-11-2021/11/5-20:04
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CaptchaRequestedEvent {
    private String phone;

    /**
     * 事件发生时间
     */
    private Long occurOn;

}
