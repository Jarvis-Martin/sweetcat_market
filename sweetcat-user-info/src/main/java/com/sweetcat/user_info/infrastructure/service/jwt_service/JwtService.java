package com.sweetcat.user_info.infrastructure.service.jwt_service;

import com.sweetcat.commons.util.JwtUtils;
import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @Author: Coder_Jarvis
 * @Description:
 * @Date: 2021-10-2021/10/27-12:15
 * @Version: 1.0
 */
@Service
public class JwtService {
    /**
     * 创建jwt
     *
     * @param id
     * @param issuer
     * @param subject
     * @param ttlMillis
     * @return
     * @throws Exception
     */
    public String createJwt(String id, String issuer, String subject, long ttlMillis, Map<String, Object> claims) throws Exception {
        return JwtUtils.createJWT(id, issuer, subject, ttlMillis, claims);
    }

    public Claims parseJwt(String jwt) throws Exception {
        return JwtUtils.parseJWT(jwt);
    }
}