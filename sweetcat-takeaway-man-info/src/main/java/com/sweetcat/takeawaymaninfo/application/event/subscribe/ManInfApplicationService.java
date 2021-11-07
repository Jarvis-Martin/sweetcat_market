package com.sweetcat.takeawaymaninfo.application.event.subscribe;

import com.sweetcat.commons.ResponseStatusEnum;
import com.sweetcat.commons.exception.ParameterFormatIllegalityException;
import com.sweetcat.takeawaymaninfo.domain.maninfo.entity.ManInfo;
import com.sweetcat.takeawaymaninfo.domain.maninfo.repository.ManInfoRepository;
import com.sweetcat.takeawaymaninfo.infrastructure.service.id_format_verfiy_service.VerifyIdFormatService;
import com.sweetcat.takeawaymaninfo.infrastructure.service.phone_format_verfiy_service.VerifyPhoneFormatService;
import com.sweetcat.takeawaymaninfo.infrastructure.service.snowflake_service.SnowFlakeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

/**
 * @Author: Coder_Jarvis
 * @Description:
 * @Date: 2021-11-2021/11/7-14:15
 * @Version: 1.0
 */
@Service
public class ManInfApplicationService {
    private VerifyIdFormatService verifyIdFormatService;
    private SnowFlakeService snowFlakeService;
    private VerifyPhoneFormatService verifyPhoneFormatService;

    private ManInfoRepository manInfoRepository;

    @Autowired
    public void setVerifyPhoneFormatService(VerifyPhoneFormatService verifyPhoneFormatService) {
        this.verifyPhoneFormatService = verifyPhoneFormatService;
    }

    @Autowired
    public void setSnowFlakeService(SnowFlakeService snowFlakeService) {
        this.snowFlakeService = snowFlakeService;
    }

    @Autowired
    public void setVerifyIdFormatService(VerifyIdFormatService verifyIdFormatService) {
        this.verifyIdFormatService = verifyIdFormatService;
    }

    @Autowired
    public void setManInfoRepository(ManInfoRepository manInfoRepository) {
        this.manInfoRepository = manInfoRepository;
    }

    /**
     * 添加
     *
     * @param name name
     * @param phone phone
     * @param createTime createTime
     */
    public void addOne(String name, String phone, Long createTime) {
        // 检查手机号
        verifyPhoneFormatService.verifyPhoneFormat(phone);
        // 检查用户名
        if (name == null || name.length() <= 0) {
            throw new ParameterFormatIllegalityException(
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorCode(),
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorMessage()
            );
        }
        // 检查 createTime
        createTime = createTime == null || createTime < 0 ? Instant.now().toEpochMilli() : createTime;
        long manId = snowFlakeService.snowflakeId();
        ManInfo manInfo = new ManInfo(manId, name, phone, createTime);
        manInfoRepository.addOne(manInfo);
    }

    /**
     * find mainInfo by mainId
     *
     * @param manId manId
     * @return
     */
    public ManInfo getOne(Long manId) {
        verifyIdFormatService.verifyId(manId);
        return manInfoRepository.find(manId);
    }
}
