package com.sweetcat.userinformation.infrastructure.factory;

import com.sweetcat.userinformation.domain.information.entity.CommentReply;
import com.sweetcat.userinformation.domain.information.entity.SystemInformation;
import com.sweetcat.userinformation.domain.information.vo.Commodity;
import com.sweetcat.userinformation.domain.information.vo.Publisher;
import com.sweetcat.userinformation.domain.information.vo.Receiver;
import com.sweetcat.userinformation.domain.information.vo.Store;
import com.sweetcat.userinformation.infrastructure.po.CommentReplyPO;
import com.sweetcat.userinformation.infrastructure.po.InformationPO;
import com.sweetcat.userinformation.infrastructure.po.SystemInformationPO;
import org.springframework.stereotype.Component;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/25-20:44
 * @version: 1.0
 */
@Component
public class InformationFactory {
    public CommentReply create(InformationPO informationPO, CommentReplyPO commentReplyPO) {
        CommentReply commentReply = new CommentReply(informationPO.getInformationId());
        Commodity commodity = new Commodity(commentReplyPO.getCommodityId());
        Publisher publisher = new Publisher(informationPO.getPublisherId());
        Receiver receiver = new Receiver(informationPO.getReceiverId());
        Store store = new Store(commentReplyPO.getStoreId());
        // 填充 CommentReplyPO
        inflateCommnetReply(informationPO, commentReplyPO, commentReply, commodity, publisher, receiver, store);
        return commentReply;
    }

    public SystemInformation create(InformationPO informationPO, SystemInformationPO systemInformationPO) {
        SystemInformation systemInformation = new SystemInformation(informationPO.getInformationId());
        Publisher publisher = new Publisher(informationPO.getPublisherId());
        Receiver receiver = new Receiver(informationPO.getReceiverId());
        // 填充 systemInformation
        inflateSystemInformation(informationPO, systemInformationPO, systemInformation, publisher, receiver);
        return systemInformation;
    }

    private void inflateSystemInformation(InformationPO informationPO, SystemInformationPO systemInformationPO, SystemInformation systemInformation, Publisher publisher, Receiver receiver) {
        systemInformation.setProcessTime(systemInformation.getProcessTime());
        systemInformation.setResponseTitle(systemInformationPO.getResponseTitle());
        systemInformation.setPublisher(publisher);
        systemInformation.setReceiver(receiver);
        systemInformation.setContent(informationPO.getContent());
        systemInformation.setContentPics(informationPO.getContentPics());
        systemInformation.setCreateTime(informationPO.getCreateTime());
        systemInformation.setStatus(informationPO.getStatus());
    }

    private void inflateCommnetReply(InformationPO informationPO, CommentReplyPO commentReplyPO, CommentReply commentReply, Commodity commodity, Publisher publisher, Receiver receiver, Store store) {
        commentReply.setCommodity(commodity);
        commentReply.setStore(store);
        commentReply.setTargetUrl(commentReplyPO.getTargetUrl());
        commentReply.setPublisher(publisher);
        commentReply.setReceiver(receiver);
        commentReply.setContent(informationPO.getContent());
        commentReply.setContentPics(commentReplyPO.getCommodityPicSmall());
        commentReply.setCreateTime(informationPO.getCreateTime());
        commentReply.setStatus(informationPO.getStatus());
    }
}
