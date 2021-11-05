package com.sweetcat.user_info.domain.user.event;

import java.time.Instant;

/**
 * @Author: Coder_Jarvis
 * @Description:
 * @Date: 2021-11-2021/11/5-20:04
 * @Version: 1.0
 */
public class CaptchaRequestedEvent {
    private String phone;
    private Long createTime;

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getPhone() {
        return phone;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public String phone() {
        return phone;
    }

    public Long createTime() {
        return createTime;
    }

    public CaptchaRequestedEvent(String phone) {
        this.phone = phone;
        this.createTime = Instant.now().toEpochMilli();
    }

    public CaptchaRequestedEvent(String phone, Long createTime) {
        this.phone = phone;
        this.createTime = createTime;
    }

    public CaptchaRequestedEvent() {}

}
