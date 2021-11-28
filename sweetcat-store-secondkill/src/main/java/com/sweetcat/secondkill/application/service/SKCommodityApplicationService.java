package com.sweetcat.secondkill.application.service;

import com.sweetcat.api.rpcdto.storeinfo.StoreInfoRpcDTO;
import com.sweetcat.commons.ResponseStatusEnum;
import com.sweetcat.commons.exception.ParameterFormatIllegalityException;
import com.sweetcat.commons.exception.StoreNotExistedException;
import com.sweetcat.secondkill.application.command.AddSKCommodityCommand;
import com.sweetcat.secondkill.application.rpc.StoreInfoRpc;
import com.sweetcat.secondkill.domain.commodity.entity.SKCommodity;
import com.sweetcat.secondkill.domain.commodity.repository.SKCommodityRepository;
import com.sweetcat.secondkill.domain.commodity.vo.Store;
import com.sweetcat.secondkill.infrastructure.service.id_format_verfiy_service.VerifyIdFormatService;
import com.sweetcat.secondkill.infrastructure.service.snowflake_service.SnowFlakeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/27-16:28
 * @version: 1.0
 */
@Service
public class SKCommodityApplicationService {
    private SKCommodityRepository commodityRepository;
    private VerifyIdFormatService verifyIdFormatService;
    private SnowFlakeService snowFlakeService;
    private StoreInfoRpc storeInfoRpc;

    @Autowired
    public void setStoreInfoRpc(StoreInfoRpc storeInfoRpc) {
        this.storeInfoRpc = storeInfoRpc;
    }

    @Autowired
    public void setVerifyIdFormatService(VerifyIdFormatService verifyIdFormatService) {
        this.verifyIdFormatService = verifyIdFormatService;
    }

    @Autowired
    public void setSnowFlakeService(SnowFlakeService snowFlakeService) {
        this.snowFlakeService = snowFlakeService;
    }

    @Autowired
    public void setCommodityRepository(SKCommodityRepository commodityRepository) {
        this.commodityRepository = commodityRepository;
    }

    /**
     * 增加一件商品记录
     * @param command
     */
    public void addOne(AddSKCommodityCommand command) {
        Long storeId = command.getStoreId();
        // 检查id
        verifyIdFormatService.verifyIds(storeId);
        // 获得 storeInfo
        StoreInfoRpcDTO storeInfoRpcDTO = storeInfoRpc.findOneByStoreId(storeId);
        // 检查 store
        checkStore(storeInfoRpcDTO);
        // 生成 commodityId
        long commodityId = snowFlakeService.snowflakeId();
        // 构建 SKCommodity
        SKCommodity skCommodity = new SKCommodity(commodityId);
        // 构建 SKCommodity.store
        Store store = new Store(command.getStoreId());
        // 填充 SKCommodity
        inflateSKCommodity(command, skCommodity, store);
        // 入库
        commodityRepository.addOne(skCommodity);
    }

    private void inflateSKCommodity(AddSKCommodityCommand command, SKCommodity skCommodity, Store store) {
        skCommodity.setStore(store);
        skCommodity.setCommodityName(command.getCommodityName());
        skCommodity.setBrand(command.getBrand());
        skCommodity.setPicSmall(command.getPicSmall());
        skCommodity.setPicBig(command.getPicBig());
        skCommodity.setFirstCategory(command.getFirstCategory());
        skCommodity.setSecondCategory(command.getSecondCategory());
        skCommodity.setThirdCategory(command.getThirdCategory());
        skCommodity.setUseTimes(command.getUseTimes());
        skCommodity.setProductionArea(command.getProductionArea());
        skCommodity.setOldPrice(command.getOldPrice());
        skCommodity.setCurrentPrice(command.getCurrentPrice());
        skCommodity.setDescription(command.getDescription());
        skCommodity.setTotalStock(command.getTotalStock());
        skCommodity.setMonthlySales(0);
        skCommodity.setAddToCarNumber(0);
        skCommodity.setCollectNumber(0);
        skCommodity.setGuarantee(command.getGuarantee());
        skCommodity.setCreateTime(command.getCreateTime());
        skCommodity.setUpdateTime(command.getCreateTime());
        skCommodity.setStatus(SKCommodity.STATUS_AUDITING);
        skCommodity.setSpecification(command.getSpecification());
        skCommodity.setCommentNumber(0L);
        skCommodity.setDefaultPostCharge(command.getDefaultPostCharge());
        skCommodity.setSubjoinChargePerGood(command.getSubjoinChargePerGood());
        skCommodity.setStartTime(command.getStartTime());
        skCommodity.setRemainStock(command.getTotalStock());
    }

    private void checkStore(StoreInfoRpcDTO storeInfoRpcDTO) {
        if (storeInfoRpcDTO == null) {
            throw new StoreNotExistedException(
                    ResponseStatusEnum.STORENOTEXISTED.getErrorCode(),
                    ResponseStatusEnum.STORENOTEXISTED.getErrorMessage()
            );
        }
    }

    /**
     * 移除秒杀商品
     * @param commodityId
     */
    public void removeOne(Long commodityId) {
        // 检查 commodityId
        verifyIdFormatService.verifyIds(commodityId);
        // 查找 SKCommodity
        SKCommodity skCommodity = commodityRepository.findOneByCommodityId(commodityId);
        // 移除
        commodityRepository.removeOne(skCommodity);
    }

    /**
     * 查找给定时间所在区间内的秒杀商品分页数据
     * @param currentTimeStamp
     * @param page
     * @param limit
     * @return
     */
    public List<SKCommodity> findPageByTime(Long currentTimeStamp, Integer page, Integer limit) {
        // 调整 page limit
        limit = limit == null || limit < 0 ? 15 : limit;
        page = page == null || page < 0 ? 0 : page * limit;
        // 检查 currentTimeStamp
        checkTimeStamp(currentTimeStamp);
        return commodityRepository.findPageByTime(currentTimeStamp, page, limit);
    }

    private void checkTimeStamp(Long currentTimeStamp) {
        if (currentTimeStamp == null || currentTimeStamp < 0) {
            throw new ParameterFormatIllegalityException(
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorCode(),
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorMessage()
            );
        }
    }

    /**
     * 查找秒杀商品的详细信息
     * @param commodityId
     * @return
     */
    public SKCommodity findOneByCommodityId(Long commodityId) {
        // 检查 commodityId
        verifyIdFormatService.verifyIds(commodityId);
        // 查 commodity
        return commodityRepository.findOneByCommodityId(commodityId);
    }
}
