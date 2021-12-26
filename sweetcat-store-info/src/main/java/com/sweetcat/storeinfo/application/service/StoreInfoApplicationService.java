package com.sweetcat.storeinfo.application.service;

import com.sweetcat.storeinfo.domain.storeinfo.entity.StoreInfo;
import com.sweetcat.storeinfo.domain.storeinfo.repository.StoreInfoRepository;
import com.sweetcat.storeinfo.infrastructure.service.id_format_verfiy_service.VerifyIdFormatService;
import com.sweetcat.storeinfo.infrastructure.service.phone_format_verfiy_service.VerifyPhoneFormatService;
import com.sweetcat.storeinfo.infrastructure.service.snowflake_service.SnowFlakeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: Coder_Jarvis
 * @Description:
 * @Date: 2021-11-2021/11/7-17:11
 * @Version: 1.0
 */
@Service
public class StoreInfoApplicationService {
    private VerifyIdFormatService verifyIdFormatService;
    private VerifyPhoneFormatService verifyPhoneFormatService;
    private StoreInfoRepository storeInfoRepository;
    private SnowFlakeService snowFlakeService;

    @Autowired
    public void setVerifyPhoneFormatService(VerifyPhoneFormatService verifyPhoneFormatService) {
        this.verifyPhoneFormatService = verifyPhoneFormatService;
    }

    @Autowired
    public void setSnowFlakeService(SnowFlakeService snowFlakeService) {
        this.snowFlakeService = snowFlakeService;
    }

    @Autowired
    public void setStoreInfoRepository(StoreInfoRepository storeInfoRepository) {
        this.storeInfoRepository = storeInfoRepository;
    }

    @Autowired
    public void setVerifyIdFormatService(VerifyIdFormatService verifyIdFormatService) {
        this.verifyIdFormatService = verifyIdFormatService;
    }

    /**
     * get store info by storeId
     *
     * @param storeId
     * @return
     */
    @Transactional
    public StoreInfo getOneById(Long storeId) {
        verifyIdFormatService.verifyIds(storeId);
        return storeInfoRepository.find(storeId);
    }

    /**
     * 添加 store
     * @param storeName
     * @param principalName
     * @param principalPhone
     * @param mainBusiness
     * @param type
     * @param createTime
     */
    @Transactional
    public void addOne(String storeName, String principalName, String principalPhone, String mainBusiness, Integer type, Long createTime) {
        // 检查手机号格式
        verifyPhoneFormatService.verifyPhoneFormat(principalPhone);
        // 构建 storeInfo
        long storeId = snowFlakeService.snowflakeId();
        StoreInfo storeInfo = new StoreInfo(storeId);
        inflateStoreInfo(storeName, principalName, principalPhone, mainBusiness, type, createTime, storeInfo);
        storeInfoRepository.addOne(storeInfo);
    }

    /**
     * 查询 storeId 是否存在
     * @param storeId storeId
     * @return storeId 是否存在
     */
    @Transactional
    public Boolean storeIsExisted(Long storeId) {
        // 检查 storeId 格式
        verifyIdFormatService.verifyIds(storeId);
        // 查询 storeId 是否以及存在
        return storeInfoRepository.storeIsExisted(storeId);
    }

    private void inflateStoreInfo(String storeName, String principalName, String principalPhone, String mainBusiness, Integer type, Long createTime, StoreInfo storeInfo) {
        storeInfo.setStoreName(storeName);
        storeInfo.setPrincipalName(principalName);
        storeInfo.setPrincipalPhone(principalPhone);
        storeInfo.setMainBusiness(mainBusiness);
        storeInfo.setType(type);
        storeInfo.setCreateTime(createTime);
        storeInfo.setUpdateTime(createTime);
    }
}
