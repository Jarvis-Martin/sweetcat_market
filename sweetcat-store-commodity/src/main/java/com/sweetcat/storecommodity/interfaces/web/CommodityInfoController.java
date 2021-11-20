package com.sweetcat.storecommodity.interfaces.web;

import com.sweetcat.commons.ResponseStatusEnum;
import com.sweetcat.commons.responsedto.ResponseDTO;
import com.sweetcat.storecommodity.application.command.AddStoreCommodityCommand;
import com.sweetcat.storecommodity.domain.commodityinfo.entity.CommodityInfo;
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
        CommodityInfo commodityInfo = commodityInfoFacade.findByCommodityId(commodityId);
        HashMap<String, Object> dataSection = new HashMap<>(2);
        if (commodityInfo != null) {
            dataSection.put("good_info", commodityRestAssembler.converterToCommodityInfoDTO(commodityInfo));
        }
        return response("一切OK", dataSection);
    }


    /**
     * 查找 指定门店商品分页数据
     *
     * @param page
     * @param limit
     * @return
     */
    @GetMapping("/credit/commodities")
    public ResponseDTO findPageCreditCanOffsetAPart(@RequestParam("_page") Integer page, @RequestParam("_limit") Integer limit) {
        List<CommodityInfo> commodityInfoPage = commodityInfoFacade.findPageCreditCanOffsetAPart(page, limit);

        ArrayList<CommoditySummaryInfoRestDTO> commoditySummaryInfoRestDTOPage = commodityInfoPage.stream().collect(
                ArrayList<CommoditySummaryInfoRestDTO>::new,
                (con, commodityInfo) -> con.add(commodityRestAssembler.converterToCommoditySummaryInfoRestDTO(commodityInfo)),
                ArrayList<CommoditySummaryInfoRestDTO>::addAll
        );

        HashMap<String, Object> dataSection = new HashMap<>(2);
        dataSection.put("commodities", commoditySummaryInfoRestDTOPage);

        return response("一切OK", dataSection);
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
        List<CommodityInfo> commodityInfoPage = commodityInfoFacade.findPageByStoreId(storeId, page, limit);

        List<CommodityDetailDTO> commodityDetailDTOS = converterCommodityInfoListToCommodityInfoDTOList(commodityInfoPage);
        HashMap<String, Object> dataSection = new HashMap<>(2);
        dataSection.put("commodities", commodityDetailDTOS);

        return response("一切OK", dataSection);
    }

    @GetMapping("/new_commodities")
    public ResponseDTO findPageNewCommodities(@RequestParam("_page") Integer page, @RequestParam("_limit") Integer limit) {
        List<CommodityInfo> pageNewCommodities = commodityInfoFacade.findPageNewCommodities(page, limit);

        List<CommodityDetailDTO> newCommodityDetailDTOS = converterCommodityInfoListToCommodityInfoDTOList(pageNewCommodities);
        HashMap<String, Object> dataSection = new HashMap<>(2);
        dataSection.put("commodities", newCommodityDetailDTOS);

        return response("一切OK", dataSection);
    }

    /**
     * 将 List<CommodityInfo> 转 List<CommodityInfoDTO>
     *
     * @param commodities List<CommodityInfo>
     * @return
     */
    private List<CommodityDetailDTO> converterCommodityInfoListToCommodityInfoDTOList(List<CommodityInfo> commodities) {
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
    @PostMapping("/add")
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
