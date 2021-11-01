package com.sweetcat.user_info.domain;

import lombok.Data;

/**
 * @Author: Coder_Jarvis
 * @Description:
 * @Date: 2021-10-2021/10/27-18:20
 * @Version: 1.0
 */
@Data
public class BaseEntity<T> {
    private T ectype;
    private Boolean isNew;
    private Boolean isChanged;
}
