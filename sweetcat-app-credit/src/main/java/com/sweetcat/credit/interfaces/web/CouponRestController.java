package com.sweetcat.credit.interfaces.web;

import com.sweetcat.commons.ResponseStatusEnum;
import com.sweetcat.commons.responsedto.ResponseDTO;
import com.sweetcat.credit.application.command.AddCouponCommand;
import com.sweetcat.credit.domain.commodity.entity.Coupon;
import com.sweetcat.credit.interfaces.facade.CouponFacade;
import com.sweetcat.credit.interfaces.facade.assembler.CommodityAssembler;
import com.sweetcat.credit.interfaces.facade.restdto.CreditCenterCommodityRestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/18-17:27
 * @version: 1.0
 */
@RestController
@RequestMapping("/credit/coupon")
public class CouponRestController {

    private CouponFacade couponFacade;
    private CommodityAssembler commodityAssembler;

    @Autowired
    public void setCouponFacade(CouponFacade couponFacade) {
        this.couponFacade = couponFacade;
    }

    @Autowired
    public void setCommodityAssembler(CommodityAssembler commodityAssembler) {
        this.commodityAssembler = commodityAssembler;
    }

    /**
     * 添加一种优惠券
     *
     * @param command
     */
    @PostMapping("/")
    public ResponseDTO addOne(AddCouponCommand command) {
        couponFacade.addOne(command);
        return response("插入积分优惠券成功", "{}");
    }

    /**
     * find data of coupon by targetType of coupon
     *
     * @param targetType
     * @param page
     * @param limit
     * @return
     */
    @GetMapping(value = "/commodities", params = {"targetType", "_page", "_limit"})
    public ResponseDTO findPageByTargetType(Long targetType, @RequestParam("_page") Integer page, @RequestParam("_limit") Integer limit) {
        List<Coupon> couponPage = couponFacade.findPageByTargetType(targetType, page, limit);
        if (couponPage == null || couponPage.isEmpty()) {
            return response("查询成功", "{}");
        }
        ArrayList<CreditCenterCommodityRestDTO> couponRestDTOPage = couponPage.stream().collect(
                ArrayList<CreditCenterCommodityRestDTO>::new,
                (con, couponRestDTO) -> con.add(commodityAssembler.converterToCreditCenterCommodityRestDTO(couponRestDTO)),
                ArrayList<CreditCenterCommodityRestDTO>::addAll
        );
        HashMap<String, Object> dataSection = new HashMap<>(2);
        dataSection.put("commodities", couponRestDTOPage);
        return response("查询成功", dataSection);
    }

    /**
     * find page data of timeType of coupon
     *
     * @param timeType
     * @param page
     * @param limit
     * @return
     */
    @GetMapping(value = "/commodities", params = {"timeType", "_page", "_limit"})
    public ResponseDTO findPageByTimeType(Long timeType, @RequestParam("_page") Integer page, @RequestParam("_limit") Integer limit) {
        List<Coupon> couponPage = couponFacade.findPageByTargetType(timeType, page, limit);
        if (couponPage == null || couponPage.isEmpty()) {
            return response("查询成功", "{}");
        }
        ArrayList<CreditCenterCommodityRestDTO> couponRestDTOPage = couponPage.stream().collect(
                ArrayList<CreditCenterCommodityRestDTO>::new,
                (con, couponRestDTO) -> con.add(commodityAssembler.converterToCreditCenterCommodityRestDTO(couponRestDTO)),
                ArrayList<CreditCenterCommodityRestDTO>::addAll
        );

        HashMap<String, Object> dataSection = new HashMap<>(2);
        dataSection.put("commodities", couponRestDTOPage);
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
