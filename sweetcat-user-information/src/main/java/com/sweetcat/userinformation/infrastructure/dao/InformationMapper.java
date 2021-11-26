package com.sweetcat.userinformation.infrastructure.dao;

import com.sweetcat.userinformation.domain.information.entity.Information;
import com.sweetcat.userinformation.infrastructure.po.InformationPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface InformationMapper {

    /**
     * 根据 receiverId 查分页数据
     * @param receiverId
     * @return
     */
    List<InformationPO> findPageByReceiverId(@Param("receiverId") Long receiverId, @Param("page") Integer page, @Param("limit") Integer limit);

    /**
     * 根据 informationId 查找 information
     * @param informationId
     */
    InformationPO findOneByInformationId(Long informationId);

    /**
     * 保存 informatio 的修改
     * @param information
     */
    void updateOne(Information information);


    /**
     * 移除
     * @param information
     */
    void deleteOne(Information information);

    /**
     * 添加
     * @param information
     * @param <T>
     */
    void addOne(Information information);
}