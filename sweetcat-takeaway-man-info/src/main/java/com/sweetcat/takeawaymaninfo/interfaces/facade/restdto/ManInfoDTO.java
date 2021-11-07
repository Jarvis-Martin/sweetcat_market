package com.sweetcat.takeawaymaninfo.interfaces.facade.restdto;

/**
 * @Author: Coder_Jarvis
 * @Description:
 * @Date: 2021-11-2021/11/7-14:45
 * @Version: 1.0
 */
public class ManInfoDTO {
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

    public ManInfoDTO(Long manId, String name, String phone) {
        this.manId = manId;
        this.name = name;
        this.phone = phone;
    }

    public Long getManId() {
        return manId;
    }

    public void setManId(Long manId) {
        this.manId = manId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
