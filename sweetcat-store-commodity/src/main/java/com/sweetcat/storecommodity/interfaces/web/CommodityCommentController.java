package com.sweetcat.storecommodity.interfaces.web;

import com.sweetcat.commons.ResponseStatusEnum;
import com.sweetcat.commons.responsedto.ResponseDTO;
import com.sweetcat.storecommodity.application.command.AddCommodityCommentCommand;
import com.sweetcat.storecommodity.domain.commoditycomment.entity.CommodityComment;
import com.sweetcat.storecommodity.interfaces.facade.CommodityCommentFacade;
import com.sweetcat.storecommodity.interfaces.facade.assembler.CommodityCommentAssembler;
import com.sweetcat.storecommodity.interfaces.facade.restdto.CommodityCommentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/9-20:05
 * @version: 1.0
 */
@RestController
@RequestMapping("/comment")
public class CommodityCommentController {
    private CommodityCommentFacade commentFacade;
    private CommodityCommentAssembler commentAssembler;

    @Autowired
    public void setCommentAssembler(CommodityCommentAssembler commentAssembler) {
        this.commentAssembler = commentAssembler;
    }

    @Autowired
    public void setCommentFacade(CommodityCommentFacade commentFacade) {
        this.commentFacade = commentFacade;
    }

    /**
     * find commodity comment by commentId
     *
     * @param commentId commentId
     * @return
     */
    @GetMapping("/{comment_id}")
    public ResponseDTO findByCommentId(@PathVariable("comment_id") Long commentId) {
        // 找到 DO
        CommodityComment commentDO = commentFacade.findByCommentId(commentId);
        // response 的 data
        HashMap<String, Object> dataSectioin = new HashMap<>(2);
        // DO 转 DTO
        CommodityCommentDTO commentDTO = commentAssembler.converterToCommodityCommentDTO(commentDO);
        // 装入 data
        dataSectioin.put("comment", commentDTO);
        // 返回 response
        return response("返回成功", dataSectioin);
    }

    /**
     * find commodity comment by commodityId
     *
     * @param commodityId commodityId
     * @param page
     * @param limit
     * @return
     */
    @GetMapping("/commodity/comments")
    public ResponseDTO findPageByCommodityId(Long commodityId, @RequestParam("_page") Integer page, @RequestParam("_limit") Integer limit) {
        // 找到 DO
        List<CommodityComment> commentDOPage = commentFacade.findPageByCommodityId(commodityId, page, limit);
        // response 的 data
        HashMap<String, Object> dataSectioin = new HashMap<>(2);
        // DO List 转 DTO List
        ArrayList<CommodityCommentDTO> commentDTOPage = commentDOPage.stream().collect(
                ArrayList<CommodityCommentDTO>::new,
                (con, commentDTO) -> con.add(commentAssembler.converterToCommodityCommentDTO(commentDTO)),
                ArrayList::addAll
        );
        // 装入 data
        dataSectioin.put("comments", commentDTOPage);
        // 返回 response
        return response("返回成功", dataSectioin);
    }

    /**
     * add a commodity comment
     *
     * @param command command
     */
    @PostMapping("/add")
    public ResponseDTO addOne(AddCommodityCommentCommand command) {
        commentFacade.addOne(command);
        return response("添加成功", "{}");
    }

    /**
     * remove a commodity comment
     *
     * @param commentId commentId
     */
    @GetMapping("/remove/{comment_id}")
    public ResponseDTO removeOne(@PathVariable("comment_id") Long commentId) {
        commentFacade.removeOne(commentId);
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
