package com.sweetcat.usercoupon.interfaces.rpc;

import com.sweetcat.api.rpcdto.usercoupon.CouponInfoRpcDTO;
import com.sweetcat.usercoupon.domain.usercoupon.entity.UserCoupon;
import com.sweetcat.usercoupon.interfaces.facade.UserCouponFacade;
import com.sweetcat.usercoupon.interfaces.facade.assembler.CouponRpcAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-12-2021/12/1-14:33
 * @version: 1.0
 */
@RestController
@RequestMapping("/rpc/user_coupon")
public class UserCouponRpcController {
    private UserCouponFacade userCouponFacade;
    private CouponRpcAssembler couponRpcAssembler;

    @Autowired
    public void setCouponRpcAssembler(CouponRpcAssembler couponRpcAssembler) {
        this.couponRpcAssembler = couponRpcAssembler;
    }

    @Autowired
    public void setUserCouponFacade(UserCouponFacade userCouponFacade) {
        this.userCouponFacade = userCouponFacade;
    }

    @GetMapping("{coupon_id}")
    public CouponInfoRpcDTO findOneByCouponId(Long userId, @PathVariable("coupon_id") Long couponId) {
        UserCoupon userCoupon = userCouponFacade.findOneByCouponId(userId, couponId);
        return couponRpcAssembler.converterToCouponRpcDTO(userCoupon);
    }
}
