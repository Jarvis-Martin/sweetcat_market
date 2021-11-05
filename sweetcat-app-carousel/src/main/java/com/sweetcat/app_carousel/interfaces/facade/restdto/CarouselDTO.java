package com.sweetcat.app_carousel.interfaces.facade.restdto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author: Coder_Jarvis
 * @Description:
 * @Date: 2021-10-2021/10/16-10:50
 * @Version: 1.0
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarouselDTO implements Serializable {
    private Long picId;
    /**
     * 轮播图路径
     */
    private String picPath;
    /**
     * 改轮播图对应的目标路径
     */
    private String targetUrl;
    /**
     * 开始时间
     */
    private Long startTime;
    /**
     * 截止日期
     */
    private Long deadline;
}
