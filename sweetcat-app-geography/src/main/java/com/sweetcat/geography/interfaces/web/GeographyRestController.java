package com.sweetcat.geography.interfaces.web;

import com.sweetcat.commons.ResponseStatusEnum;
import com.sweetcat.commons.responsedto.ResponseDTO;
import com.sweetcat.geography.application.command.AddGeographyCommand;
import com.sweetcat.geography.domain.geography.entity.Geography;
import com.sweetcat.geography.interfaces.facade.GeographyFacade;
import com.sweetcat.geography.interfaces.facade.assembler.RestDTOAssembler;
import com.sweetcat.geography.interfaces.facade.restdto.GeographyRestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/10-15:51
 * @version: 1.0
 */
@RestController
@RequestMapping("/geography")
public class GeographyRestController {
    private GeographyFacade geographyFacade;
    private RestDTOAssembler dtoAssembler;

    @Autowired
    public void setDtoAssembler(RestDTOAssembler dtoAssembler) {
        this.dtoAssembler = dtoAssembler;
    }

    @Autowired
    public void setGeographyFacade(GeographyFacade geographyFacade) {
        this.geographyFacade = geographyFacade;
    }

    /**
     * 根据 addressCode 查找一个 geography
     *
     * @param addressCode addressCode
     * @return
     */
    @GetMapping("/{address_code}")
    public ResponseDTO getGeographyInfo(@PathVariable("address_code") String addressCode) {
        Geography geography = geographyFacade.find(addressCode);
        if (geography == null) {
            return response("查询结果为空", "{}");
        }
        HashMap<String, Object> dataSection = new HashMap<>(2);
        GeographyRestDTO geographyRestDTO = dtoAssembler.converterGeographyRestDTO(geography);
        dataSection.put("geography", geographyRestDTO);
        return response("查询成共", dataSection);
    }

    /**
     * 添加一条geography
     *
     * @param geographyCommand
     */
    @PostMapping("/add")
    public ResponseDTO addOne(AddGeographyCommand geographyCommand) {
        geographyFacade.addOne(geographyCommand);
        return response("插入成功", "{}");
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
