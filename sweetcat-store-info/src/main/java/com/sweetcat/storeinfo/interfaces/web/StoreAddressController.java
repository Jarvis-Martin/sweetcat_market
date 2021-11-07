package com.sweetcat.storeinfo.interfaces.web;

import com.sweetcat.commons.ResponseStatusEnum;
import com.sweetcat.commons.responsedto.ResponseDTO;
import com.sweetcat.storeinfo.domain.storeaddress.entity.StoreAddress;
import com.sweetcat.storeinfo.interfaces.facade.StoreAddressFacade;
import com.sweetcat.storeinfo.interfaces.facade.assembler.StoreAddressAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * @Author: Coder_Jarvis
 * @Description:
 * @Date: 2021-11-2021/11/7-19:53
 * @Version: 1.0
 */
@RestController
@RequestMapping("/store/address")
public class StoreAddressController {
    private StoreAddressFacade storeAddressFacade;
    private StoreAddressAssembler storeAddressAssembler;

    @Autowired
    public void setStoreAddressAssembler(StoreAddressAssembler storeAddressAssembler) {
        this.storeAddressAssembler = storeAddressAssembler;
    }

    @Autowired
    public void setStoreAddressFacade(StoreAddressFacade storeAddressFacade) {
        this.storeAddressFacade = storeAddressFacade;
    }

    @GetMapping("/{store_id}")
    public ResponseDTO getOneById(@PathVariable("/store_id") Long storeId) {
        StoreAddress storeAddress = storeAddressFacade.getOneById(storeId);

        HashMap<String, Object> storeAddressDTO = new HashMap<>(2);
        storeAddressDTO.put("store_address", storeAddressAssembler.converterToStoreAddressDTO(storeAddress));
        return response("一切OK", storeAddressDTO);
    }

    @PostMapping("/add")
    public ResponseDTO addOne(Long storeId, String provinceName, String cityName, String areaName, String townName, String detailAddress,
                              Long createTime) {
        storeAddressFacade.addOne(storeId, provinceName, cityName, areaName, townName, detailAddress, createTime);

        return response("一切OK", "{}");
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
