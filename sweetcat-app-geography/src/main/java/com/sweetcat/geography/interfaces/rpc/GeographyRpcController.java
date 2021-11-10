package com.sweetcat.geography.interfaces.rpc;

import com.sweetcat.api.rpcdto.geography.GeographyRpcDTO;
import com.sweetcat.geography.domain.geography.entity.Geography;
import com.sweetcat.geography.interfaces.facade.GeographyFacade;
import com.sweetcat.geography.interfaces.facade.assembler.RpcDTOAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/10-15:06
 * @version: 1.0
 */
@RestController
@RequestMapping("/rpc/app/geography")
public class GeographyRpcController {
    private GeographyFacade geographyFacade;
    private RpcDTOAssembler rpcDTOAssembler;

    @Autowired
    public void setRpcDTOAssembler(RpcDTOAssembler rpcDTOAssembler) {
        this.rpcDTOAssembler = rpcDTOAssembler;
    }

    @Autowired
    public void setGeographyFacade(GeographyFacade geographyFacade) {
        this.geographyFacade = geographyFacade;
    }

    /**
     * 根据 addressCode 查找一个 geography
     *
     * @param addressCode addressCode
     * @return
     */
    @GetMapping("/{address_code}")
    public GeographyRpcDTO getGeographyInfo(@PathVariable("address_code") String addressCode) {
        Geography geography = geographyFacade.find(addressCode);
        GeographyRpcDTO geographyRpcDTO = null;
        if (geography != null) {
            geographyRpcDTO = rpcDTOAssembler.converterGeographyRpcDTO(geography);
        }
        return geographyRpcDTO;
    }
}
