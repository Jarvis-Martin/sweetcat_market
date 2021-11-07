package com.sweetcat.storeinfo.interfaces.web;

import com.sweetcat.commons.ResponseStatusEnum;
import com.sweetcat.commons.responsedto.ResponseDTO;
import com.sweetcat.storeinfo.domain.storeinfo.entity.StoreInfo;
import com.sweetcat.storeinfo.interfaces.facade.StoreInfoFacade;
import com.sweetcat.storeinfo.interfaces.facade.assembler.StoreInfoAssembler;
import com.sweetcat.storeinfo.interfaces.facade.restdto.StoreInfoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * @Author: Coder_Jarvis
 * @Description:
 * @Date: 2021-11-2021/11/7-17:31
 * @Version: 1.0
 */
@RestController
@RequestMapping("/store")
public class StoreInfoController {
    private StoreInfoFacade storeInfoFacade;
    private StoreInfoAssembler storeInfoAssembler;

    @Autowired
    public void setStoreInfoAssembler(StoreInfoAssembler storeInfoAssembler) {
        this.storeInfoAssembler = storeInfoAssembler;
    }

    @Autowired
    public void setStoreInfoFacade(StoreInfoFacade storeInfoFacade) {
        this.storeInfoFacade = storeInfoFacade;
    }

    /**
     * get store info by storeId
     *
     * @param storeId storeId
     * @return
     */
    @GetMapping("/{store_id}")
    public ResponseDTO getOneById(@PathVariable("store_id") Long storeId) {
        StoreInfo storeInfo = storeInfoFacade.getOneById(storeId);

        HashMap<String, Object> storeInfoDTO = new HashMap<>(2);
        storeInfoDTO.put("store_info", storeInfoAssembler.converterToStoreInfoDTO(storeInfo));

        return response("一切OK", storeInfoDTO);
    }

    @PostMapping("")
    public ResponseDTO addOne(String storeName, String principalName, String principalPhone, String mainBusiness, Integer type, Long createTime) {
        storeInfoFacade.addOne(storeName, principalName, principalPhone, mainBusiness, type, createTime);

        return response("添加成功", "{}");
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
