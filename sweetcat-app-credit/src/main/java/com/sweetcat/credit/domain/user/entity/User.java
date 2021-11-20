package com.sweetcat.credit.domain.user.entity;

import com.sweetcat.commons.ResponseStatusEnum;
import com.sweetcat.commons.exception.ParameterFormatIllegalityException;
import lombok.Getter;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/14-16:02
 * @version: 1.0
 */
@Getter
public class User {

    /**
     * 用户编号
     */
    private Long userId;
    /**
     * 用户持有的积分数量
     */
    private Long credit;
    /**
     * 记录创建时间
     */
    private Long createTime;

    public User(Long userId) {
        this.userId = userId;
    }

    public void setUserId(Long userId) {
        if (userId == null || userId < 0) {
            throw new ParameterFormatIllegalityException(
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorCode(),
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorMessage()
            );
        }
        this.userId = userId;
    }

    public void setCredit(Long credit) {
        if (credit == null || credit < 0) {
            throw new ParameterFormatIllegalityException(
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorCode(),
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorMessage()
            );
        }
        this.credit = credit;
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

    /**
     * 收入积分
     *
     * @param credit
     */
    public void acquire(Long credit) {
        setCredit(this.credit + credit);
    }

    /**
     * 支出积分
     *
     * @param credit
     */
    public void expend(Long credit) {
        setCredit(this.credit - credit);
    }


    /**
     * 跟新用户 credit
     * @param incrementOfCredit
     */
    public void updateCredit(Long incrementOfCredit) {
        setCredit(credit + incrementOfCredit);
    }
}
