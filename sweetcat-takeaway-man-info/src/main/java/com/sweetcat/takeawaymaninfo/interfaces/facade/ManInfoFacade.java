package com.sweetcat.takeawaymaninfo.interfaces.facade;

import com.sweetcat.takeawaymaninfo.application.event.subscribe.ManInfApplicationService;
import com.sweetcat.takeawaymaninfo.domain.maninfo.entity.ManInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author: Coder_Jarvis
 * @Description:
 * @Date: 2021-11-2021/11/7-14:35
 * @Version: 1.0
 */
@Component
public class ManInfoFacade {
    private ManInfApplicationService manInfApplicationService;

    @Autowired
    public void setManInfApplicationService(ManInfApplicationService manInfApplicationService) {
        this.manInfApplicationService = manInfApplicationService;
    }

    /**
     * 添加
     *
     * @param name       name
     * @param phone      phone
     * @param createTime createTime
     */
    public void addOne(String name, String phone, Long createTime) {
        manInfApplicationService.addOne(name, phone, createTime);
    }

    /**
     * find mainInfo by mainId
     *
     * @param manId manId
     * @return
     */
    public ManInfo getOne(Long manId) {
        return manInfApplicationService.getOne(manId);
    }
}
