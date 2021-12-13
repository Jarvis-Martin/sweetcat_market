package com.sweetcat.credit.interfaces.web;

import com.sweetcat.commons.ResponseStatusEnum;
import com.sweetcat.commons.responsedto.ResponseDTO;
import com.sweetcat.credit.domain.user.entity.User;
import com.sweetcat.credit.interfaces.facade.UserFacade;
import com.sweetcat.credit.interfaces.facade.assembler.UserAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/15-15:09
 * @version: 1.0
 */
@RestController
@RequestMapping("/credit/user")
public class UserRestController {
    private UserFacade userFacade;
    private UserAssembler userAssembler;

    @Autowired
    public void setUserAssembler(UserAssembler userAssembler) {
        this.userAssembler = userAssembler;
    }

    @Autowired
    public void setUserFacade(UserFacade userFacade) {
        this.userFacade = userFacade;
    }

    @GetMapping("/{user_id}")
    public ResponseDTO findOneByUserId(@PathVariable("user_id") Long userId) {
        User user = userFacade.findOneByUserId(userId);
        if (user == null) {
            return response("查询成功", "{}");
        }
        HashMap<String, Object> dataSection = new HashMap<>(2);
        dataSection.put("user", userAssembler.converterToUserRestDTO(user));
        return response("查询成功", dataSection);
    }

    /**
     * 用户签到功能
     *
     * @param userId
     */
    @PostMapping("/checkin")
    public ResponseDTO checkIn(Long userId) {
        userFacade.checkIn(userId);
        return response("签到成功", "{}");
    }


    /**
     * 用户兑换兑换商品
     *
     * @param userId
     * @param marketItemId
     */
    @PostMapping("/redeem/commodity/{commodity_id}")
    public ResponseDTO redeemCommodity(Long userId, @PathVariable("commodity_id") Long marketItemId, Long createTime) {
        userFacade.redeemCommodity(userId, marketItemId, createTime);
        return response("兑换商品成功", "{}");
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
