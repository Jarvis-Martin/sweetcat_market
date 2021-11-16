package com.sweetcat.credit.domain.creditlog.entity;

import com.sweetcat.commons.ResponseStatusEnum;
import com.sweetcat.commons.exception.ParameterFormatIllegalityException;
import lombok.Getter;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/14-16:25
 * @version: 1.0
 */
@Getter
public class CreditLog {
    /**
     * 收入
     */
    public static final Integer LOGTYPE_ACQUIRE = 0;
    /**
     * 支出
     */
    public static final Integer LOGTYPE_EXPAND = 1;

    /**
     * 签到记录id
     */
    private Long creditLogId;
    /**
     * 积分记录用户
     */
    private CreditLogUser creditLogUser;
    /**
     * 记录类型：0收入；1支付
     */
    private Integer logType;
    /**
     * 描述：如签到获得30积分；兑换支出300积分
     */
    private String description;
    /**
     * 纪录涉及的积分数量
     */
    private Integer creditNumber;
    /**
     * 记录创建时间
     */
    private Long createTime;

    public CreditLog(Long creditLogId) {
        this.setCreditLogId(creditLogId);
    }

    public CreditLog(Long creditLogId, CreditLogUser creditedUser, Integer logType, String description, Integer creditNumber, Long createTime) {
        this.setCreditLogId(creditLogId);
        this.setCreditLogUser(creditedUser);
        this.setLogType(logType);
        this.setDescription(description);
        this.setCreditNumber(creditNumber);
        this.setCreateTime(createTime);
    }

    public void setCreditLogId(Long creditLogId) {
        if (creditLogId == null || creditLogId < 0) {
            throw new ParameterFormatIllegalityException(
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorCode(),
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorMessage()
            );
        }
        this.creditLogId = creditLogId;
    }

    public void setCreditLogUser(CreditLogUser creditLogUser) {
        if (creditLogUser == null) {
            throw new ParameterFormatIllegalityException(
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorCode(),
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorMessage()
            );
        }
        this.creditLogUser = creditLogUser;
    }

    public void setLogType(Integer logType) {
        if (logType == null || (!CreditLog.LOGTYPE_ACQUIRE.equals(logType) && !CreditLog.LOGTYPE_EXPAND.equals(logType) )) {
            throw new ParameterFormatIllegalityException(
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorCode(),
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorMessage()
            );
        }
        this.logType = logType;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCreditNumber(Integer creditNumber) {
        if (creditNumber == null || creditNumber < 0) {
            throw new ParameterFormatIllegalityException(
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorCode(),
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorMessage()
            );
        }
        this.creditNumber = creditNumber;
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


}
