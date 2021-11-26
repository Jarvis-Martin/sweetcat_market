package com.sweetcat.userinformation.domain.information.repository;

import com.sweetcat.userinformation.domain.information.entity.Information;

import java.util.List;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/25-18:47
 * @version: 1.0
 */
public interface InformationRepository {
    /**
     * 根据 receiverId 查分页数据
     * @param receiverId
     * @param page
     * @param limit
     * @return
     */
     <T extends Information> List<T> findPageByReceiverId(Long receiverId, Integer page, Integer limit);

    /**
     * 根据 informationId 查找 information
     * @param informationId
     * @param <T>
     */
    <T extends Information> T findOneByInformationId(Long informationId);

    /**
     * 保存 informatio 的修改
     * @param information
     * @param <T>
     */
    <T extends Information> void save(T information);

    /**
     * 移除
     * @param information
     * @param <T>
     */
    <T extends Information> void removeOne(T information);

    /**
     * 添加
     * @param systemInformation
     * @param <T>
     */
    <T extends Information> void addOne(T systemInformation);
}
