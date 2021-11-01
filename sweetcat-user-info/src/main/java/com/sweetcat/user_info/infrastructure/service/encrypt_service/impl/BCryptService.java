package com.sweetcat.user_info.infrastructure.service.encrypt_service.impl;

import com.sweetcat.user_info.infrastructure.service.encrypt_service.EncryptAndDecryptService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @Author: Coder_Jarvis
 * @Description:
 * @Date: 2021-10-2021/10/25-19:36
 * @Version: 1.0
 */
public class BCryptService implements EncryptAndDecryptService {

    @Override
    public String encrypt(String plainString) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder.encode(plainString);
    }

    @Override
    public Boolean isMatch(String rawPassword,String encodedPassword) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder.matches(rawPassword,encodedPassword);
    }

    @Override
    public String decrypt(String encryptedString) {
        return null;
    }
}
