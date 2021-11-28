package com.sweetcat.secondkill.interfaces.rpc;

import com.sweetcat.api.rpcdto.secondkill.SKCommodityInfoRpcDTO;
import com.sweetcat.secondkill.domain.commodity.entity.SKCommodity;
import com.sweetcat.secondkill.interfaces.facade.SKCommodityFacade;
import com.sweetcat.secondkill.interfaces.facade.assembler.rpc.SKCommodityInfoRpcAssemlber;
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
@RequestMapping("/rpc/secondkill/commodity")
public class SKCommodityInfoRpcController {
    private SKCommodityInfoRpcAssemlber commodityRpcAssemlber;
    private SKCommodityFacade commodityFacade;

    @Autowired
    public void setCommodityFacade(SKCommodityFacade commodityFacade) {
        this.commodityFacade = commodityFacade;
    }

    @Autowired
    public void setCommodityRpcAssemlber(SKCommodityInfoRpcAssemlber commodityRpcAssemlber) {
        this.commodityRpcAssemlber = commodityRpcAssemlber;
    }

    /**
     * 根据商品id 找商品
     *
     * @param commodityId
     * @return
     */
    @GetMapping("/{commodity_id}")
    public SKCommodityInfoRpcDTO findByCommodityId(@PathVariable("commodity_id") Long commodityId) {
       SKCommodity commodity = commodityFacade.findOneByCommodityId(commodityId);
        if (commodity == null) {
            return null;
        }
        return commodityRpcAssemlber.converterToCommodityInfoRpcDTO(commodity);
    }
}
