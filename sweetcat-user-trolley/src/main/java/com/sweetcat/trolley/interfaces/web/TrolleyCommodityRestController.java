package com.sweetcat.trolley.interfaces.web;

import com.sweetcat.commons.ResponseStatusEnum;
import com.sweetcat.commons.responsedto.ResponseDTO;
import com.sweetcat.trolley.application.command.AddTrolleyCommodityCommand;
import com.sweetcat.trolley.domain.commodity.entity.Commodity;
import com.sweetcat.trolley.interfaces.facade.CommodityFacade;
import com.sweetcat.trolley.interfaces.facade.assembler.TrolleyCommodityAssembler;
import com.sweetcat.trolley.interfaces.facade.restdto.TrolleyCommodityRestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/28-21:55
 * @version: 1.0
 */
@RestController
@RequestMapping("/trolley")
public class TrolleyCommodityRestController {
    private CommodityFacade commodityFacade;
    private TrolleyCommodityAssembler trolleyCommodityAssembler;

    @Autowired
    public void setTrolleyCommodityAssembler(TrolleyCommodityAssembler trolleyCommodityAssembler) {
        this.trolleyCommodityAssembler = trolleyCommodityAssembler;
    }

    @Autowired
    public void setCommodityFacade(CommodityFacade commodityFacade) {
        this.commodityFacade = commodityFacade;
    }

    @PostMapping("/add")
    public ResponseDTO addOneCommodity(AddTrolleyCommodityCommand command) {
        commodityFacade.addOneCommodity(command);
        return response("添加购物商品成功", "{}");
    }

    @DeleteMapping("/{commodity_id}")
    public ResponseDTO removeOne(Long userId, @PathVariable("commodity_id") Long commodityId) {
        commodityFacade.removeOne(userId, commodityId);
        return response("删除购物商品成功", "{}");
    }

    @GetMapping("/{commodity_id}/reduceCount")
    public ResponseDTO reduceCount(Long userId, @PathVariable("commodity_id") Long commodityId) {
        commodityFacade.reduceCount(userId, commodityId);
        return response("增加购物车商品数量成功", "{}");
    }

    @GetMapping("/{commodity_id}/increaseCount")
    public ResponseDTO increaseCount(Long userId, @PathVariable("commodity_id") Long commodityId) {
        commodityFacade.increaseCount(userId, commodityId);
        return response("减少购物车商品数量成功", "{}");
    }

    @GetMapping("/{user_id}")
    public ResponseDTO findPage(@PathVariable("user_id") Long userId, @RequestParam("_page") Integer page, @RequestParam("_limit") Integer limit) {
        List<Commodity> commodities = commodityFacade.findPageTrolleyCommodities(userId, page, limit);
        if (commodities == null) {
            return response("查询购物车商品成功", "{}");
        }
        ArrayList<TrolleyCommodityRestDTO> commodityRestDTOPage = commodities.stream().collect(
                ArrayList<TrolleyCommodityRestDTO>::new,
                (con, commodity) -> con.add(trolleyCommodityAssembler.converterToTrolleyCommodityRestDTO(commodity)),
                ArrayList<TrolleyCommodityRestDTO>::addAll
        );
        HashMap<String, Object> dataSection = new HashMap<>(2);
        dataSection.put("information", commodityRestDTOPage);
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
