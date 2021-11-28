package com.sweetcat.secondkill.infrastructure.rpc;

import com.sweetcat.api.client.GeographyClient;
import com.sweetcat.api.rpcdto.geography.GeographyRpcDTO;
import com.sweetcat.secondkill.application.rpc.GeographyRpc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/10-19:39
 * @version: 1.0
 */
@Component
public class GeographyRpcImpl implements GeographyRpc {
    private GeographyClient geographyClient;

    @Autowired
    public void setGeographyClient(GeographyClient geographyClient) {
        this.geographyClient = geographyClient;
    }

    @Override
    public GeographyRpcDTO getGeographyInfo(String addressCode) {
        return this.geographyClient.getGeographyInfo(addressCode);
    }
}
