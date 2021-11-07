package com.sweetcat.takeawaymaninfo.domain.maninfo.entity;

import com.sweetcat.commons.ResponseStatusEnum;
import com.sweetcat.commons.exception.ParameterFormatIllegalityException;
import com.sweetcat.takeawaymaninfo.domain.maninfo.exception.UserPropertyIlleagalException;

import java.util.regex.Pattern;

/**
 * @Author: Coder_Jarvis
 * @Description:
 * @Date: 2021-11-2021/11/7-13:49
 * @Version: 1.0
 */
public class ManInfo {
    /**
     * 骑手编号
     */
    private Long manId;

    /**
     * 骑手姓名
     */
    private String name;

    /**
     * 骑手联系方式
     */
    private String phone;

    public ManInfo(Long manId) {
        this.setManId(manId);
    }

    public ManInfo(Long manId, String name, String phone, Long createTime) {
        this.setManId(manId);
        this.setName(name);
        this.setPhone(phone);
        this.setCreateTime(createTime);
    }

    /**
     * 创建时间
     */
    private Long createTime;

    private void setManId(Long manId) {
        if (manId == null || manId < 0) {
            throw new UserPropertyIlleagalException("骑手编号格式错误");
        }
        this.manId = manId;
    }

    public Long getManId() {
        return manId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name.length() <= 0 || name == null) {
            throw new UserPropertyIlleagalException("骑手姓名不能为空");
        }
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        // 正则验证手机号格式
        String phoneRegex = "^1[3-9]\\d{9}$";
        boolean phoneMatches = Pattern.matches(phoneRegex, phone);
        // phone 格式不匹配，通知用户参数格式错误
        if (!phoneMatches) {
            throw new UserPropertyIlleagalException("骑手手机号格式错误");
        }
        this.phone = phone;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        if (createTime == null || createTime < 0) {
            throw new UserPropertyIlleagalException("骑手信息创建时间错误");
        }
        this.createTime = createTime;
    }
}
