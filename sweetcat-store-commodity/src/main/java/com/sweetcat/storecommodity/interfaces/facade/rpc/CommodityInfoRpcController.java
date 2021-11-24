package com.sweetcat.storecommodity.interfaces.facade.rpc;

import com.sweetcat.api.rpcdto.commodityinfo.CommodityInfoRpcDTO;
import com.sweetcat.storecommodity.domain.commodityinfo.entity.Commodity;
import com.sweetcat.storecommodity.interfaces.facade.CommodityInfoFacade;
import com.sweetcat.storecommodity.interfaces.facade.assembler.rpc.CommodityInfoRpcAssemlber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/11-16:50
 * @version: 1.0
 */
@RestController
@RequestMapping("/rpc/commodity")
public class CommodityInfoRpcController {
    private CommodityInfoRpcAssemlber commodityInfoRpcAssemlber;
    private CommodityInfoFacade commodityInfoFacade;

    @Autowired
    public void setCommodityInfoFacade(CommodityInfoFacade commodityInfoFacade) {
        this.commodityInfoFacade = commodityInfoFacade;
    }

    @Autowired
    public void setCommodityInfoRpcAssemlber(CommodityInfoRpcAssemlber commodityInfoRpcAssemlber) {
        this.commodityInfoRpcAssemlber = commodityInfoRpcAssemlber;
    }

    /**
     * 根据商品id 找商品
     *
     * @param commodityId
     * @return
     */
    @GetMapping("/{commodity_id}")
    public CommodityInfoRpcDTO findByCommodityId(@PathVariable("commodity_id") Long commodityId) {
       Commodity commodity = commodityInfoFacade.findByCommodityId(commodityId);
        if (commodity == null) {
            return null;
        }
        return commodityInfoRpcAssemlber.converterToCommodityInfoRpcDTO(commodity);
    }
}
