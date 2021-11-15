package com.sweetcat.credit.domain.checkinlog.entity;

import lombok.Getter;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/14-16:20
 * @version: 1.0
 */
@Getter
public class CheckedInUser {
    /**
     * 已签到用户的id
     */
    private Long checkedInUserId;

    public void setCheckedInUserId(Long checkedInUserId) {
        this.checkedInUserId = checkedInUserId;
    }

    public CheckedInUser(Long checkedInUserId) {
        this.checkedInUserId = checkedInUserId;
    }
}
