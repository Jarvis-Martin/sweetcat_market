package com.sweetcat.couponcenter.interfaces.rpc;

import com.sweetcat.api.rpcdto.couponcenter.CouponRpcDTO;
import com.sweetcat.couponcenter.domain.coupon.entity.CommodityCoupon;
import com.sweetcat.couponcenter.domain.coupon.entity.Coupon;
import com.sweetcat.couponcenter.domain.coupon.entity.UniversalCoupon;
import com.sweetcat.couponcenter.domain.coupon.vo.CouponTargetType;
import com.sweetcat.couponcenter.interfaces.facade.CommodityCouponFacade;
import com.sweetcat.couponcenter.interfaces.facade.CouponFacade;
import com.sweetcat.couponcenter.interfaces.facade.UniversalCouponFacade;
import com.sweetcat.couponcenter.interfaces.facade.assembler.CouponRpcAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/22-11:06
 * @version: 1.0
 */
@RestController
@RequestMapping("/rpc/coupon_center")
public class CouponRpcController {
    private CouponRpcAssembler couponRpcAssembler;
    private CouponFacade couponFacade;
    private CommodityCouponFacade commodityCouponFacade;
    private UniversalCouponFacade universalCouponFacade;

    @Autowired
    public void setCommodityCouponFacade(CommodityCouponFacade commodityCouponFacade) {
        this.commodityCouponFacade = commodityCouponFacade;
    }

    @Autowired
    public void setUniversalCouponFacade(UniversalCouponFacade universalCouponFacade) {
        this.universalCouponFacade = universalCouponFacade;
    }

    @Autowired
    public void setCouponFacade(CouponFacade couponFacade) {
        this.couponFacade = couponFacade;
    }

    @Autowired
    public void setCouponRpcAssembler(CouponRpcAssembler couponRpcAssembler) {
        this.couponRpcAssembler = couponRpcAssembler;
    }

    @GetMapping("/{coupon_id}")
    public CouponRpcDTO findOneByCouponId(@PathVariable("coupon_id") Long couponId) {
        Coupon coupon = couponFacade.findByCouponId(couponId);
        if (coupon == null) {
            return null;
        }
        if (CouponTargetType.TARGETTYPE_COMMODITY.equals(coupon.getTargetType())) {
            CommodityCoupon commodityCoupon = commodityCouponFacade.findOneByCouponId(couponId);
            if (commodityCoupon == null) {
                return null;
            }
            return couponRpcAssembler.converterToCouponRpcDTO(commodityCoupon);
        }
        if (CouponTargetType.TARGETTYPE_UNIVERSAL.equals(coupon.getTargetType())) {
            UniversalCoupon universalCoupon = universalCouponFacade.findOneByCouponId(couponId);
            if (universalCoupon == null) {
                return null;
            }
            return couponRpcAssembler.converterToCouponRpcDTO(universalCoupon);
        }
        return null;
    }
}
