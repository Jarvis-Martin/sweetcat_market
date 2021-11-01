package com.sweetcat.user_info.domain.user.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Author: Coder_Jarvis
 * @Description:
 * @Date: 2021-10-2021/10/25-21:20
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
public class UserDescriptor {
    private Long userId;
    private String password;
}
