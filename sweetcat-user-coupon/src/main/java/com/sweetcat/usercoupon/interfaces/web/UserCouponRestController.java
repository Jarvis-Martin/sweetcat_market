package com.sweetcat.usercoupon.interfaces.web;

import com.sweetcat.commons.ResponseStatusEnum;
import com.sweetcat.commons.responsedto.ResponseDTO;
import com.sweetcat.usercoupon.domain.usercoupon.entity.UserCoupon;
import com.sweetcat.usercoupon.interfaces.facade.UserCouponFacade;
import com.sweetcat.usercoupon.interfaces.facade.assembler.CouponRestAssembler;
import com.sweetcat.usercoupon.interfaces.facade.restdto.CouponRestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/22-13:23
 * @version: 1.0
 */
@RestController
@RequestMapping("/user_coupon")
public class UserCouponRestController {
    private UserCouponFacade userCouponFacade;
    private CouponRestAssembler couponRestAssembler;

    @Autowired
    public void setCouponRestAssembler(CouponRestAssembler couponRestAssembler) {
        this.couponRestAssembler = couponRestAssembler;
    }

    @Autowired
    public void setUserCouponFacade(UserCouponFacade userCouponFacade) {
        this.userCouponFacade = userCouponFacade;
    }

    /**
     * find userCoupon page by userId
     * @param userId
     * @param page
     * @param limit
     * @return
     */
    @GetMapping("/coupons")
    public ResponseDTO findPageByUserId(Long userId, Integer page, Integer limit) {
        List<UserCoupon> userCouponPage = userCouponFacade.findPageByUserId(userId, page, limit);
        if (userCouponPage == null) {
            return response("查询优惠券成功", "{}");
        }
        ArrayList<CouponRestDTO> couponRestDTOPage = userCouponPage.stream().collect(
                ArrayList<CouponRestDTO>::new,
                (con, userCoupon) -> con.add(couponRestAssembler.converterToCouponRestDTO(userCoupon)),
                ArrayList<CouponRestDTO>::addAll
        );

        HashMap<String, Object> dataSection = new HashMap<>(2);
        dataSection.put("coupons", couponRestDTOPage);
        return response("查询成功", dataSection);
    }

    /**
     * 移除
     *
     * @param userId
     * @param couponId
     */
    @DeleteMapping("/coupon/{coupon_id}")
    public ResponseDTO remove(Long userId, @PathVariable("coupon_id") Long couponId) {
        userCouponFacade.remove(userId, couponId);
        return response("删除优惠券成功", "{}");
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
