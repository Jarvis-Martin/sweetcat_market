package com.sweetcat.usercomment.interfaces.web;

import com.sweetcat.commons.ResponseStatusEnum;
import com.sweetcat.commons.responsedto.ResponseDTO;
import com.sweetcat.usercomment.application.command.AddCommentCommentCommand;
import com.sweetcat.usercomment.application.command.AddCommodityCommentCommand;
import com.sweetcat.usercomment.domain.comment.entity.Comment;
import com.sweetcat.usercomment.interfaces.facade.CommentFacade;
import com.sweetcat.usercomment.interfaces.facade.assembler.CommentAssembler;
import com.sweetcat.usercomment.interfaces.facade.restdto.CommentRestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/23-23:07
 * @version: 1.0
 */
@RestController
@RequestMapping("/user_comment")
public class CommentRestController {
    private CommentFacade commentFacade;
    private CommentAssembler commentAssembler;

    @Autowired
    public void setCommentAssembler(CommentAssembler commentAssembler) {
        this.commentAssembler = commentAssembler;
    }

    @Autowired
    public void setCommentFacade(CommentFacade commentFacade) {
        this.commentFacade = commentFacade;
    }

    /**
     * 添加一条 CommodityComment
     *
     * @param command
     */
    @PostMapping("/commodity_comment")
    public ResponseDTO addOneCommodityComment(AddCommodityCommentCommand command) {
        commentFacade.addOneCommodityComment(command);
        return response("添加商品评价成功", "{}");
    }

    /**
     * 添加一条 commentComment
     *
     * @param command
     */
    @PostMapping("/comment_comment")
    public ResponseDTO addOneCommentComment(AddCommentCommentCommand command) {
        commentFacade.addOneCommentComment(command);
        return response("添加评论成功", "{}");
    }

    /**
     * 移除一个
     *
     * @param commentId 评论id
     */
    @DeleteMapping("/{comment_id}")
    public ResponseDTO removeOne(@PathVariable("comment_id") Long commentId) {
        commentFacade.removeOne(commentId);
        return response("移除评论成功", "{}");
    }

    /**
     * 根据 commentId 查找 comment
     *
     * @param commentId
     * @return
     */
    @GetMapping("/comment/{comment_id}")
    public ResponseDTO findOneByCommentId(@PathVariable("comment_id") Long commentId) {
        Comment comment = commentFacade.findOneByCommentId(commentId);
        if (comment == null) {
            return response("查询评论成功", "[]");
        }
        HashMap<String, Object> dataSection = new HashMap<>(2);
        dataSection.put("comment", commentAssembler.converterToCommentRestDTO(comment));
        return response("查询成功", dataSection);
    }

    /**
     * 更加 publisherId 查找 commentPage
     *
     * @param publisherId
     * @return
     */
    @GetMapping("/comments")
    public ResponseDTO findPageByPublisherId(Long publisherId, @RequestParam("_page") Integer page, @RequestParam("_limit") Integer limit) {
        List<Comment> commentPage = commentFacade.findPageByPublisherId(publisherId, page, limit);
        if (commentPage == null || commentPage.isEmpty()) {
            return response("查询评论分页成功", "[]");
        }
        ArrayList<CommentRestDTO> commentRestDTOPage = commentPage.stream().collect(
                ArrayList<CommentRestDTO>::new,
                (con, comment) -> con.add(commentAssembler.converterToCommentRestDTO(comment)),
                ArrayList<CommentRestDTO>::addAll
        );
        HashMap<String, Object> dataSection = new HashMap<>(2);
        dataSection.put("comments", commentRestDTOPage);
        return response("查询评论分页成功", dataSection);
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
