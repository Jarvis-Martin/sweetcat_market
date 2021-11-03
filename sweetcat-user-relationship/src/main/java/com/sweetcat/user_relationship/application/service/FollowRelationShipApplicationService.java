package com.sweetcat.user_relationship.application.service;

import com.sweetcat.user_relationship.domain.follow_relationship.entity.FollowRelationShip;
import com.sweetcat.user_relationship.domain.follow_relationship.repository.FollowRelationShipRepository;
import com.sweetcat.user_relationship.infrastructure.service.id_format_verfiy_service.VerifyIdFormatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: Coder_Jarvis
 * @Description:
 * @Date: 2021-11-2021/11/3-15:57
 * @Version: 1.0
 */
@Service
public class FollowRelationShipApplicationService {

    /**
     * 默认 粉丝列表 limit
     */
    @Value("${default-fans-limit}")
    private Integer defaultFansLimit;
    private FollowRelationShipRepository followRelationShipRepository;
    private VerifyIdFormatService verifyIdFormatService;

    @Autowired
    public void setFollowRelationShipRepository(FollowRelationShipRepository followRelationShipRepository) {
        this.followRelationShipRepository = followRelationShipRepository;
    }

    @Autowired
    public void setVerifyIdFormatService(VerifyIdFormatService verifyIdFormatService) {
        this.verifyIdFormatService = verifyIdFormatService;
    }

    /**
     * 获得 互关关系列表：粉丝列表（分页数据）
     *
     * @param userId userId
     * @param page   page
     * @param limit  limit
     * @return 互关关系列表：粉丝列表、关注列表（分页数据）
     */
    public List<FollowRelationShip> getFansPage(Long userId, Integer page, Integer limit) {
        // 检查 userId
        verifyIdFormatService.verifyId(userId);
        // page limit 参数检查 + 调整
        page = page < 0 ? 0 : page * limit;
        limit = limit < 0 ? this.defaultFansLimit : limit;
        // 返回分页
        List<FollowRelationShip> fansPage = followRelationShipRepository.getFansPage(userId, page, limit);
        return fansPage;
    }

    /**
     * 获得 互关关系列表：关注列表（分页数据）
     *
     * @param userId userId
     * @param page   page
     * @param limit  limit
     * @return 互关关系列表：粉丝列表、关注列表（分页数据）
     */
    public List<FollowRelationShip> getSubscriberPage(Long userId, Integer page, Integer limit) {
        // 检查 userId
        verifyIdFormatService.verifyId(userId);
        // page limit 参数检查 + 调整
        page = page < 0 ? 0 : page * limit;
        limit = limit < 0 ? this.defaultFansLimit : limit;
        return followRelationShipRepository.getSubscriberPage(userId, page, limit);
    }
}
