package com.sweetcat.userinformation.infrastructure.repository;

import com.sweetcat.userinformation.domain.information.entity.CommentReply;
import com.sweetcat.userinformation.domain.information.entity.Information;
import com.sweetcat.userinformation.domain.information.entity.SystemInformation;
import com.sweetcat.userinformation.domain.information.repository.InformationRepository;
import com.sweetcat.userinformation.infrastructure.dao.CommentReplyMapper;
import com.sweetcat.userinformation.infrastructure.dao.InformationMapper;
import com.sweetcat.userinformation.infrastructure.dao.SystemInformationMapper;
import com.sweetcat.userinformation.infrastructure.factory.InformationFactory;
import com.sweetcat.userinformation.infrastructure.po.CommentReplyPO;
import com.sweetcat.userinformation.infrastructure.po.InformationPO;
import com.sweetcat.userinformation.infrastructure.po.SystemInformationPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/25-18:56
 * @version: 1.0
 */
@Repository
public class InformationRepositoryImpl implements InformationRepository {
    private InformationMapper informationMapper;
    private CommentReplyMapper commentReplyMapper;
    private SystemInformationMapper systemInformationMapper;
    private InformationFactory informationFactory;

    @Autowired
    public void setSystemInformationMapper(SystemInformationMapper systemInformationMapper) {
        this.systemInformationMapper = systemInformationMapper;
    }

    @Autowired
    public void setCommentReplyMapper(CommentReplyMapper commentReplyMapper) {
        this.commentReplyMapper = commentReplyMapper;
    }

    @Autowired
    public void setInformationFactory(InformationFactory informationFactory) {
        this.informationFactory = informationFactory;
    }

    @Autowired
    public void setInformationMapper(InformationMapper informationMapper) {
        this.informationMapper = informationMapper;
    }

    /**
     * 根据 receiverId 查分页数据
     * @param receiverId
     * @param page
     * @param limit
     * @return
     */
    @Override
    public <T extends Information> List<T> findPageByReceiverId(Long receiverId, Integer page, Integer limit) {
        List<InformationPO> informationPOPage = informationMapper.findPageByReceiverId(receiverId, page, limit);
        if (informationPOPage == null|| informationPOPage.isEmpty()) {
            return Collections.emptyList();
        }
        return informationPOPage.stream().collect(
                ArrayList<T>::new,
                (con, informationPO) -> {
                    CommentReplyPO commentReplyPO = commentReplyMapper.findOneByInformationId(informationPO.getInformationId());
                    con.add((T) informationFactory.create(informationPO, commentReplyPO));
                },
                ArrayList<T>::addAll
        );
    }

    /**
     * 根据 informationId 查找 information
     * @param informationId
     * @param <T>
     */
    @Override
    public <T extends Information> T findOneByInformationId(Long informationId) {
        InformationPO informationPO = informationMapper.findOneByInformationId(informationId);
        if (informationPO == null) {
            return null;
        }
        // 评论回复
        if (Information.TYPE_COMMENTREPLY.equals(informationPO.getType())) {
            CommentReplyPO commentReplyPO = commentReplyMapper.findOneByInformationId(informationId);
            return (T) informationFactory.create(informationPO, commentReplyPO);
        }
        // 系统通知
        if (Information.TYPE_SYSTEMINFORMATION.equals(informationPO.getType())) {
            SystemInformationPO systemInformationPO = systemInformationMapper.findOneByInformationId(informationId);
            return (T) informationFactory.create(informationPO, systemInformationPO);
        }
        return null;
    }

    /**
     * 保存 informatio 的修改
     * @param information
     * @param <T>
     */
    @Override
    public <T extends Information> void save(T information) {
        informationMapper.updateOne(information);
    }

    /**
     * 移除
     * @param information
     * @param <T>
     */
    @Override
    public <T extends Information> void removeOne(T information) {
        informationMapper.deleteOne(information);
        if (Information.TYPE_COMMENTREPLY.equals(information.getType())) {
            commentReplyMapper.deleteOne(((CommentReply) information));
        }
        if (Information.TYPE_SYSTEMINFORMATION.equals(information.getType())) {
            systemInformationMapper.deleteOne(((SystemInformation) information));
        }
    }

    /**
     * 添加
     * @param information
     * @param <T>
     */
    @Override
    public <T extends Information> void addOne(T information) {
        informationMapper.addOne(information);
        if (Information.TYPE_SYSTEMINFORMATION.equals(information.getType())) {
            SystemInformation systemInformation = (SystemInformation) information;
            systemInformationMapper.addOne(systemInformation);
        }
        if (Information.TYPE_COMMENTREPLY.equals(information.getType())) {
            CommentReply commentReply = (CommentReply) information;
            commentReplyMapper.addOne(commentReply);
        }
    }
}
