package com.sweetcat.user_info.interfaces.web.controller;

import com.sweetcat.commons.ResponseStatusEnum;
import com.sweetcat.commons.responsedto.ResponseDTO;
import com.sweetcat.user_info.application.command.address.AddAddressCommand;
import com.sweetcat.user_info.application.command.address.EditAddressCommand;
import com.sweetcat.user_info.domain.address.entity.UserAddress;
import com.sweetcat.user_info.interfaces.UserAddressFacade;
import com.sweetcat.user_info.interfaces.facade.UserInfoFacade;
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
    private UserInfoFacade userInfoFacade;
    private UserAddressFacade userAddressFacade;

    private UserAddressAssembler userAddressAssembler;

    @Autowired
    public void setFacade(UserInfoFacade userInfoFacade) {
        this.userInfoFacade = userInfoFacade;
    }

    @Autowired
    public void setUserAddressAssembler(UserAddressAssembler userAddressAssembler) {
        this.userAddressAssembler = userAddressAssembler;
    }

    @GetMapping("/user/{user_id}/address_list")
    public ResponseDTO getPage(@PathVariable("user_id") Long userId, @RequestParam("_page") Integer page, @RequestParam("_limit") Integer limit) {
        List<UserAddress> addressPage = userAddressFacade.getPage(userId, page, limit);

        ArrayList<UserAddressDTO> userAddressDTOPage = addressPage.stream().collect(
                ArrayList::new,
                (con, userAddress) -> con.add(userAddressAssembler.converter2UserAddressDTO(userAddress)),
                ArrayList::addAll
        );
        userAddressDTOPage.forEach(address -> {
            System.out.println(address.getAddressId());
        });
        HashMap<String, List<UserAddressDTO>> userAddressList = new HashMap<>(16);
        userAddressList.put("address_list", userAddressDTOPage);
        return response("一切OK", userAddressList);
    }

    @PostMapping("/user/{user_id}/address/add")
    public ResponseDTO addAddress(@PathVariable("user_id") Long userId, @RequestParam("receiverName") String receiverName, @RequestParam("receiverPhone") String receiverPhone,
                                  @RequestParam("provinceName") String provinceName, @RequestParam("cityName") String cityName, @RequestParam("areaName") String areaName,
                                  @RequestParam("townName") String townName, @RequestParam("detailAddress") String detailAddress, @RequestParam("defaultAddress") Integer defaultAddress,
                                  @RequestParam("createTime")Long createTime) {
        AddAddressCommand addAddressCommand = new AddAddressCommand(
                userId, receiverName, receiverPhone, provinceName, cityName, areaName, townName, detailAddress, defaultAddress, createTime
        );
        UserAddress userAddress = userAddressFacade.addAddress(addAddressCommand);

        UserAddressDTO userAddressDTO = userAddressAssembler.converter2UserAddressDTO(userAddress);
        HashMap<String, UserAddressDTO> addressDetail = new HashMap<>(2);
        addressDetail.put("address_detail", userAddressDTO);
        return response("添加收货地址成功！", addressDetail);
    }

    @GetMapping("/user/{user_id}/address/{address_id}")
    public ResponseDTO getAddressDetail(@PathVariable("user_id") Long userId, @PathVariable("address_id") Long addressId) {
        UserAddress addressDetail = userAddressFacade.findAddressById(addressId);

        HashMap<String, UserAddressDTO> addressDTODetail = new HashMap<>(2);
        addressDTODetail.put("address_detail", userAddressAssembler.converter2UserAddressDTO(addressDetail));
        return response("一切OK", addressDTODetail);

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
    public ResponseDTO editAddress(@PathVariable("user_id") Long userId, @PathVariable("address_id") Long addressId, @RequestParam("receiverName") String receiverName,
                                   @RequestParam("receiverPhone") String receiverPhone, @RequestParam("provinceName") String provinceName, @RequestParam("cityName") String cityName,
                                   @RequestParam("areaName") String areaName, @RequestParam("townName") String townName, @RequestParam("detailAddress") String detailAddress,
                                   @RequestParam("defaultAddress") Integer defaultAddress, @RequestParam("updateTime")Long updateTime) {
        EditAddressCommand editAddressCommand = new EditAddressCommand(
                addressId, userId, receiverName, receiverPhone, provinceName, cityName, areaName, townName, detailAddress, defaultAddress, updateTime
        );
        userAddressFacade.editAddress(editAddressCommand);

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
