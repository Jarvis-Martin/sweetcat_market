package com.sweetcat.secondkill.interfaces.web;

import com.sweetcat.commons.ResponseStatusEnum;
import com.sweetcat.commons.responsedto.ResponseDTO;
import com.sweetcat.secondkill.application.command.AddSKCommodityCommand;
import com.sweetcat.secondkill.domain.commodity.entity.SKCommodity;
import com.sweetcat.secondkill.interfaces.facade.SKCommodityFacade;
import com.sweetcat.secondkill.interfaces.facade.assembler.SKCommodityAssembler;
import com.sweetcat.secondkill.interfaces.facade.restdto.SKCommoditySummaryInfoRestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/27-17:25
 * @version: 1.0
 */
@RestController
@RequestMapping("/secondkill")
public class SKCommodityRestController {
    private SKCommodityFacade skCommodityFacade;
    private SKCommodityAssembler skCommodityAssembler;

    @Autowired
    public void setSkCommodityAssembler(SKCommodityAssembler skCommodityAssembler) {
        this.skCommodityAssembler = skCommodityAssembler;
    }

    @Autowired
    public void setSkCommodityFacade(SKCommodityFacade skCommodityFacade) {
        this.skCommodityFacade = skCommodityFacade;
    }

    /**
     * 增加一件商品记录
     *
     * @param command
     */
    @PostMapping("/commodity")
    public ResponseDTO addOne(AddSKCommodityCommand command) {
        skCommodityFacade.addOne(command);
        return response("添加商品成功", "{}");
    }

    /**
     * 移除秒杀商品
     *
     * @param commodityId
     */
    @DeleteMapping("/commodity/{commodity_id}")
    public ResponseDTO removeOne(@PathVariable("commodity_id") Long commodityId) {
        skCommodityFacade.removeOne(commodityId);
        return response("移除商品成功", "{}");
    }

    /**
     * 查找给定时间所在区间内的秒杀商品分页数据
     *
     * @param currentTimeStamp
     * @param page
     * @param limit
     * @return
     */
    @GetMapping("/commodities")
    public ResponseDTO findPageByTime(Long currentTimeStamp, @RequestParam("_page") Integer page, @RequestParam("_limit") Integer limit) {
        List<SKCommodity> skCommoditiePage = skCommodityFacade.findPageByTime(currentTimeStamp, page, limit);
        if (skCommoditiePage == null || skCommoditiePage.size() < 0) {
            return response("移除商品成功", "{}");
        }
        ArrayList<SKCommoditySummaryInfoRestDTO> skCommoditySummaryInfoRestDTOPage = skCommoditiePage.stream().collect(
                ArrayList<SKCommoditySummaryInfoRestDTO>::new,
                (con, skCommodity) -> con.add(skCommodityAssembler.converterToSKCommoditySummaryInfoRestDTO(skCommodity)),
                ArrayList<SKCommoditySummaryInfoRestDTO>::addAll
        );
        HashMap<String, Object> dataSection = new HashMap<>(2);
        dataSection.put("commodities", skCommoditySummaryInfoRestDTOPage);
        return response("移除商品成功", dataSection);
    }

    /**
     * 查找秒杀商品的详细信息
     *
     * @param commodityId
     * @return
     */
    @GetMapping("/commodity/{commodity_id}")
    public ResponseDTO findOneByCommodityId(@PathVariable("commodity_id") Long commodityId) {
        SKCommodity skCommodity = skCommodityFacade.findOneByCommodityId(commodityId);
        if (skCommodity == null) {
            return response("查询商品分页成功", "{}");
        }
        HashMap<String, Object> dataSection = new HashMap<>(2);
        dataSection.put("commodity", skCommodityAssembler.converterToSKCommodityDetailDTO(skCommodity));
        return response("查询商品分页成功", dataSection);
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
