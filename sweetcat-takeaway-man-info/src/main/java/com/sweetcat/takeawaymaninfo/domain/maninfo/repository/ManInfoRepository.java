package com.sweetcat.takeawaymaninfo.domain.maninfo.repository;

import com.sweetcat.takeawaymaninfo.domain.maninfo.entity.ManInfo;

/**
 * @Author: Coder_Jarvis
 * @Description:
 * @Date: 2021-11-2021/11/7-14:01
 * @Version: 1.0
 */
public interface ManInfoRepository {
    /**
     * 添加
     *
     * @param mainInfo mainInfo
     */
    void addOne(ManInfo mainInfo);

    /**
     * find mainInfo by mainId
     *
     * @param manId manId
     * @return
     */
    ManInfo find(Long manId);
}
