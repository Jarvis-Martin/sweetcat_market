package com.sweetcat.storecommodity.interfaces.web;

import com.sweetcat.commons.ResponseStatusEnum;
import com.sweetcat.commons.responsedto.ResponseDTO;
import com.sweetcat.storecommodity.application.command.AddCommodityPostChargeCommand;
import com.sweetcat.storecommodity.domain.commonditypostcharge.entity.CommodityPostCharge;
import com.sweetcat.storecommodity.interfaces.facade.CommodityPostChargeFacade;
import com.sweetcat.storecommodity.interfaces.facade.assembler.CommodityPostChargeRestAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/10-19:56
 * @version: 1.0
 */
@RestController
@RequestMapping("/post_charge")
public class CommodityPostChargeController {
    private CommodityPostChargeFacade postChargeFacade;
    private CommodityPostChargeRestAssembler postChargeRestAssembler;

    @Autowired
    public void setPostChargeFacade(CommodityPostChargeFacade postChargeFacade) {
        this.postChargeFacade = postChargeFacade;
    }

    @Autowired
    public void setPostChargeRestAssembler(CommodityPostChargeRestAssembler postChargeRestAssembler) {
        this.postChargeRestAssembler = postChargeRestAssembler;
    }

    /**
     * 添加一个记录
     *
     * @param command command
     */
    @PostMapping("/")
    public ResponseDTO addOne(AddCommodityPostChargeCommand command) {
        postChargeFacade.addOne(command);
        return response("插入商品邮费记录成功", "{}");
    }

    /**
     * find post charge recorde by commodityId and provinceCode
     *
     * @param commodityId  commodityId
     * @param provinceCode provinceCode
     * @return
     */
    @GetMapping("/{commodity_id}")
    public ResponseDTO find(@PathVariable("commodity_id") Long commodityId, String provinceCode) {
        CommodityPostCharge commodityPostCharge = postChargeFacade.find(commodityId, provinceCode);
        if (commodityPostCharge == null) {
            return null;
        }
        HashMap<String, Object> dataSection = new HashMap<>(2);
        dataSection.put("post_charge", postChargeRestAssembler.converterToCommodityPostChargeDTO(commodityPostCharge));
        return response("查询商品优惠记录成功", dataSection);
    }

    /**
     * remove post charge recorde by commodity id and province code
     *
     * @param chargeId chargeId
     */
    @DeleteMapping("/{charge_id}")
    public ResponseDTO remove(@PathVariable("charge_id") Long chargeId) {
        postChargeFacade.remove(chargeId);
        return response("删除邮费记录成功", "{}");
    }

    /**
     * 通用的放回 ResponseDTO
     *
     * @param tip  用户提示信息
     * @param data 数据部分
     * @return ResponseDTO
     */
    private ResponseDTO response(String tip, Object data) {
        return new ResponseDTO(
                ResponseStatusEnum.SUCCESS.getErrorCode(),
                ResponseStatusEnum.SUCCESS.getErrorMessage(),
                tip,
                data
        );
    }
}
