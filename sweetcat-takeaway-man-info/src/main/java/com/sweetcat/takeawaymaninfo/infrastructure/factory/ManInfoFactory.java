package com.sweetcat.takeawaymaninfo.infrastructure.factory;

import com.sweetcat.takeawaymaninfo.domain.maninfo.entity.ManInfo;
import com.sweetcat.takeawaymaninfo.infrastructure.po.ManInfoPO;
import org.springframework.stereotype.Component;

/**
 * @Author: Coder_Jarvis
 * @Description:
 * @Date: 2021-11-2021/11/7-14:03
 * @Version: 1.0
 */
@Component
public class ManInfoFactory {
    public ManInfo create(ManInfoPO manInfoPO) {
        ManInfo manInfo = new ManInfo(manInfoPO.getManId());
        manInfo.setName(manInfoPO.getName());
        manInfo.setPhone(manInfoPO.getPhone());
        manInfo.setCreateTime(manInfoPO.getCreateTime());
        return manInfo;
    }
}
