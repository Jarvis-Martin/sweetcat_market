package com.sweetcat.userinformation.application.service;

import com.sweetcat.api.rpcdto.commodityinfo.CommodityInfoRpcDTO;
import com.sweetcat.api.rpcdto.customerservice.CustomerServiceStaffInfoRpcDTO;
import com.sweetcat.api.rpcdto.storeinfo.StoreInfoRpcDTO;
import com.sweetcat.api.rpcdto.userinfo.UserInfoRpcDTO;
import com.sweetcat.commons.ResponseStatusEnum;
import com.sweetcat.commons.exception.CustomerServiceStaffNotExistedException;
import com.sweetcat.commons.exception.UserNotExistedException;
import com.sweetcat.userinformation.application.command.AddCommentReplyCommand;
import com.sweetcat.userinformation.application.command.AddSystemInformationCommand;
import com.sweetcat.userinformation.application.rpc.CommodityInfoRpc;
import com.sweetcat.userinformation.application.rpc.CustomerServiceInforRpc;
import com.sweetcat.userinformation.application.rpc.StoreInfoRpc;
import com.sweetcat.userinformation.application.rpc.UserInfoRpc;
import com.sweetcat.userinformation.domain.information.entity.CommentReply;
import com.sweetcat.userinformation.domain.information.entity.Information;
import com.sweetcat.userinformation.domain.information.entity.SystemInformation;
import com.sweetcat.userinformation.domain.information.repository.InformationRepository;
import com.sweetcat.userinformation.domain.information.vo.Commodity;
import com.sweetcat.userinformation.domain.information.vo.Publisher;
import com.sweetcat.userinformation.domain.information.vo.Receiver;
import com.sweetcat.userinformation.domain.information.vo.Store;
import com.sweetcat.userinformation.infrastructure.service.id_format_verfiy_service.VerifyIdFormatService;
import org.apache.shardingsphere.transaction.annotation.ShardingTransactionType;
import org.apache.shardingsphere.transaction.core.TransactionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/25-21:39
 * @version: 1.0
 */
@Service
public class InformationApplicationService {
    private InformationRepository informationRepository;
    private VerifyIdFormatService verifyIdFormatService;
    private UserInfoRpc userInfoRpc;
    private CommodityInfoRpc commodityInfoRpc;
    private CustomerServiceInforRpc customerServiceInforRpc;
    private StoreInfoRpc storeInfoRpc;

    @Autowired
    public void setStoreInfoRpc(StoreInfoRpc storeInfoRpc) {
        this.storeInfoRpc = storeInfoRpc;
    }

    @Autowired
    public void setCustomerServiceInforRpc(CustomerServiceInforRpc customerServiceInforRpc) {
        this.customerServiceInforRpc = customerServiceInforRpc;
    }

    @Autowired
    public void setUserInfoRpc(UserInfoRpc userInfoRpc) {
        this.userInfoRpc = userInfoRpc;
    }

    @Autowired
    public void setCommodityInfoRpc(CommodityInfoRpc commodityInfoRpc) {
        this.commodityInfoRpc = commodityInfoRpc;
    }

    @Autowired
    public void setVerifyIdFormatService(VerifyIdFormatService verifyIdFormatService) {
        this.verifyIdFormatService = verifyIdFormatService;
    }

    @Autowired
    public void setInformationRepository(InformationRepository informationRepository) {
        this.informationRepository = informationRepository;
    }

    @Transactional
    @ShardingTransactionType(TransactionType.BASE)
    public void addOneSystemInformation(AddSystemInformationCommand command) {
        Long informationId = command.getInformationId();
        Long publisherId = command.getPublisherId();
        Long receiverId = command.getReceiverId();
        // ?????? id
        verifyIdFormatService.verifyIds(informationId, publisherId, receiverId);
        CustomerServiceStaffInfoRpcDTO staff = customerServiceInforRpc.findByStaffId(publisherId);
        // ?????? ????????????
        checkCustomerServiceStaff(staff);
        // ??????????????????
        SystemInformation systemInformation = new SystemInformation(informationId);
        //      -- ?????? SystemInformation.publisher
        Publisher publisher = new Publisher(command.getPublisherId());
        publisher.setPublisherName(staff.getStaffNickname());
        publisher.setPublisherAvatar(staff.getStaffAvatar());
        //      -- ?????? SystemInformation.receiver
        Receiver receiver = new Receiver(command.getReceiverId());
        //      -- ?????? systemInformation
        inflateSystemInformation(command, systemInformation, publisher, receiver);
        // ??????
        informationRepository.addOne(systemInformation);
    }

    @Transactional
    @ShardingTransactionType(TransactionType.BASE)
    public void addOneCommentReply(AddCommentReplyCommand command) {
        Long commodityId = command.getCommodityId();
        Long informationId = command.getInformationId();
        Long publisherId = command.getPublisherId();
        Long receiverId = command.getReceiverId();
        Long storeId = command.getStoreId();
        // ??????id
        verifyIdFormatService.verifyIds(commodityId, informationId, publisherId, receiverId, storeId);
        CommodityInfoRpcDTO commodityInfo = commodityInfoRpc.findByCommodityId(commodityId);
        // ????????????
        checkCommodity(commodityInfo);
        UserInfoRpcDTO publisherInfo = userInfoRpc.getUserInfo(publisherId);
        UserInfoRpcDTO receiverInfo = userInfoRpc.getUserInfo(receiverId);
        // ?????? ?????????????????????
        checkUser(publisherInfo, receiverInfo);
        CommentReply commentReply = new CommentReply(informationId);
        // ??????????????????
        StoreInfoRpcDTO storeInfoRpcDTO = storeInfoRpc.findOneByStoreId(storeId);
        // CommentReply.Store
        Store store = new Store(storeId);
        store.setStoreName(storeInfoRpcDTO.getStoreName());
        // CommentReply.Publisher
        Publisher publisher = new Publisher(command.getPublisherId());
        publisher.setPublisherName(publisherInfo.getNickname());
        publisher.setPublisherAvatar(publisherInfo.getAvatarPath());
        // CommentReply.Receiver
        Receiver receiver = new Receiver(receiverId);
        // CommentReply.Commodity
        Commodity commodity = new Commodity(commodityId);
        commodity.setCommodityName(commodityInfo.getCommodityName());
        commodity.setPicSmall(commodityInfo.getPicSmall());
        // ?????? CommentReply
        inflateCommentReply(command, informationId, commentReply, store, publisher, receiver, commodity);
        // ??????
        informationRepository.addOne(commentReply);
    }

