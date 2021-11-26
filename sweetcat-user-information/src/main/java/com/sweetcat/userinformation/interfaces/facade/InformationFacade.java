package com.sweetcat.userinformation.interfaces.facade;

import com.sweetcat.userinformation.application.service.InformationApplicationService;
import com.sweetcat.userinformation.domain.information.entity.Information;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/25-22:05
 * @version: 1.0
 */
@Component
public class InformationFacade {
    private InformationApplicationService informationApplicationService;

    @Autowired
    public void setInformationApplicationService(InformationApplicationService informationApplicationService) {
        this.informationApplicationService = informationApplicationService;
    }

    /**
     * 根据 receiverId 查分页数据
     * @param receiverId
     * @param page
     * @param limit
     * @return
     */
    public <T extends Information> List<T> findPageByReceiverId(Long receiverId, Integer page, Integer limit) {
        return informationApplicationService.findPageByReceiverId(receiverId, page, limit);
    }


    /**
     * 根据 informationId 查找 information
     * @param informationId
     * @param <T>
     */
    public <T extends Information> T findOneByInformationId(Long informationId) {
        return informationApplicationService.findOneByInformationId(informationId);
    }

    /**
     * 移除
     * @param informationId
     */
    public void removeOne(Long informationId) {
        informationApplicationService.removeOne(informationId);
    }
}
