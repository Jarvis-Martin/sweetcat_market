package com.sweetcat.recommend.interfaces.web;

import com.sweetcat.commons.ResponseStatusEnum;
import com.sweetcat.commons.responsedto.ResponseDTO;
import com.sweetcat.recommend.application.command.AddCommodityRecommendCommand;
import com.sweetcat.recommend.domain.recommendform.entity.RecommendForm;
import com.sweetcat.recommend.interfaces.facade.UserCommodityRecommendFacade;
import com.sweetcat.recommend.interfaces.facade.assembler.UserCommodityRecommendAssembler;
import com.sweetcat.recommend.interfaces.facade.restdto.UserCommodityRecommendRestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/12-20:36
 * @version: 1.0
 */
@RestController
@RequestMapping("/recommend")
public class UserCommodityRecommendRestController {
    private UserCommodityRecommendFacade recommendFacade;
    private UserCommodityRecommendAssembler recommendAssembler;

    @Autowired
    public void setRecommendAssembler(UserCommodityRecommendAssembler recommendAssembler) {
        this.recommendAssembler = recommendAssembler;
    }

    @Autowired
    public void setRecommendFacade(UserCommodityRecommendFacade recommendFacade) {
        this.recommendFacade = recommendFacade;
    }

    /**
     * 添加商品推荐
     *
     * @param command
     */
    @PostMapping("/")
    public ResponseDTO addOne(AddCommodityRecommendCommand command) {
        recommendFacade.addOne(command);

        return response("推荐商品成功", "{}");
    }

    /**
     * 移除指定商品推荐
     *
     * @param recordId
     */
    @DeleteMapping("/{record_id}")
    public ResponseDTO remove(@PathVariable("record_id") Long recordId) {
        recommendFacade.remove(recordId);

        return response("删除推荐商品成功", "{}");
    }

    /**
     * 根据推荐人 id 查找分页数据
     *
     * @param referrerId
     * @param page
     * @param limit
     * @return
     */
    @GetMapping("/{referrer_id}")
    public ResponseDTO findPageByReferrerId(@PathVariable("referrer_id") Long referrerId, @RequestParam("_page") Integer page, @RequestParam("_limit") Integer limit) {
        List<RecommendForm> recommendFormPage = recommendFacade.findPageByReferrerId(referrerId, page, limit);
        if (recommendFormPage == null || recommendFormPage.isEmpty()) {
            return response("查询用户商品推荐记录(分页)成功", "{}");
        }

        HashMap<String, Object> dataSection = new HashMap<>(2);

        ArrayList<UserCommodityRecommendRestDTO> commodityRecommendRestDTOPage = recommendFormPage.stream().collect(
                ArrayList<UserCommodityRecommendRestDTO>::new,
                (con, recommendForm) -> con.add(recommendAssembler.converterToUserCommodityRecommendRestDTO(recommendForm)),
                ArrayList::addAll
        );
        dataSection.put("recommends", commodityRecommendRestDTOPage);
        return response("查询用户商品推荐记录(分页)成功", dataSection);
    }

    /**
     * 根据推荐记录 id 查找
     *
     * @param recordId recordId
     * @return
     */
    @GetMapping(value = "/{record_id}")
    public ResponseDTO findByRecordId(@PathVariable("record_id") Long recordId) {
        RecommendForm recommendForm = recommendFacade.findByRecordId(recordId);
        if (recommendForm == null) {
            return response("查询用户推荐商品记录成功", "{}");
        }
        HashMap<String, Object> dataSection = new HashMap<>(2);
        dataSection.put("recommend", recommendAssembler.converterToUserCommodityRecommendRestDTO(recommendForm));
        return response("查询用户推荐商品记录成功", dataSection);
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
