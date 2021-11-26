package com.sweetcat.userinformation.infrastructure.dao;

import com.sweetcat.userinformation.domain.information.entity.CommentReply;
import com.sweetcat.userinformation.infrastructure.po.CommentReplyPO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommentReplyMapper {
    /**
     * 根据 informationId 查找 commentReply
     * @param informationId
     * @return
     */
    CommentReplyPO findOneByInformationId(Long informationId);

    /**
     * 根据 commentReply
     * @param commentReply
     */
    void updateOne(CommentReply commentReply);

    /**
     *
     * @param commentReply
     */
    void deleteOne(CommentReply commentReply);

    /**
     * 添加
     * @param commentReply
     */
    void addOne(CommentReply commentReply);
}