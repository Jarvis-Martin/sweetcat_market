package com.sweetcat.couponcenter.interfaces.web;

import com.sweetcat.commons.ResponseStatusEnum;
import com.sweetcat.commons.responsedto.ResponseDTO;
import com.sweetcat.couponcenter.application.command.AddCommodityCouponCommand;
import com.sweetcat.couponcenter.application.command.AddUniversalCouponCommand;
import com.sweetcat.couponcenter.domain.coupon.entity.CommodityCoupon;
import com.sweetcat.couponcenter.domain.coupon.entity.UniversalCoupon;
import com.sweetcat.couponcenter.interfaces.facade.CommodityCouponFacade;
import com.sweetcat.couponcenter.interfaces.facade.CouponFacade;
import com.sweetcat.couponcenter.interfaces.facade.UniversalCouponFacade;
import com.sweetcat.couponcenter.interfaces.facade.assembler.CouponRestAssembler;
import com.sweetcat.couponcenter.interfaces.facade.restdto.CouponRestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/20-22:50
 * @version: 1.0
 */
@RestController
@RequestMapping("/coupon_center")
public class CouponRestController {
    private CouponFacade couponFacade;
    private UniversalCouponFacade universalCouponFacade;
    private CommodityCouponFacade commodityCouponFacade;
    private CouponRestAssembler couponAssembler;

    @Autowired
    public void setCouponFacade(CouponFacade couponFacade) {
        this.couponFacade = couponFacade;
    }

    @Autowired
    public void setCouponAssembler(CouponRestAssembler couponAssembler) {
        this.couponAssembler = couponAssembler;
    }

    @Autowired
    public void setUniversalCouponFacade(UniversalCouponFacade universalCouponFacade) {
        this.universalCouponFacade = universalCouponFacade;
    }

    @Autowired
    public void setCommodityCouponFacade(CommodityCouponFacade commodityCouponFacade) {
        this.commodityCouponFacade = commodityCouponFacade;
    }


    /**
     * 查询分页数据
     *
     * @param page
     * @param limit
     * @return
     */
    @GetMapping("/coupons/commodity")
    public ResponseDTO findPageCommodityCoupon(@RequestParam("_page") Integer page, @RequestParam("_limit") Integer limit) {
        List<CommodityCoupon> commodityCouponPage = commodityCouponFacade.findPage(page, limit);
        if (commodityCouponPage == null || commodityCouponPage.isEmpty()) {
            return response("查询成功", "{}");
        }
        ArrayList<CouponRestDTO> commodityCouponRestDTOPage = commodityCouponPage.stream().collect(
                ArrayList<CouponRestDTO>::new,
                (con, commodityCoupon) -> con.add(couponAssembler.converterToCouponRestDTO(commodityCoupon)),
                ArrayList<CouponRestDTO>::addAll
        );

        HashMap<String, Object> dataSection = new HashMap<>(2);
        dataSection.put("coupons", commodityCouponRestDTOPage);
        return response("查询成功", dataSection);
    }


    /**
     * 查询分页数据
     *
     * @param page
     * @param limit
     * @return
     */
    @GetMapping("/coupons/universal")
    public ResponseDTO findPageUniversalCoupon(@RequestParam("_page") Integer page, @RequestParam("_limit") Integer limit) {
        List<UniversalCoupon> universalCouponPage = universalCouponFacade.findPage(page, limit);
        if (universalCouponPage == null || universalCouponPage.isEmpty()) {
            return response("查询成功", "{}");
        }
        ArrayList<CouponRestDTO> commodityCouponRestDTOPage = universalCouponPage.stream().collect(
                ArrayList<CouponRestDTO>::new,
                (con, universalCouponRestDTO) -> con.add(couponAssembler.converterToCouponRestDTO(universalCouponRestDTO)),
                ArrayList<CouponRestDTO>::addAll
        );

        HashMap<String, Object> dataSection = new HashMap<>(2);
        dataSection.put("coupons", commodityCouponRestDTOPage);
        return response("查询成功", dataSection);
    }

    @PostMapping("/commodity_coupon")
    public ResponseDTO addCommodityCoupon(AddCommodityCouponCommand command) {
        commodityCouponFacade.addOne(command);

        return response("插入成功", "{}");

    }

    @PostMapping("/universal_coupon")
    public ResponseDTO addUniversalCoupon(AddUniversalCouponCommand command) {
        universalCouponFacade.addOne(command);

        return response("插入成功", "{}");

    }

    /**
     * 领取优惠券
     * @param userId
     * @param couponId
     * @return
     */
    @PostMapping("/coupon/{coupon_id}")
    public ResponseDTO getOneCoupon(Long userId, @PathVariable("coupon_id") Long couponId) {
        couponFacade.getOneCoupon(userId, couponId);

        return response("领取优惠券成功", "{}");
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
