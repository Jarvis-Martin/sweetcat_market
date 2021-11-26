package com.sweetcat.customerservice.domain.staff.entity;

import com.sweetcat.commons.ResponseStatusEnum;
import com.sweetcat.commons.exception.ParameterFormatIllegalityException;
import lombok.Getter;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/13-10:34
 * @version: 1.0
 */
@Getter
public class CustomerServiceStaff {
    /**
     * 客服id
     */
    private Long staffId;

    /**
     * 客服昵称
     */
    private String staffNickname;

    /**
     * 客服人员头像
     */
    private String staffAvatar;

    /**
     * 账号创建时间
     */
    private Long createTime;

    /**
     * 账号更新时间
     */
    private Long updateTime;

    public CustomerServiceStaff(Long staffId) {
        this.staffId = staffId;
    }

    public CustomerServiceStaff(Long staffId, String staffNickname, Long createTime, Long updateTime) {
        this.staffId = staffId;
        this.staffNickname = staffNickname;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public void setStaffId(Long staffId) {
        if (staffId == null || staffId < 0) {
            throw new ParameterFormatIllegalityException(
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorCode(),
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorMessage()
            );
        }
        this.staffId = staffId;
    }

    public void setStaffNickname(String staffNickname) {
        if (staffNickname == null || staffNickname.length() <= 0) {
            throw new ParameterFormatIllegalityException(
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorCode(),
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorMessage()
            );
        }
        this.staffNickname = staffNickname;
    }

    public void setCreateTime(Long createTime) {
        if (createTime == null || createTime < 0) {
            throw new ParameterFormatIllegalityException(
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorCode(),
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorMessage()
            );
        }
        this.createTime = createTime;
    }

    public void setUpdateTime(Long updateTime) {
        if (updateTime == null || updateTime < 0) {
            throw new ParameterFormatIllegalityException(
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorCode(),
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorMessage()
            );
        }
        this.updateTime = updateTime;
    }

    public void setStaffAvatar(String staffAvatar) {
        if (staffAvatar == null || staffAvatar.length() < 0) {
            throw new ParameterFormatIllegalityException(
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorCode(),
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorMessage()
            );
        }
        this.staffAvatar = staffAvatar;
    }
}
