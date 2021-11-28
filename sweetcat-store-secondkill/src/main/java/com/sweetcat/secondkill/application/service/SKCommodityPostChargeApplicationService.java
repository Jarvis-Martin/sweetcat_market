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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
     * 添加一个记录
     *
     * @param command command
     */
    public void addOne(AddSKCommodityPostChargeCommand command) {
        long commodityId = command.getCommodityId();
        long storeId = command.getStoreId();
        String provinceCode = command.getProvinceCode();
        // 检查 ids
        verifyIdFormatService.verifyIds(commodityId, storeId);
        // 商店是否存在
        StoreIsExistedRpcDTO storeIsExisted = storeInfoRpc.storeIsExisted(storeId);
        // 店家不存在
        if (storeIsExisted == null) {
            throw new RecodeNotExistedException(
                    ResponseStatusEnum.RECORDENOTEXISTED.getErrorCode(),
                    ResponseStatusEnum.RECORDENOTEXISTED.getErrorMessage()
            );
        }
        // 商品是否存在
        SKCommodity commodity = commodityRepository.findOneByCommodityId(commodityId);
        // 商品不存在
        if (commodity == null) {
            throw new RecodeNotExistedException(
                    ResponseStatusEnum.RECORDENOTEXISTED.getErrorCode(),
                    ResponseStatusEnum.RECORDENOTEXISTED.getErrorMessage()
            );
        }
        // 省名是否查找
        GeographyRpcDTO geographyInfo = geographyRpc.getGeographyInfo(provinceCode);
        // 地址不正确
        if (geographyInfo == null) {
            throw new RecodeNotExistedException(
                    ResponseStatusEnum.RECORDENOTEXISTED.getErrorCode(),
                    ResponseStatusEnum.RECORDENOTEXISTED.getErrorMessage()
            );
        }
        // 生成 charge id
        long chargeId = snowFlakeService.snowflakeId();
        // 创建
        SKCommodityPostCharge commodityPostCharge = new SKCommodityPostCharge(chargeId, commodityId);
        Store store = new Store(storeId);
        commodityPostCharge.setStore(store);
        commodityPostCharge.setProvinceCode(provinceCode);
        commodityPostCharge.setPostCharge(command.getPostCharge());
        commodityPostCharge.setCreateTime(command.getCreateTime());
        commodityPostCharge.setUpdateTime(command.getCreateTime());
        // 加入 db
        postChargeRepository.addOne(commodityPostCharge);
    }

    /**
     * find post charge recorde by commodityId and provinceCode
     *
     * @param commodityId commodityId
     * @param addressCode addressCode
     * @return
     */
    public SKCommodityPostCharge find(Long commodityId, String addressCode) {
        // 检查id 格式
        verifyIdFormatService.verifyIds(commodityId);
        // 检查 commodityid 对应商品是否存在
        SKCommodity commodity = commodityRepository.findOneByCommodityId(commodityId);
        // 商品不存在
        if (commodity == null) {
            throw new ParameterFormatIllegalityException(
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorCode(),
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorMessage()
            );
        }
        // 验证 addressCode 正确性
        GeographyRpcDTO geographyInfo = geographyRpc.getGeographyInfo(addressCode);
        // geography 不存在
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
    public void remove(Long chargeId) {
        // 检查 id
        verifyIdFormatService.verifyIds(chargeId);
        // 查找 邮费记录
        SKCommodityPostCharge postCharge = postChargeRepository.findByPostChargeId(chargeId);
        // 删除
        postChargeRepository.remove(postCharge);
    }
}