    private void inflateCommentReply(AddCommentReplyCommand command, Long informationId, CommentReply commentReply, Store store, Publisher publisher, Receiver receiver, Commodity commodity) {
        commentReply.setInformationId(informationId);
        commentReply.setCommodity(commodity);
        commentReply.setStore(store);
        commentReply.setTargetUrl(command.getTargetUrl());
        commentReply.setPublisher(publisher);
        commentReply.setReceiver(receiver);
        commentReply.setContent(command.getContent());
        commentReply.setContentPics(command.getContentPics());
        commentReply.setCreateTime(command.getCreateTime());
        commentReply.setStatus(command.getStatus());
        commentReply.setType(Information.TYPE_COMMENTREPLY);
    }

    private void checkCommodity(CommodityInfoRpcDTO ...commodityInfo) {
        if (commodityInfo == null || commodityInfo.length <= 0) {
            throw new UserNotExistedException(
                    ResponseStatusEnum.USERNOTEXISTED.getErrorCode(),
                    ResponseStatusEnum.USERNOTEXISTED.getErrorMessage()
            );
        }
        Arrays.stream(commodityInfo).forEach(
                commodityInfoRpcDTO -> {
                    if (commodityInfo == null) {
                        throw new UserNotExistedException(
                                ResponseStatusEnum.USERNOTEXISTED.getErrorCode(),
                                ResponseStatusEnum.USERNOTEXISTED.getErrorMessage()
                        );
                    }
                }
        );
    }

    private void inflateSystemInformation(AddSystemInformationCommand command, SystemInformation systemInformation, Publisher publisher, Receiver receiver) {
        systemInformation.setProcessTime(command.getProcessTime());
        systemInformation.setResponseTitle(command.getResponseTitle());
        systemInformation.setPublisher(publisher);
        systemInformation.setReceiver(receiver);
        systemInformation.setContent(command.getContent());
        systemInformation.setContentPics(command.getContentPics());
        systemInformation.setCreateTime(command.getCreateTime());
        systemInformation.setStatus(Information.STATUS_UNREAD);
        systemInformation.setType(Information.TYPE_SYSTEMINFORMATION);
    }

    private void checkCustomerServiceStaff(CustomerServiceStaffInfoRpcDTO staff) {
        if (staff == null) {
            throw new CustomerServiceStaffNotExistedException(
                    ResponseStatusEnum.CustomerServiceStaffNOTEXISTED.getErrorCode(),
                    ResponseStatusEnum.CustomerServiceStaffNOTEXISTED.getErrorMessage()
            );
        }
    }

    /**
     * ?????? receiverId ???????????????
     *
     * @param receiverId
     * @param page
     * @param limit
     * @return
     */
    @Transactional
    public <T extends Information> List<T> findPageByReceiverId(Long receiverId, Integer page, Integer limit) {
        // ?????? id
        verifyIdFormatService.verifyIds(receiverId);
        // ?????? user
        UserInfoRpcDTO userInfo = userInfoRpc.getUserInfo(receiverId);
        // ?????? user
        checkUser(userInfo);
        // ?????? page limit
        limit = limit == null || limit < 0 ? 15 : limit;
        page = page == null || page < 0 ? 0 : page * limit;
        // ??????
        return informationRepository.findPageByReceiverId(receiverId, page, limit);
    }

    private void checkUser(UserInfoRpcDTO... userInfos) {
        if (userInfos == null || userInfos.length <= 0) {
            throw new UserNotExistedException(
                    ResponseStatusEnum.USERNOTEXISTED.getErrorCode(),
                    ResponseStatusEnum.USERNOTEXISTED.getErrorMessage()
            );
        }
        Arrays.stream(userInfos).forEach(
                userInfoRpcDTO -> {
                    if (userInfoRpcDTO == null) {
                        throw new UserNotExistedException(
                                ResponseStatusEnum.USERNOTEXISTED.getErrorCode(),
                                ResponseStatusEnum.USERNOTEXISTED.getErrorMessage()
                        );
                    }
                }
        );
    }

    /**
     * ?????? informationId ?????? information
     *
     * @param informationId
     * @param <T>
     */
    @Transactional
    public <T extends Information> T findOneByInformationId(Long informationId) {
        // ??????id
        verifyIdFormatService.verifyIds(informationId);
        return informationRepository.findOneByInformationId(informationId);
    }

    /**
     * ??????
     *
     * @param informationId
     */
    @Transactional
    public void removeOne(Long informationId) {
        // ??????id
        verifyIdFormatService.verifyIds(informationId);
        // ?????? information
        Information information = informationRepository.findOneByInformationId(informationId);
        // ??????
        informationRepository.removeOne(information);
    }
}
