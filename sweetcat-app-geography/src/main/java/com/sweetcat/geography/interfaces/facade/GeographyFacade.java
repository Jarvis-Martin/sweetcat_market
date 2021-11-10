package com.sweetcat.geography.interfaces.facade;

import com.sweetcat.geography.application.command.AddGeographyCommand;
import com.sweetcat.geography.application.service.GeographyApplicationService;
import com.sweetcat.geography.domain.geography.entity.Geography;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/10-15:02
 * @version: 1.0
 */
@Component
public class GeographyFacade {
    private GeographyApplicationService geographyApplicationService;

    @Autowired
    public void setGeographyApplicationService(GeographyApplicationService geographyApplicationService) {
        this.geographyApplicationService = geographyApplicationService;
    }

    /**
     * 添加一条geography
     *
     * @param geographyCommand
     */
    public void addOne(AddGeographyCommand geographyCommand) {
        geographyApplicationService.addOne(geographyCommand);
    }

    /**
     * 根据 addressCode 查找一个 geography
     *
     * @param addressCode addressCode
     * @return
     */
    public Geography find(String addressCode) {
        return geographyApplicationService.find(addressCode);
    }

    /**
     * 移除一条geography
     *
     * @param geography geography
     */
    public void remove(Geography geography) {
        geographyApplicationService.remove(geography);
    }
}
