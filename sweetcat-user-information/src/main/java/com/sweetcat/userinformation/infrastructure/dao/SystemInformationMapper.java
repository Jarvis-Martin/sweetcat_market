package com.sweetcat.userinformation.infrastructure.dao;

import com.sweetcat.userinformation.domain.information.entity.SystemInformation;
import com.sweetcat.userinformation.infrastructure.po.SystemInformationPO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SystemInformationMapper {
    /**
     * 根据 informationId 查找 commentReply
     * @param informationId
     * @return
     */
    SystemInformationPO findOneByInformationId(Long informationId);

    /**
     * 根据 commentReply
     * @param systemInformation
     */
    void updateOne(SystemInformation systemInformation);

    /**
     *
     * @param systemInformation
     */
    void deleteOne(SystemInformation systemInformation);

    /**
     * 添加
     * @param systemInformation
     */
    void addOne(SystemInformation systemInformation);
}