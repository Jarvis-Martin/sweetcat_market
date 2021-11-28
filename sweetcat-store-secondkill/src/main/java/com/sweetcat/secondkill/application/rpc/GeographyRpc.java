package com.sweetcat.secondkill.application.rpc;

import com.sweetcat.api.rpcdto.geography.GeographyRpcDTO;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/10-19:37
 * @version: 1.0
 */
public interface GeographyRpc {
    /**
     * 根据 addressCode 查找地址信息
     * @param addressCode addressCode
     * @return
     */
    GeographyRpcDTO getGeographyInfo(String addressCode);
}
