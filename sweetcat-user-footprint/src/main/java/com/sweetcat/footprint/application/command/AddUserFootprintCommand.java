package com.sweetcat.footprint.application.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/10-22:39
 * @version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddUserFootprintCommand {

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 商品id
     */
    private Long commodityId;

    /**
     * 浏览开始时间
     */
    private Long startTime;

    /**
     * 浏览时长, 单位: ms
     */
    private Integer browserDuration;
}
