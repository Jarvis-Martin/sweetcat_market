package com.sweetcat.secondkill.interfaces.web;

import com.sweetcat.commons.ResponseStatusEnum;
import com.sweetcat.commons.responsedto.ResponseDTO;
import com.sweetcat.secondkill.application.command.AddSKCommodityCommentCommand;
import com.sweetcat.secondkill.domain.commoditycomment.entity.SKCommodityComment;
import com.sweetcat.secondkill.interfaces.facade.SKCommodityCommentFacade;
import com.sweetcat.secondkill.interfaces.facade.assembler.SKCommodityCommentRestAssembler;
import com.sweetcat.secondkill.interfaces.facade.restdto.SKCommodityCommentDTO;
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
@RequestMapping("/secondkill/comment")
public class SKCommodityCommentController {
    private SKCommodityCommentFacade commentFacade;
    private SKCommodityCommentRestAssembler commentAssembler;

    @Autowired
    public void setCommentFacade(SKCommodityCommentFacade commentFacade) {
        this.commentFacade = commentFacade;
    }

    @Autowired
    public void setCommentAssembler(SKCommodityCommentRestAssembler commentAssembler) {
        this.commentAssembler = commentAssembler;
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
        SKCommodityComment commentDO = commentFacade.findByCommentId(commentId);
        if (commentDO == null) {
            return response("查询商品评论成功", "{}");
        }
        // response 的 data
        HashMap<String, Object> dataSection = new HashMap<>(2);
        // DO 转 DTO
        SKCommodityCommentDTO commentDTO = commentAssembler.converterToSKCommodityCommentDTO(commentDO);
        // 装入 data
        dataSection.put("comment", commentDTO);
        // 返回 response
        return response("查询商品评论成功", dataSection);
    }

    /**
     * find commodity comment by commodityId
     *
     * @param commodityId commodityId
     * @param page
     * @param limit
     * @return
     */
    @GetMapping("/comments")
    public ResponseDTO findPageByCommodityId(Long commodityId, @RequestParam("_page") Integer page, @RequestParam("_limit") Integer limit) {
        // 找到 DO
        List<SKCommodityComment> commentDOPage = commentFacade.findPageByCommodityId(commodityId, page, limit);
        if (commentDOPage == null) {
            return response("返回商品评论分页数据成功", "{}");
        }
        // response 的 data
        HashMap<String, Object> dataSectioin = new HashMap<>(2);
        // DO List 转 DTO List
        ArrayList<SKCommodityCommentDTO> commentDTOPage = commentDOPage.stream().collect(
                ArrayList<SKCommodityCommentDTO>::new,
                (con, commentDTO) -> con.add(commentAssembler.converterToSKCommodityCommentDTO(commentDTO)),
                ArrayList::addAll
        );
        // 装入 data
        dataSectioin.put("comments", commentDTOPage);
        // 返回 response
        return response("返回商品评论分页数据成功", dataSectioin);
    }

    /**
     * add a commodity comment
     *
     * @param command command
     */
    @PostMapping("/")
    public ResponseDTO addOne(AddSKCommodityCommentCommand command) {
        commentFacade.addOne(command);
        return response("添加成功", "{}");
    }

    /**
     * remove a commodity comment
     *
     * @param commentId commentId
     */
    @DeleteMapping("/{comment_id}")
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
