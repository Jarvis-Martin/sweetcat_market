package com.sweetcat.takeawaymaninfo.interfaces.facade.assembler;

import com.sweetcat.takeawaymaninfo.domain.maninfo.entity.ManInfo;
import com.sweetcat.takeawaymaninfo.interfaces.facade.restdto.ManInfoDTO;
import org.springframework.stereotype.Component;

/**
 * @Author: Coder_Jarvis
 * @Description:
 * @Date: 2021-11-2021/11/7-14:46
 * @Version: 1.0
 */
@Component
public class ManInfoAssembler {
    public ManInfoDTO converterToManInfoDTO(ManInfo manInfo) {
        return new ManInfoDTO(manInfo.getManId(), manInfo.getName(), manInfo.getPhone());
    }
}
