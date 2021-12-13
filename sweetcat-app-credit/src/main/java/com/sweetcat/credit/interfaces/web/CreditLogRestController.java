package com.sweetcat.credit.interfaces.web;

import com.sweetcat.commons.ResponseStatusEnum;
import com.sweetcat.commons.responsedto.ResponseDTO;
import com.sweetcat.credit.domain.creditlog.entity.CreditLog;
import com.sweetcat.credit.interfaces.facade.CreditLogFacade;
import com.sweetcat.credit.interfaces.facade.assembler.CreditLogAssembler;
import com.sweetcat.credit.interfaces.facade.restdto.CreditLogRestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/16-14:05
 * @version: 1.0
 */
@RestController
@RequestMapping("/credit")
public class CreditLogRestController {
    private CreditLogFacade logFacade;
    private CreditLogAssembler logAssembler;

    @Autowired
    public void setLogAssembler(CreditLogAssembler logAssembler) {
        this.logAssembler = logAssembler;
    }

    @Autowired
    public void setLogFacade(CreditLogFacade logFacade) {
        this.logFacade = logFacade;
    }

    /**
     * 获得指定月份的分页数据
     *
     * @param timestamp
     * @param userId
     * @param page
     * @param limit
     * @return
     */
    @GetMapping(value = "/creditlogs/1/{user_id}")
    public ResponseDTO findPageForCurrentMonth(Long timestamp, @PathVariable("user_id") Long userId, @RequestParam("_page") Integer page, @RequestParam("_limit") Integer limit) {
        List<CreditLog> creditLogPage = logFacade.findPageForCurrentMonth(timestamp, userId, page, limit);
        if (creditLogPage == null || creditLogPage.size() <= 0) {
            return response("获得指定月份的分页数据成功", "{}");
        }
        ArrayList<CreditLogRestDTO> logRestDTOPage = creditLogPage.stream().collect(
                ArrayList<CreditLogRestDTO>::new,
                (con, creditLog) -> con.add(logAssembler.converterToCreditLogRestDTO(creditLog)),
                ArrayList::addAll
        );

        HashMap<String, Object> dataSection = new HashMap<>(2);
        dataSection.put("creditLogs", logRestDTOPage);
        return response("查询本欲积分记录成功", dataSection);

    }

    /**
     * 获得近3个月的积分收支记录的分页数据
     *
     * @param timestamp
     * @param userId
     * @param page
     * @param limit
     * @return
     */
    @GetMapping(value = "/creditlogs/3/{user_id}")
    public ResponseDTO findPageForNearlyThreeMonths(Long timestamp, @PathVariable("user_id") Long userId,
                                                    @RequestParam("_page") Integer page, @RequestParam("_limit") Integer limit) {
        List<CreditLog> creditLogPage = logFacade.findPageForNearlyThreeMonths(timestamp, userId, page, limit);
        if (creditLogPage == null || creditLogPage.size() <= 0) {
            return response("获得近3个月的积分收支记录的分页数据", "{}");
        }
        ArrayList<CreditLogRestDTO> logRestDTOPage = creditLogPage.stream().collect(
                ArrayList<CreditLogRestDTO>::new,
                (con, creditLog) -> con.add(logAssembler.converterToCreditLogRestDTO(creditLog)),
                ArrayList::addAll
        );

        HashMap<String, Object> dataSection = new HashMap<>(2);
        dataSection.put("creditLogs", logRestDTOPage);
        return response("查询近3月积分记录成功", dataSection);
    }


    /**
     * 获得本月总收入
     *
     * @param timestamp
     * @param userId
     * @param page
     * @param limit
     * @return
     */
    @GetMapping("/income/1/{user_id}")
    public ResponseDTO totalIncomeForThisMonth(Long timestamp, @PathVariable("user_id") Long userId, @RequestParam("_page") Integer page, @RequestParam("_limit") Integer limit) {
        long credit = logFacade.totalIncomeForThisMonth(timestamp, userId, page, limit);
        Map<String, Object> dataSection = new HashMap<String, Object>(2);
        dataSection.put("totalIncomeForThisMonth", credit);
        return response("查询本月总收入成功", dataSection);
    }

    /**
     * 获得本月总支出
     *
     * @param timestamp
     * @param userId
     * @param page
     * @param limit
     * @return
     */
    @GetMapping("/outcome/1/{user_id}")
    public ResponseDTO totalOutComeForThisMonth(Long timestamp, @PathVariable("user_id") Long userId, @RequestParam("_page") Integer page, @RequestParam("_page") Integer limit) {
        long credit = logFacade.totalOutComeForThisMonth(timestamp, userId, page, limit);
        Map<String, Object> dataSection = new HashMap<String, Object>(2);
        dataSection.put("totalOutComeForThisMonth", credit);
        return response("查询本月总支出成功", dataSection);
    }

    /**
     * 获得近3个月总收入
     *
     * @param timestamp
     * @param userId
     * @param page
     * @param limit
     * @return
     */
    @GetMapping("/income/3/{user_id}")
    public ResponseDTO totalIncomeForNearlyThreeMonths(Long timestamp, @PathVariable("user_id") Long userId, @RequestParam("_page") Integer page, @RequestParam("_limit") Integer limit) {
        long credit = logFacade.totalIncomeForNearlyThreeMonths(timestamp, userId, page, limit);
        Map<String, Object> dataSection = new HashMap<String, Object>(2);
        dataSection.put("totalIncomeForNearlyThreeMonths", credit);
        return response("查询近3个月总收入成功", dataSection);
    }

    /**
     * 获得近3个月总支出
     *
     * @param timestamp
     * @param userId
     * @param page
     * @param limit
     * @return
     */
    @GetMapping("/outcome/3/{user_id}")
    public ResponseDTO totalOutComeForNearlyThreeMonths(Long timestamp, @PathVariable("user_id") Long userId, @RequestParam("_page") Integer page, @RequestParam("_limit") Integer limit) {
        long credit = logFacade.totalOutComeForNearlyThreeMonths(timestamp, userId, page, limit);
        Map<String, Object> dataSection = new HashMap<String, Object>(2);
        dataSection.put("totalOutComeForNearlyThreeMonths", credit);
        return response("查询近3个月总支出成功", dataSection);

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
