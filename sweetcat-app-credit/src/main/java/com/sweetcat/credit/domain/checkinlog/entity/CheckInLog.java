package com.sweetcat.credit.domain.checkinlog.entity;

import com.sweetcat.commons.ResponseStatusEnum;
import com.sweetcat.commons.exception.ParameterFormatIllegalityException;
import lombok.Getter;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/14-16:20
 * @version: 1.0
 */
@Getter
public class CheckInLog {
    /**
     * 签到记录id
     */
    private Long checkInLogId;
    /**
     * 签到的用户id
     */
    private CheckedInUser checkedInUser;
    /**
     * 签到时间
     */
    private Long checkedInTime;

    public CheckInLog(Long checkInLogId) {
        this.setCheckInLogId(checkInLogId);
    }

    public CheckInLog(Long checkInLogId, CheckedInUser checkedInUser, Long checkedInTime) {
        this.setCheckInLogId(checkInLogId);
        this.setCheckedInUser(checkedInUser);
        this.setCheckedInTime(checkedInTime);
    }

    public void setCheckInLogId(Long checkInLogId) {
        if (checkInLogId == null || checkInLogId < 0) {
            throw new ParameterFormatIllegalityException(
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorCode(),
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorMessage()
            );
        }
        this.checkInLogId = checkInLogId;
    }

    public void setCheckedInUser(CheckedInUser checkedInUser) {
        if (checkedInUser == null) {
            throw new ParameterFormatIllegalityException(
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorCode(),
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorMessage()
            );
        }
        this.checkedInUser = checkedInUser;
    }

    public void setCheckedInTime(Long checkedInTime) {
        if (checkedInTime == null || checkedInTime < 0) {
            throw new ParameterFormatIllegalityException(
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorCode(),
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorMessage()
            );
        }
        this.checkedInTime = checkedInTime;
    }
}
