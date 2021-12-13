package com.sweetcat.credit.interfaces.web;

import com.sweetcat.commons.ResponseStatusEnum;
import com.sweetcat.commons.responsedto.ResponseDTO;
import com.sweetcat.credit.domain.commodity.entity.BaseCommodity;
import com.sweetcat.credit.interfaces.facade.CommodityFacade;
import com.sweetcat.credit.interfaces.facade.assembler.CommodityAssembler;
import com.sweetcat.credit.interfaces.facade.restdto.CreditCenterCommodityRestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/17-20:42
 * @version: 1.0
 */
@RestController
@RequestMapping("/credit")
public class CommodityRestController {
    private CommodityFacade commodityFacade;
    private CommodityAssembler commodityAssembler;

    @Autowired
    public void setCommodityAssembler(CommodityAssembler commodityAssembler) {
        this.commodityAssembler = commodityAssembler;
    }

    @Autowired
    public void setCommodityFacade(CommodityFacade commodityFacade) {
        this.commodityFacade = commodityFacade;
    }


    /**
     * 根据 商品分类查找商品分页数据
     *
     * @param commodityType
     * @param page
     * @param limit
     * @return
     */
    @GetMapping("/commodities")
    public ResponseDTO findPageByCommodityType(Integer commodityType, @RequestParam("_page") Integer page, @RequestParam("_limit") Integer limit) {
        List<BaseCommodity> commodityPage = commodityFacade.findPageByCommodityType(commodityType, page, limit);
        if (commodityPage == null || commodityPage.size() <= 0) {
            return response("查询成功", "{}");
        }
        ArrayList<CreditCenterCommodityRestDTO> commodityDTOPage = commodityPage.stream().collect(
                ArrayList<CreditCenterCommodityRestDTO>::new,
                (con, baseCommodity) -> {
                    con.add(commodityAssembler.converterToCreditCenterCommodityRestDTO(baseCommodity));
                },
                ArrayList::addAll
        );
        HashMap<String, Object> dataSection = new HashMap<>(2);
        dataSection.put("commodities", commodityDTOPage);
        return response("查询成功", dataSection);
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
