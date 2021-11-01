package com.sweetcat.user_info.infrastructure.service.encrypt_service;

/**
 * @Author: Coder_Jarvis
 * @Description:
 * @Date: 2021-10-2021/10/25-19:33
 * @Version: 1.0
 */
public interface EncryptAndDecryptService {
    /**
     * 加密 plainString
     * @param plainString 明文
     * @return 加密后的文本
     */
    String encrypt(String plainString);

    Boolean isMatch(String templateStr, String targetStr);

    /**
     * 解密 encryptedString
     * @param encryptedString 密文
     * @return 解密后的文本
     */
    String decrypt(String encryptedString);
}
