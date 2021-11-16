package com.sweetcat.credit.interfaces.facade.assembler;

import com.sweetcat.credit.domain.user.entity.User;
import com.sweetcat.credit.interfaces.facade.restdto.UserRestDTO;
import org.springframework.stereotype.Component;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/15-15:06
 * @version: 1.0
 */
@Component
public class UserAssembler {
    public UserRestDTO converterToUserRestDTO(User user) {
        UserRestDTO userRestDTO = new UserRestDTO();
        userRestDTO.setUserId(user.getUserId());
        userRestDTO.setCredit(user.getCredit());
        return userRestDTO;
    }
}
