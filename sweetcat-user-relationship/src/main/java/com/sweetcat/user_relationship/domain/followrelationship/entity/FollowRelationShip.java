package com.sweetcat.user_relationship.domain.followrelationship.entity;

import com.sweetcat.user_relationship.domain.followrelationship.exception.UserPropertyIlleagalException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Instant;

/**
 * @Author: Coder_Jarvis
 * @Description:
 * @Date: 2021-11-2021/11/3-15:41
 * @Version: 1.0
 */
@AllArgsConstructor
public class FollowRelationShip {

    /**
     * 当前userid
     */
    private User user;


    /**
     * 被关注人信息
     */
    private User targetUser;

    /**
     * 是否以及关注过它: 0 未关注; 1 已关注
     */
    private Integer isLiked;

    /**
     * 关注时间
     */
    private Long createTime;

    public User getUser() {
        return user;
    }

    public User getTargetUser() {
        return targetUser;
    }

    public Integer getIsLiked() {
        return isLiked;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public FollowRelationShip(User user) {
        this.user = user;
        this.setCreateTime(Instant.now().toEpochMilli());
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setCreateTime(Long createTime) {
        if (createTime == null || createTime < 0) {
            throw new UserPropertyIlleagalException(
                    "创建事件格式错误。"
            );
        }
        this.createTime = createTime;
    }

    private void setTargetUser(User targetUser) {
        this.targetUser = targetUser;
    }

    /**
     * user with FollowRelationShip like user(parameter)
     *
     * @param user 被关注人
     */
    public void like(User user) {
        this.setTargetUser(user);
    }

    /**
     * 取消关注
     */
    public void disLike(User user) {
        // 不做任何事情，保留 被关注用户信息，以便remove方法可获取要被取关用户的userid
    }
}
