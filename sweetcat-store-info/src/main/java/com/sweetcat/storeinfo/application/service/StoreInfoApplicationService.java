package com.sweetcat.storeinfo.application.service;

import com.sweetcat.commons.ResponseStatusEnum;
import com.sweetcat.commons.exception.ParameterFormatIllegalityException;
import com.sweetcat.storeinfo.domain.storeinfo.entity.StoreInfo;
import com.sweetcat.storeinfo.domain.storeinfo.repository.StoreInfoRepository;
import com.sweetcat.storeinfo.infrastructure.service.id_format_verfiy_service.VerifyIdFormatService;
import com.sweetcat.storeinfo.infrastructure.service.phone_format_verfiy_service.VerifyPhoneFormatService;
import com.sweetcat.storeinfo.infrastructure.service.snowflake_service.SnowFlakeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Arrays;

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
    public StoreInfo getOneById(Long storeId) {
        verifyIdFormatService.verifyIds(storeId);
        return storeInfoRepository.find(storeId);
    }

    public void addOne(String storeName, String principalName, String principalPhone, String mainBusiness, Integer type, Long createTime) {
        // 检查 店铺名，开店人姓名， 主营业务mainBusiness可以为null
        verifyString(storeName, principalName);
        // 检查手机号格式
        verifyPhoneFormatService.verifyPhoneFormat(principalPhone);
        // 检查创建时间
        createTime = createTime == null || createTime < 0 ? Instant.now().toEpochMilli() : createTime;
        // 构建 storeInfo
        long storeId = snowFlakeService.snowflakeId();
        StoreInfo storeInfo = new StoreInfo(storeId);
        storeInfo.setStoreName(storeName);
        storeInfo.setPrincipalName(principalName);
        storeInfo.setPrincipalPhone(principalPhone);
        storeInfo.setMainBusiness(mainBusiness);
        storeInfo.setType(type);
        storeInfo.setCreateTime(createTime);
        storeInfo.setUpdateTime(createTime);
        storeInfoRepository.addOne(storeInfo);
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
