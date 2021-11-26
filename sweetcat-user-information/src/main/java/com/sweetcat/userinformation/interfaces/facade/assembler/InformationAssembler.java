package com.sweetcat.userinformation.interfaces.facade.assembler;

import com.sweetcat.userinformation.domain.information.entity.CommentReply;
import com.sweetcat.userinformation.domain.information.entity.Information;
import com.sweetcat.userinformation.domain.information.entity.SystemInformation;
import com.sweetcat.userinformation.domain.information.vo.Commodity;
import com.sweetcat.userinformation.domain.information.vo.Publisher;
import com.sweetcat.userinformation.domain.information.vo.Store;
import com.sweetcat.userinformation.interfaces.facade.restdto.*;
import org.springframework.stereotype.Component;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/25-22:04
 * @version: 1.0
 */
@Component
public class InformationAssembler {

    public <DTO extends InformationRestDTO, T extends Information> DTO converterToRestDTO(T information) {
        if (Information.TYPE_COMMENTREPLY.equals(information.getType())) {
            CommentReply commentReply = (CommentReply) information;

            CommentReplyRestDTO commentReplyRestDTO = new CommentReplyRestDTO();
            // 构建 CommentReplyRestDTO.StoreRestDTO
            Store store = commentReply.getStore();
            StoreRestDTO storeRestDTO = new StoreRestDTO(store.getStoreId());
            storeRestDTO.setStoreName(store.getStoreName());

            // 构建 CommentReplyRestDTO.CommodityRestDTO
            Commodity commodity = commentReply.getCommodity();
            CommodityRestDTO commodityRestDTO = new CommodityRestDTO(commodity.getCommodityId());
            commodityRestDTO.setCommodityName(commodity.getCommodityName());
            commodityRestDTO.setPicSmall(commodity.getPicSmall());

            // 构建 CommentReplyRestDTO.PublisherRestDTO
            Publisher publisher = commentReply.getPublisher();
            PublisherRestDTO publisherRestDTO = new PublisherRestDTO(publisher.getPublisherId());
            publisherRestDTO.setPublisherName(publisher.getPublisherName());
            publisherRestDTO.setPublisherAvatar(publisher.getPublisherAvatar());

            // 构建 CommentReplyRestDTO.ReceiverRestDTO
            ReceiverRestDTO receiverRestDTO = new ReceiverRestDTO(commentReply.getReceiver().getReceiverId());
            inflateCommentReplyRestDTO(commentReply, commentReplyRestDTO, storeRestDTO, commodityRestDTO, publisherRestDTO, receiverRestDTO);
            return (DTO) commentReplyRestDTO;
        }
        if (Information.TYPE_SYSTEMINFORMATION.equals(information.getType())) {
            SystemInformation systemInformation = (SystemInformation) information;

            SystemInformationRestDTO systemInformationRestDTO = new SystemInformationRestDTO();

            // 构建 SystemInformationRestDTO.PublisherRestDTO
            Publisher publisher = systemInformation.getPublisher();
            PublisherRestDTO publisherRestDTO = new PublisherRestDTO(publisher.getPublisherId());
            publisherRestDTO.setPublisherName(publisher.getPublisherName());
            publisherRestDTO.setPublisherAvatar(publisher.getPublisherAvatar());

            // 构建 SystemInformationRestDTO.ReceiverRestDTO
            ReceiverRestDTO receiverRestDTO = new ReceiverRestDTO(systemInformation.getReceiver().getReceiverId());

            inflateSystemInformationRestDTO(systemInformation, systemInformationRestDTO, publisherRestDTO, receiverRestDTO);
            return (DTO) systemInformationRestDTO;
        }
        return null;
    }

    private void inflateCommentReplyRestDTO(CommentReply commentReply, CommentReplyRestDTO commentReplyRestDTO, StoreRestDTO storeRestDTO, CommodityRestDTO commodityRestDTO, PublisherRestDTO publisherRestDTO, ReceiverRestDTO receiverRestDTO) {
        commentReplyRestDTO.setCommodity(commodityRestDTO);
        commentReplyRestDTO.setStore(storeRestDTO);
        commentReplyRestDTO.setTargetUrl(commentReply.getTargetUrl());
        commentReplyRestDTO.setInformationId(commentReply.getInformationId());
        commentReplyRestDTO.setPublisher(publisherRestDTO);
        commentReplyRestDTO.setReceiver(receiverRestDTO);
        commentReplyRestDTO.setContent(commentReply.getContent());
        commentReplyRestDTO.setContentPics(commentReply.getContentPics());
        commentReplyRestDTO.setCreateTime(commentReply.getCreateTime());
        commentReplyRestDTO.setStatus(commentReply.getStatus());
        commentReplyRestDTO.setType(commentReply.getType());
    }

    private void inflateSystemInformationRestDTO(SystemInformation systemInformation, SystemInformationRestDTO systemInformationRestDTO, PublisherRestDTO publisherRestDTO, ReceiverRestDTO receiverRestDTO) {
        systemInformationRestDTO.setPublisher(publisherRestDTO);
        systemInformationRestDTO.setProcessTime(systemInformation.getProcessTime());
        systemInformationRestDTO.setResponseTitle(systemInformation.getResponseTitle());
        systemInformationRestDTO.setInformationId(systemInformation.getInformationId());
        systemInformationRestDTO.setReceiver(receiverRestDTO);
        systemInformationRestDTO.setContent(systemInformation.getContent());
        systemInformationRestDTO.setContentPics(systemInformation.getContentPics());
        systemInformationRestDTO.setCreateTime(systemInformation.getCreateTime());
        systemInformationRestDTO.setStatus(systemInformation.getStatus());
        systemInformationRestDTO.setType(systemInformation.getType());
    }


}
