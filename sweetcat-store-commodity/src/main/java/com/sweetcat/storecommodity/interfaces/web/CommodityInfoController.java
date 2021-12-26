package com.sweetcat.storecommodity.interfaces.web;

import com.sweetcat.commons.ResponseStatusEnum;
import com.sweetcat.commons.responsedto.ResponseDTO;
import com.sweetcat.storecommodity.application.command.AddStoreCommodityCommand;
import com.sweetcat.storecommodity.domain.commodityinfo.entity.Commodity;
import com.sweetcat.storecommodity.interfaces.facade.CommodityInfoFacade;
import com.sweetcat.storecommodity.interfaces.facade.assembler.CommodityRestAssembler;
import com.sweetcat.storecommodity.interfaces.facade.restdto.CommodityDetailDTO;
import com.sweetcat.storecommodity.interfaces.facade.restdto.CommoditySummaryInfoRestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @Author: Coder_Jarvis
 * @Description:
 * @Date: 2021-11-2021/11/7-22:58
 * @Version: 1.0
 */
@RestController
@RequestMapping("/commodity")
public class CommodityInfoController {
    private CommodityInfoFacade commodityInfoFacade;
    private CommodityRestAssembler commodityRestAssembler;

    @Autowired
    public void setCommodityAssembler(CommodityRestAssembler commodityRestAssembler) {
        this.commodityRestAssembler = commodityRestAssembler;
    }

    @Autowired
    public void setCommodityInfoFacade(CommodityInfoFacade commodityInfoFacade) {
        this.commodityInfoFacade = commodityInfoFacade;
    }

    /**
     * 根据商品id 找商品
     *
     * @param commodityId
     * @return
     */
    @GetMapping("/{commodity_id}")
    public ResponseDTO findByCommodityId(@PathVariable("commodity_id") Long commodityId) {
        Commodity commodity = commodityInfoFacade.findByCommodityId(commodityId);
        if (commodity == null) {
            return response("查询商品信息成功", "{}");
        }
        HashMap<String, Object> dataSection = new HashMap<>(2);
        dataSection.put("commodity", commodityRestAssembler.converterToCommodityInfoDTO(commodity));
        return response("查询商品信息成功", dataSection);
    }


    /**
     * 查找积分可兑换商品
     *
     * @param page
     * @param limit
     * @return
     */
    @GetMapping("/credit/commodities")
    public ResponseDTO findPageCreditCanOffsetAPart(@RequestParam("_page") Integer page, @RequestParam("_limit") Integer limit) {
        List<Commodity> commodityPage = commodityInfoFacade.findPageCreditCanOffsetAPart(page, limit);
        if (commodityPage == null || commodityPage.isEmpty()) {
            return response("查找积分可兑换商品", "{}");
        }
        ArrayList<CommoditySummaryInfoRestDTO> commodities = commodityPage.stream().collect(
                ArrayList<CommoditySummaryInfoRestDTO>::new,
                (con, commodityInfo) -> con.add(commodityRestAssembler.converterToCommoditySummaryInfoRestDTO(commodityInfo)),
                ArrayList<CommoditySummaryInfoRestDTO>::addAll
        );

        HashMap<String, Object> dataSection = new HashMap<>(2);
        dataSection.put("commodities", commodities);

        return response("查找积分可兑换商品", dataSection);
    }

    /**
     * 查找 指定门店商品分页数据
     *
     * @param storeId
     * @param page
     * @param limit
     * @return
     */
    @GetMapping("/store/commodities")
    public ResponseDTO findPageByStoreId(@PathVariable("store_id") Long storeId, @RequestParam("_page") Integer page, @RequestParam("_limit") Integer limit) {
        List<Commodity> commodityPage = commodityInfoFacade.findPageByStoreId(storeId, page, limit);
        if (commodityPage == null || commodityPage.isEmpty()) {
            return response("指定门店商品分页数据", "{}");
        }
        List<CommodityDetailDTO> commodities = converterCommodityInfoListToCommodityInfoDTOList(commodityPage);
        HashMap<String, Object> dataSection = new HashMap<>(2);
        dataSection.put("commodities", commodities);

        return response("指定门店商品分页数据", dataSection);
    }

    @GetMapping("/commodities/newlisting")
    public ResponseDTO findPageNewCommodities(@RequestParam("_page") Integer page, @RequestParam("_limit") Integer limit) {
        List<Commodity> pageNewCommodities = commodityInfoFacade.findPageNewCommodities(page, limit);
        if (pageNewCommodities == null || pageNewCommodities.isEmpty()) {
            return response("新上架商品", "{}");
        }
        List<CommodityDetailDTO> commodities = converterCommodityInfoListToCommodityInfoDTOList(pageNewCommodities);
        HashMap<String, Object> dataSection = new HashMap<>(2);
        dataSection.put("commodities", commodities);

        return response("新上架商品", dataSection);
    }

    /**
     * 将 List<CommodityInfo> 转 List<CommodityInfoDTO>
     *
     * @param commodities List<CommodityInfo>
     * @return
     */
    private List<CommodityDetailDTO> converterCommodityInfoListToCommodityInfoDTOList(List<Commodity> commodities) {
        ArrayList<CommodityDetailDTO> commodityDetailDTOS = null;
        if (commodities != null && commodities.size() != 0) {
            commodityDetailDTOS = commodities.stream().collect(
                    ArrayList<CommodityDetailDTO>::new,
                    (con, commodityInfo) -> con.add(commodityRestAssembler.converterToCommodityInfoDTO(commodityInfo)),
                    ArrayList::addAll
            );
        }
        return commodityDetailDTOS;
    }

    /**
     * 添加商品
     *
     * @param addStoreCommodityCommand
     * @return
     */
    @PostMapping("/")
    public ResponseDTO addOne(AddStoreCommodityCommand addStoreCommodityCommand) {
        commodityInfoFacade.addOne(addStoreCommodityCommand);
        return response("添加商品成功", "{}");
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
