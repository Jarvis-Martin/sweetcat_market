package com.sweetcat.credit.interfaces.web;

import com.sweetcat.commons.ResponseStatusEnum;
import com.sweetcat.commons.responsedto.ResponseDTO;
import com.sweetcat.credit.domain.redeemlog.entity.RedeemLog;
import com.sweetcat.credit.interfaces.facade.RedeemLogFacade;
import com.sweetcat.credit.interfaces.facade.assembler.RedeemLogAssembler;
import com.sweetcat.credit.interfaces.facade.restdto.RedeemLogRestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/18-22:26
 * @version: 1.0
 */
@RestController
@RequestMapping("/credit")
public class RedeemLogRestController {
    private RedeemLogFacade redeemLogFacade;
    private RedeemLogAssembler redeemLogAssembler;

    @Autowired
    public void setRedeemLogAssembler(RedeemLogAssembler redeemLogAssembler) {
        this.redeemLogAssembler = redeemLogAssembler;
    }

    @Autowired
    public void setRedeemLogFacade(RedeemLogFacade redeemLogFacade) {
        this.redeemLogFacade = redeemLogFacade;
    }

    /**
     * 查找分页数据
     *
     * @param page
     * @param limit
     * @return
     */
    @GetMapping("/redeem_logs")
    public ResponseDTO findPage(@RequestParam("_page") Integer page, @RequestParam("_limit") Integer limit) {
        List<RedeemLog> redeemLogPage = redeemLogFacade.findPage(page, limit);
        if (redeemLogPage == null || redeemLogPage.size() < 0) {
            return response("查询成功", "{}");
        }
        ArrayList<RedeemLogRestDTO> redeemLogRestDTOPage = redeemLogPage.stream().collect(
                ArrayList<RedeemLogRestDTO>::new,
                (con, RedeemLogRestDTO) -> con.add(redeemLogAssembler.converterToRedeemLogRestDTO(RedeemLogRestDTO)),
                ArrayList::addAll
        );
        HashMap<String, Object> dataSection = new HashMap<>(2);
        dataSection.put("redeem_logs", redeemLogRestDTOPage);
        return response("查询成功", dataSection);
    }

    /**
     * 移除一条兑换记录
     *
     * @param userId
     * @param redeemLogId
     */
    @DeleteMapping("/redeem_log/{redeem_log_id}")
    public ResponseDTO remove(Long userId, @PathVariable("redeem_log_id") Long redeemLogId) {
        redeemLogFacade.remove(userId, redeemLogId);
        return response("删除 redeemLog 成功", "{}");
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
