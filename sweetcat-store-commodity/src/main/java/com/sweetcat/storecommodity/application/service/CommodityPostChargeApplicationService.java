package com.sweetcat.storecommodity.application.service;

import com.sweetcat.api.rpcdto.geography.GeographyRpcDTO;
import com.sweetcat.api.rpcdto.storeinfo.StoreIsExistedRpcDTO;
import com.sweetcat.commons.ResponseStatusEnum;
import com.sweetcat.commons.exception.ParameterFormatIllegalityException;
import com.sweetcat.commons.exception.RecodeNotExistedException;
import com.sweetcat.storecommodity.application.command.AddCommodityPostChargeCommand;
import com.sweetcat.storecommodity.application.rpc.GeographyRpc;
import com.sweetcat.storecommodity.application.rpc.StoreInfoRpc;
import com.sweetcat.storecommodity.domain.commodityinfo.entity.Commodity;
import com.sweetcat.storecommodity.domain.commodityinfo.repository.CommodityInfoRepository;
import com.sweetcat.storecommodity.domain.commonditypostcharge.entity.CommodityPostCharge;
import com.sweetcat.storecommodity.domain.commonditypostcharge.repository.CommodityPostChargeRepository;
import com.sweetcat.storecommodity.infrastructure.service.id_format_verfiy_service.VerifyIdFormatService;
import com.sweetcat.storecommodity.infrastructure.service.snowflake_service.SnowFlakeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/10-13:30
 * @version: 1.0
 */
@Service
public class CommodityPostChargeApplicationService {
    private StoreInfoRpc storeInfoRpc;
    private GeographyRpc geographyRpc;
    private SnowFlakeService snowFlakeService;
    private VerifyIdFormatService verifyIdFormatService;
    private CommodityPostChargeRepository postChargeRepository;
    private CommodityInfoRepository commodityInfoRepository;

    @Autowired
    public void setSnowFlakeService(SnowFlakeService snowFlakeService) {
        this.snowFlakeService = snowFlakeService;
    }

    @Autowired
    public void setStoreInfoRpc(StoreInfoRpc storeInfoRpc) {
        this.storeInfoRpc = storeInfoRpc;
    }

    @Autowired
    public void setCommodityInfoRepository(CommodityInfoRepository commodityInfoRepository) {
        this.commodityInfoRepository = commodityInfoRepository;
    }

    @Autowired
    public void setGeographyRpc(GeographyRpc geographyRpc) {
        this.geographyRpc = geographyRpc;
    }

    @Autowired
    public void setPostChargeRepository(CommodityPostChargeRepository postChargeRepository) {
        this.postChargeRepository = postChargeRepository;
    }

    @Autowired
    public void setVerifyIdFormatService(VerifyIdFormatService verifyIdFormatService) {
        this.verifyIdFormatService = verifyIdFormatService;
    }

    /**
     * ??????????????????
     *
     * @param command command
     */
    @Transactional
    public void addOne(AddCommodityPostChargeCommand command) {
        long commodityId = command.getCommodityId();
        long storeId = command.getStoreId();
        String provinceCode = command.getProvinceCode();
        // ?????? ids
        verifyIdFormatService.verifyId(commodityId, storeId);
        // ??????????????????
        StoreIsExistedRpcDTO storeIsExisted = storeInfoRpc.storeIsExisted(storeId);
        // ???????????????
        if (storeIsExisted == null) {
            throw new RecodeNotExistedException(
                    ResponseStatusEnum.RECORDENOTEXISTED.getErrorCode(),
                    ResponseStatusEnum.RECORDENOTEXISTED.getErrorMessage()
            );
        }
        // ??????????????????
        Commodity commodity = commodityInfoRepository.findByCommodityId(commodityId);
        // ???????????????
        if (commodity == null) {
            throw new RecodeNotExistedException(
                    ResponseStatusEnum.RECORDENOTEXISTED.getErrorCode(),
                    ResponseStatusEnum.RECORDENOTEXISTED.getErrorMessage()
            );
        }
        // ??????????????????
        GeographyRpcDTO geographyInfo = geographyRpc.getGeographyInfo(provinceCode);
        // ???????????????
        if (geographyInfo == null) {
            throw new RecodeNotExistedException(
                    ResponseStatusEnum.RECORDENOTEXISTED.getErrorCode(),
                    ResponseStatusEnum.RECORDENOTEXISTED.getErrorMessage()
            );
        }
        // ?????? charge id
        long chargeId = snowFlakeService.snowflakeId();
        // ??????
        CommodityPostCharge commodityPostCharge = new CommodityPostCharge(chargeId, commodityId, storeId, provinceCode);
        commodityPostCharge.setPostCharge(command.getPostCharge());
        commodityPostCharge.setCreateTime(command.getCreateTime());
        commodityPostCharge.setUpdateTime(command.getCreateTime());
        // ?????? db
        postChargeRepository.addOne(commodityPostCharge);
    }

    /**
     * find post charge recorde by commodityId and provinceCode
     *
     * @param commodityId commodityId
     * @param addressCode addressCode
     * @return
     */
    @Transactional
    public CommodityPostCharge find(Long commodityId, String addressCode) {
        // ??????id ??????
        verifyIdFormatService.verifyId(commodityId);
        // ?????? commodityid ????????????????????????
        Commodity commodity = commodityInfoRepository.findByCommodityId(commodityId);
        // ???????????????
        if (commodity == null) {
            throw new ParameterFormatIllegalityException(
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorCode(),
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorMessage()
            );
        }
        // ?????? addressCode ?????????
        GeographyRpcDTO geographyInfo = geographyRpc.getGeographyInfo(addressCode);
        // geography ?????????
        if (geographyInfo == null) {
            throw new ParameterFormatIllegalityException(
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorCode(),
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorMessage()
            );
        }
        return postChargeRepository.find(commodityId, addressCode);
    }

    /**
     * remove post charge recorde by commodity id and province code
     *
     * @param chargeId chargeId
     */
    @Transactional
    public void remove(Long chargeId) {
        // ?????? id
        verifyIdFormatService.verifyId(chargeId);
        // ?????? ????????????
        CommodityPostCharge postCharge = postChargeRepository.findByPostChargeId(chargeId);
        // ??????
        postChargeRepository.remove(postCharge);
    }
}
