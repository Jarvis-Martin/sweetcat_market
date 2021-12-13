package com.sweetcat.userinformation.interfaces.web;

import com.sweetcat.commons.ResponseStatusEnum;
import com.sweetcat.commons.responsedto.ResponseDTO;
import com.sweetcat.userinformation.domain.information.entity.Information;
import com.sweetcat.userinformation.interfaces.facade.InformationFacade;
import com.sweetcat.userinformation.interfaces.facade.assembler.InformationAssembler;
import com.sweetcat.userinformation.interfaces.facade.restdto.InformationRestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/25-22:05
 * @version: 1.0
 */
@RestController
@RequestMapping("/user_information")
public class InformationRestController {
    private InformationFacade informationFacade;
    private InformationAssembler informationAssembler;

    @Autowired
    public void setInformationAssembler(InformationAssembler informationAssembler) {
        this.informationAssembler = informationAssembler;
    }

    @Autowired
    public void setInformationFacade(InformationFacade informationFacade) {
        this.informationFacade = informationFacade;
    }

    /**
     * 根据 receiverId 查分页数据
     * @param receiverId
     * @param page
     * @param limit
     * @return
     */
    @GetMapping("/informations/{receiver_id}")
    public ResponseDTO findPageByReceiverId(@PathVariable("receiver_id") Long receiverId, Integer page, Integer limit) {
        List<Information> informationPage = informationFacade.findPageByReceiverId(receiverId, page, limit);
        if (informationPage == null || informationPage.size() <= 0) {
            return response("查询通知分页数据成功", "{}");
        }
        ArrayList<InformationRestDTO> informationRestDTOPage = informationPage.stream().collect(
                ArrayList<InformationRestDTO>::new,
                (con, information) -> con.add(informationAssembler.converterToRestDTO(information)),
                ArrayList<InformationRestDTO>::addAll
        );
        HashMap<String, Object> dataSection = new HashMap<>(2);
        dataSection.put("informations", informationRestDTOPage);
        return response("查询通知分页数据成功", dataSection);
    }

    /**
     * 根据 informationId 查找 information
     * @param informationId
     */
    @GetMapping("/information/{information_id}")
    public ResponseDTO findOneByInformationId(@PathVariable("information_id") Long informationId) {
        Information information = informationFacade.findOneByInformationId(informationId);
        HashMap<String, Object> dataSection = new HashMap<>(2);
        dataSection.put("information", informationAssembler.converterToRestDTO(information));
        return response("查询成功", dataSection);
    }

    /**
     * 移除
     * @param informationId
     */
    @DeleteMapping("/information/{information_id}")
    public ResponseDTO removeOne(@PathVariable("information_id") Long informationId) {
        informationFacade.removeOne(informationId);

        return response("删除成功", "{}");
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
