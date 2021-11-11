package com.sweetcat.footprint.interfaces.web;

import com.sweetcat.commons.ResponseStatusEnum;
import com.sweetcat.commons.responsedto.ResponseDTO;
import com.sweetcat.footprint.application.command.AddUserFootprintCommand;
import com.sweetcat.footprint.domain.carousel.entity.UserFootprint;
import com.sweetcat.footprint.interfaces.facade.UserFootprintFacade;
import com.sweetcat.footprint.interfaces.facade.assembler.UserFootprintAssembler;
import com.sweetcat.footprint.interfaces.facade.restdto.UserFootprintRestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/11-17:19
 * @version: 1.0
 */
@RestController
@RequestMapping("/footprint")
public class UserFootprintRestController {

    private UserFootprintFacade footprintFacade;
    private UserFootprintAssembler footprintAssembler;

    @Autowired
    public void setFootprintAssembler(UserFootprintAssembler footprintAssembler) {
        this.footprintAssembler = footprintAssembler;
    }

    @Autowired
    public void setFootprintFacade(UserFootprintFacade footprintFacade) {
        this.footprintFacade = footprintFacade;
    }

    /**
     * 添加一条足迹记录
     *
     * @param command
     */
    @PostMapping("/add")
    public ResponseDTO addOne(AddUserFootprintCommand command) {
        footprintFacade.addOne(command);
        return response("添加足迹成功", "{}");
    }

    /**
     * 删除一条足迹记录
     *
     * @param footprintId footprintId
     */
    @DeleteMapping("/delete/{footprint_id}")
    public ResponseDTO deleteOne(@PathVariable("footprint_id") Long footprintId) {
        footprintFacade.deleteOne(footprintId);
        return response("删除足迹成功", "{}");

    }

    /**
     * 根据时间戳查找该时间戳之前的足迹分页数据
     *
     * @param date  时间戳
     * @param page  page
     * @param limit limit
     * @return
     */
    @GetMapping("/{user_id}")
    public ResponseDTO findByDate(@PathVariable("user_id") Long userId, @RequestParam("date") Long date, @RequestParam("_page") Integer page, @RequestParam("_limit") Integer limit) {
        List<UserFootprint> footprintPage = footprintFacade.findByDate(userId, date, page, limit);

        HashMap<String, Object> dataSection = new HashMap<>(16);
        ArrayList<UserFootprintRestDTO> userFootprintRestDTOs = footprintPage.stream().collect(
                ArrayList<UserFootprintRestDTO>::new,
                (con, footprint) -> con.add(footprintAssembler.converterToUserFootprintRestDTO(footprint)),
                ArrayList::addAll
        );
        dataSection.put("footprints", userFootprintRestDTOs);
        return response("查询成功", dataSection);
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
