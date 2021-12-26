package com.sweetcat.geography.application.service;

import com.sweetcat.commons.ResponseStatusEnum;
import com.sweetcat.commons.exception.ParameterFormatIllegalityException;
import com.sweetcat.commons.exception.RecodeExistedException;
import com.sweetcat.geography.application.command.AddGeographyCommand;
import com.sweetcat.geography.domain.geography.entity.Geography;
import com.sweetcat.geography.domain.geography.repository.GeographyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/10-14:21
 * @version: 1.0
 */
@Service
public class GeographyApplicationService {
    private GeographyRepository geographyRepository;

    @Autowired
    public void setGeographyRepository(GeographyRepository geographyRepository) {
        this.geographyRepository = geographyRepository;
    }

    /**
     * 添加一条geography
     * @param geographyCommand geographyCommand
     */
    @Transactional
    public void addOne(AddGeographyCommand geographyCommand) {
        String addressCode = geographyCommand.getAddressCode();
        // 检查 地址代码格式
        verifyAddressCode(addressCode);
        // 检查是否已存在
        Geography geography = geographyRepository.find(addressCode);
        // 已存在，直接通知用户
        if (geography != null) {
            throw new RecodeExistedException(
                    ResponseStatusEnum.RECORDEEXISTED.getErrorCode(),
                    ResponseStatusEnum.RECORDEEXISTED.getErrorMessage()
            );
        }
        // 构建 geography
        geography = new Geography();
        inflateGeography(geographyCommand, geography);
        geographyRepository.addOne(geography);
    }

    private void inflateGeography(AddGeographyCommand geographyCommand, Geography geography) {
        geography.setAddressCode(geographyCommand.getAddressCode());
        geography.setAddressName(geographyCommand.getAddressName());
        geography.setProvinceCode(geographyCommand.getProvinceCode());
        geography.setCityCode(geographyCommand.getCityCode());
        geography.setAreaCode(geographyCommand.getAreaCode());
        geography.setTownCode(geographyCommand.getTownCode());
        geography.setCreateTime(geographyCommand.getCreateTime());
        geography.setUpdateTime(geographyCommand.getCreateTime());
    }

    /**
     * 根据 addressCode 查找一个 geography
     * @param addressCode addressCode
     * @return
     */
    @Transactional
    public Geography find(String addressCode) {
        // 检查 地址代码格式
        verifyAddressCode(addressCode);
        // 查找并返回
        return geographyRepository.find(addressCode);
    }

    /**
     * 移除一条geography
     * @param geography geography
     */
    @Transactional
    public void remove(Geography geography) {
        // 检查 addressCode
        verifyAddressCode(geography.getAddressCode());
        // 移除 geography
        geographyRepository.remove(geography);
    }

    private void verifyAddressCode(String addressCode) {
        if (addressCode == null) {
            throw new ParameterFormatIllegalityException(
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorCode(),
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorMessage()
            );
        }
    }
}
