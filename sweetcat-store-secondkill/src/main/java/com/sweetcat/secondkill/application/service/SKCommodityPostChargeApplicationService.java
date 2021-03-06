package com.sweetcat.secondkill.application.service;

import com.sweetcat.api.rpcdto.geography.GeographyRpcDTO;
import com.sweetcat.api.rpcdto.storeinfo.StoreIsExistedRpcDTO;
import com.sweetcat.commons.ResponseStatusEnum;
import com.sweetcat.commons.exception.ParameterFormatIllegalityException;
import com.sweetcat.commons.exception.RecodeNotExistedException;
import com.sweetcat.secondkill.application.command.AddSKCommodityPostChargeCommand;
import com.sweetcat.secondkill.application.rpc.GeographyRpc;
import com.sweetcat.secondkill.application.rpc.StoreInfoRpc;
import com.sweetcat.secondkill.domain.commodity.entity.SKCommodity;
import com.sweetcat.secondkill.domain.commodity.repository.SKCommodityRepository;
import com.sweetcat.secondkill.domain.commodity.vo.Store;
import com.sweetcat.secondkill.domain.commonditypostcharge.entity.SKCommodityPostCharge;
import com.sweetcat.secondkill.domain.commonditypostcharge.repository.SKCommodityPostChargeRepository;
import com.sweetcat.secondkill.infrastructure.service.id_format_verfiy_service.VerifyIdFormatService;
import com.sweetcat.secondkill.infrastructure.service.snowflake_service.SnowFlakeService;
import org.apache.shardingsphere.transaction.annotation.ShardingTransactionType;
import org.apache.shardingsphere.transaction.core.TransactionType;
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
public class SKCommodityPostChargeApplicationService {
    private StoreInfoRpc storeInfoRpc;
    private GeographyRpc geographyRpc;
    private SnowFlakeService snowFlakeService;
    private VerifyIdFormatService verifyIdFormatService;
    private SKCommodityPostChargeRepository postChargeRepository;
    private SKCommodityRepository commodityRepository;

    @Autowired
    public void setSnowFlakeService(SnowFlakeService snowFlakeService) {
        this.snowFlakeService = snowFlakeService;
    }

    @Autowired
    public void setStoreInfoRpc(StoreInfoRpc storeInfoRpc) {
        this.storeInfoRpc = storeInfoRpc;
    }

    @Autowired
    public void setCommodityRepository(SKCommodityRepository commodityRepository) {
        this.commodityRepository = commodityRepository;
    }

    @Autowired
    public void setGeographyRpc(GeographyRpc geographyRpc) {
        this.geographyRpc = geographyRpc;
    }

    @Autowired
    public void setPostChargeRepository(SKCommodityPostChargeRepository postChargeRepository) {
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
    @ShardingTransactionType(TransactionType.BASE)
    public void addOne(AddSKCommodityPostChargeCommand command) {
        long commodityId = command.getCommodityId();
        long storeId = command.getStoreId();
        String provinceCode = command.getProvinceCode();
        // ?????? ids
        verifyIdFormatService.verifyIds(commodityId, storeId);
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
        SKCommodity commodity = commodityRepository.findOneByCommodityId(commodityId);
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
        SKCommodityPostCharge commodityPostCharge = new SKCommodityPostCharge(chargeId, commodityId);
        Store store = new Store(storeId);
        commodityPostCharge.setStore(store);
        commodityPostCharge.setProvinceCode(provinceCode);
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
    @ShardingTransactionType(TransactionType.BASE)
    public SKCommodityPostCharge find(Long commodityId, String addressCode) {
        // ??????id ??????
        verifyIdFormatService.verifyIds(commodityId);
        // ?????? commodityid ????????????????????????
        SKCommodity commodity = commodityRepository.findOneByCommodityId(commodityId);
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
        verifyIdFormatService.verifyIds(chargeId);
        // ?????? ????????????
        SKCommodityPostCharge postCharge = postChargeRepository.findByPostChargeId(chargeId);
        // ??????
        postChargeRepository.remove(postCharge);
    }
}
