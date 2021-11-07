package com.sweetcat.storeinfo.application.service;

import com.sweetcat.commons.ResponseStatusEnum;
import com.sweetcat.commons.exception.ParameterFormatIllegalityException;
import com.sweetcat.storeinfo.domain.storeaddress.entity.StoreAddress;
import com.sweetcat.storeinfo.domain.storeaddress.repository.StoreAddressRepository;
import com.sweetcat.storeinfo.infrastructure.service.id_format_verfiy_service.VerifyIdFormatService;
import com.sweetcat.storeinfo.infrastructure.service.timestamp_format_verfiy_service.VerifyTimeStampFormatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * @Author: Coder_Jarvis
 * @Description:
 * @Date: 2021-11-2021/11/7-17:11
 * @Version: 1.0
 */
@Service
public class StoreAddressApplicationService {
    private StoreAddressRepository storeAddressRepository;
    private VerifyIdFormatService verifyIdFormatService;
    private VerifyTimeStampFormatService verifyTimeStampFormatService;

    @Autowired
    public void setVerifyTimeStampFormatService(VerifyTimeStampFormatService verifyTimeStampFormatService) {
        this.verifyTimeStampFormatService = verifyTimeStampFormatService;
    }

    @Autowired
    public void setVerifyIdFormatService(VerifyIdFormatService verifyIdFormatService) {
        this.verifyIdFormatService = verifyIdFormatService;
    }

    @Autowired
    public void setStoreAddressRepository(StoreAddressRepository storeAddressRepository) {
        this.storeAddressRepository = storeAddressRepository;
    }

    public StoreAddress getOneById(Long storeId) {
        // 检查 storeId 格式
        verifyIdFormatService.verifyIds(storeId);
        return storeAddressRepository.find(storeId);
    }

    public void addOne(Long storeId, String provinceName, String cityName, String areaName, String townName, String detailAddress,
                       Long createTime) {
        // 检查 店铺地址
        verifyString(provinceName, cityName, areaName, townName, detailAddress, detailAddress);
        // 检查创建时间
        verifyTimeStampFormatService.verifyTimeStamps(createTime);
        // 构造店铺地址
        StoreAddress storeAddress = new StoreAddress(storeId, provinceName, cityName, areaName, townName, detailAddress, createTime, createTime);
        // 添加
        storeAddressRepository.addOne(storeAddress);
    }

    private void verifyString(String... strs) {
        Arrays.stream(strs).forEach(str -> {
            if (str == null || str.length() < 0) {
                throw new ParameterFormatIllegalityException(
                        ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorCode(),
                        ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorMessage()
                );
            }
        });
    }
}
