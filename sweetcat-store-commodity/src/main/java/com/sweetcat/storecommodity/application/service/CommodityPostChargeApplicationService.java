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
     * 添加一个记录
     *
     * @param command command
     */
    @Transactional
    public void addOne(AddCommodityPostChargeCommand command) {
        long commodityId = command.getCommodityId();
        long storeId = command.getStoreId();
        String provinceCode = command.getProvinceCode();
        // 检查 ids
        verifyIdFormatService.verifyId(commodityId, storeId);
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
        Commodity commodity = commodityInfoRepository.findByCommodityId(commodityId);
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
        CommodityPostCharge commodityPostCharge = new CommodityPostCharge(chargeId, commodityId, storeId, provinceCode);
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
    @Transactional
    public CommodityPostCharge find(Long commodityId, String addressCode) {
        // 检查id 格式
        verifyIdFormatService.verifyId(commodityId);
        // 检查 commodityid 对应商品是否存在
        Commodity commodity = commodityInfoRepository.findByCommodityId(commodityId);
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
    @Transactional
    public void remove(Long chargeId) {
        // 检查 id
        verifyIdFormatService.verifyId(chargeId);
        // 查找 邮费记录
        CommodityPostCharge postCharge = postChargeRepository.findByPostChargeId(chargeId);
        // 删除
        postChargeRepository.remove(postCharge);
    }
}
