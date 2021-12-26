package com.sweetcat.user_info.interfaces.web.controller;

import com.sweetcat.commons.ResponseStatusEnum;
import com.sweetcat.commons.responsedto.ResponseDTO;
import com.sweetcat.user_info.application.command.address.AddAddressCommand;
import com.sweetcat.user_info.application.command.address.EditAddressCommand;
import com.sweetcat.user_info.domain.address.entity.UserAddress;
import com.sweetcat.user_info.interfaces.UserAddressFacade;
import com.sweetcat.user_info.interfaces.facade.assembler.UserAddressAssembler;
import com.sweetcat.user_info.interfaces.facade.restdto.UserAddressDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @Author: Coder_Jarvis
 * @Description:
 * @Date: 2021-10-2021/10/30-11:56
 * @Version: 1.0
 */
@RestController
public class UserAddressController {
    private UserAddressFacade userAddressFacade;
    private UserAddressAssembler userAddressAssembler;

    @Autowired
    public void setUserAddressFacade(UserAddressFacade userAddressFacade) {
        this.userAddressFacade = userAddressFacade;
    }

    @Autowired
    public void setUserAddressAssembler(UserAddressAssembler userAddressAssembler) {
        this.userAddressAssembler = userAddressAssembler;
    }

    @GetMapping("/user/{user_id}/addresses")
    public ResponseDTO getPage(@PathVariable("user_id") Long userId, @RequestParam("_page") Integer page, @RequestParam("_limit") Integer limit) {
        List<UserAddress> addressPage = userAddressFacade.getPage(userId, page, limit);
        if (addressPage == null || addressPage.isEmpty()) {
            return response("查询用户地址列表分页数据成功", "{}");
        }
        ArrayList<UserAddressDTO> userAddressDTOPage = addressPage.stream().collect(
                ArrayList<UserAddressDTO>::new,
                (con, userAddress) -> con.add(userAddressAssembler.converter2UserAddressDTO(userAddress)),
                ArrayList::addAll
        );
        HashMap<String, List<UserAddressDTO>> dataSection = new HashMap<>(16);
        dataSection.put("addresses", userAddressDTOPage);
        return response("查询用户地址列表分页数据成功", dataSection);
    }

    @PostMapping("/user/{user_id}/address")
    public ResponseDTO addAddress(AddAddressCommand command) {
        UserAddress userAddress = userAddressFacade.addAddress(command);

        UserAddressDTO userAddressDTO = userAddressAssembler.converter2UserAddressDTO(userAddress);
        HashMap<String, UserAddressDTO> addressDetail = new HashMap<>(2);
        addressDetail.put("address", userAddressDTO);
        return response("添加收货地址成功！", addressDetail);
    }

    @GetMapping("/user/{user_id}/address/{address_id}")
    public ResponseDTO getAddressDetail(@PathVariable("user_id") Long userId, @PathVariable("address_id") Long addressId) {
        UserAddress addressDetail = userAddressFacade.findAddressById(addressId);
        if (addressDetail == null) {
            return response("查询用户地址详细记录成功", "{}");
        }
        HashMap<String, UserAddressDTO> dataSection = new HashMap<>(2);
        dataSection.put("address_detail", userAddressAssembler.converter2UserAddressDTO(addressDetail));
        return response("查询用户地址详细记录成功", dataSection);

    }

    @DeleteMapping("/user/{user_id}/address/{address_id}")
    public ResponseDTO deleteAddress(@PathVariable("user_id") Long userId, @PathVariable("address_id") Long addressId) {
        userAddressFacade.deleteAddress(userId, addressId);

        return response("删除收货地址成功！", "{}");
    }

    /**
     * 设置为默认地址
     *
     * @param userId    userId
     * @param addressId addressId
     */
    @PatchMapping("/user/{user_id}/address/{address_id}/default")
    public ResponseDTO change2DefaultAddress(@PathVariable("user_id") Long userId, @PathVariable("address_id") Long addressId) {
        userAddressFacade.change2DefaultAddress(userId, addressId);
        return response("设置为默认地址成功", "{}");
    }


    /**
     * 设置为非默认地址
     *
     * @param userId    userId
     * @param addressId addressId
     */
    @PatchMapping("/user/{user_id}/address/{address_id}/notdefault")
    public ResponseDTO change2NotDefaultAddress(@PathVariable("user_id") Long userId, @PathVariable("address_id") Long addressId) {
        userAddressFacade.change2NotDefaultAddress(userId, addressId);
        return response("设置为非默认地址成功", "{}");
    }

    @PostMapping("/user/{user_id}/address/{address_id}/edit")
    public ResponseDTO editAddress(EditAddressCommand command) {
        userAddressFacade.editAddress(command);

        return response("修改收货地址成功！", "{}");
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
