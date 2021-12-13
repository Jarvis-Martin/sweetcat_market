package com.sweetcat.storeinfo.interfaces.web;

import com.sweetcat.commons.ResponseStatusEnum;
import com.sweetcat.commons.responsedto.ResponseDTO;
import com.sweetcat.storeinfo.application.commmand.AddStoreAddressCommand;
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
    public ResponseDTO getOneById(@PathVariable("store_id") Long storeId) {
        StoreAddress storeAddress = storeAddressFacade.getOneById(storeId);
        if (storeAddress == null) {
            return response("查询店铺地址信息成功", "{}");
        }
        HashMap<String, Object> storeAddressDTO = new HashMap<>(2);
        storeAddressDTO.put("address", storeAddressAssembler.converterToStoreAddressDTO(storeAddress));
        return response("一切OK", storeAddressDTO);
    }

    @PostMapping("/")
    public ResponseDTO addOne(AddStoreAddressCommand command) {
        storeAddressFacade.addOne(command);

        return response("添加店地址铺信息成功", "{}");
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
