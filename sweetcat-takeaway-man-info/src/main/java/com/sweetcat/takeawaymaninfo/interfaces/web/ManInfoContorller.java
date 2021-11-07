package com.sweetcat.takeawaymaninfo.interfaces.web;

import com.sweetcat.commons.ResponseStatusEnum;
import com.sweetcat.commons.responsedto.ResponseDTO;
import com.sweetcat.takeawaymaninfo.domain.maninfo.entity.ManInfo;
import com.sweetcat.takeawaymaninfo.interfaces.facade.ManInfoFacade;
import com.sweetcat.takeawaymaninfo.interfaces.facade.assembler.ManInfoAssembler;
import com.sweetcat.takeawaymaninfo.interfaces.facade.restdto.ManInfoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * @Author: Coder_Jarvis
 * @Description:
 * @Date: 2021-11-2021/11/7-14:37
 * @Version: 1.0
 */
@RestController
@RequestMapping("/takeaway")
public class ManInfoContorller {
    private ManInfoFacade manInfoFacade;
    private ManInfoAssembler manInfoAssembler;

    @Autowired
    public void setManInfoAssembler(ManInfoAssembler manInfoAssembler) {
        this.manInfoAssembler = manInfoAssembler;
    }

    @Autowired
    public void setManInfoFacade(ManInfoFacade manInfoFacade) {
        this.manInfoFacade = manInfoFacade;
    }

    /**
     * 添加
     *
     * @param name       name
     * @param phone      phone
     * @param createTime createTime
     */
    @PostMapping("")
    public ResponseDTO addOne(String name, String phone, Long createTime) {
        manInfoFacade.addOne(name, phone, createTime);
        return response("添加成功", "{}");
    }

    /**
     * find mainInfo by mainId
     *
     * @param manId manId
     * @return
     */
    @GetMapping("/{man_id}")
    public ResponseDTO getOne(@PathVariable("man_id") Long manId) {
        ManInfo manInfo = manInfoFacade.getOne(manId);
        HashMap<String, ManInfoDTO> manInfoDTO = new HashMap<>(2);
        manInfoDTO.put("takeawayManInfo", manInfoAssembler.converterToManInfoDTO(manInfo));
        return response("获取信息成功", manInfoDTO);
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
