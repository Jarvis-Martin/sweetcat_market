package com.sweetcat.favorite.application.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/11-22:18
 * @version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddUserFavoriteCommand {
    private Long userId;

    private Long commodityId;

    private Long createTime;
}
