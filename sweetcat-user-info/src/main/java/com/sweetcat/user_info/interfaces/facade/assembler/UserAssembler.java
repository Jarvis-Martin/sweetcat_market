package com.sweetcat.user_info.interfaces.facade.assembler;

import com.sweetcat.user_info.domain.user.entity.User;
import com.sweetcat.user_info.interfaces.facade.restdto.UserInfoDTO;
import org.springframework.stereotype.Component;

/**
 * @Author: Coder_Jarvis
 * @Description:
 * @Date: 2021-10-2021/10/25-14:50
 * @Version: 1.0
 */
@Component
public class UserAssembler {
    public UserInfoDTO converter2UserInfoDTO(User user) {
        UserInfoDTO userInfoDTO = new UserInfoDTO();
        userInfoDTO.setUserId(user.getUserId());
        userInfoDTO.setNickname(user.getNickname());
        userInfoDTO.setAvatarPath(user.getAvatarPath());
        userInfoDTO.setGender(user.getGender());
        userInfoDTO.setBirthday(user.getBirthday());
        userInfoDTO.setVipLevel(user.getVipLevel());
        userInfoDTO.setIsReferrer(user.getReferrer());
        userInfoDTO.setPersonalizedSignature(user.getPersonalizedSignature());
        userInfoDTO.setPhone(user.getPhone());
        userInfoDTO.setCreateTime(user.getCreateTime());
        userInfoDTO.setUpdateTime(user.getUpdateTime());
        return userInfoDTO;
    }
}
