package com.sweetcat.user_info.domain.user.service.authen_service;

import com.sweetcat.user_info.domain.user.entity.User;
import com.sweetcat.user_info.domain.user.vo.UserDescriptor;
import com.sweetcat.user_info.infrastructure.service.encrypt_service.EncryptAndDecryptService;
import com.sweetcat.user_info.infrastructure.service.encrypt_service.impl.BCryptService;

/**
 * @Author: Coder_Jarvis
 * @Description:
 * @Date: 2021-10-2021/10/25-20:29
 * @Version: 1.0
 */
public class AuthenService {
    public Boolean authenticate(UserDescriptor userDescriptor, User user) {
        EncryptAndDecryptService encryptAndDecryptService = new BCryptService();
        return encryptAndDecryptService.isMatch(userDescriptor.getPassword(), user.getPassword());
    }
}
