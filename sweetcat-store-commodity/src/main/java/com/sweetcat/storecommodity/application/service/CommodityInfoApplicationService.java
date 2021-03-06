package com.sweetcat.storecommodity.application.service;

import com.sweetcat.api.rpcdto.storeinfo.StoreIsExistedRpcDTO;
import com.sweetcat.commons.ResponseStatusEnum;
import com.sweetcat.commons.exception.CommodityNotExistedException;
import com.sweetcat.commons.exception.StoreNotExistedException;
import com.sweetcat.storecommodity.application.command.AddStoreCommodityCommand;
import com.sweetcat.storecommodity.application.rpc.StoreInfoRpc;
import com.sweetcat.storecommodity.domain.commodityinfo.entity.Commodity;
import com.sweetcat.storecommodity.domain.commodityinfo.repository.CommodityInfoRepository;
import com.sweetcat.storecommodity.infrastructure.service.id_format_verfiy_service.VerifyIdFormatService;
import com.sweetcat.storecommodity.infrastructure.service.snowflake_service.SnowFlakeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: Coder_Jarvis
 * @Description:
 * @Date: 2021-11-2021/11/8-15:10
 * @Version: 1.0
 */
@Service
public class CommodityInfoApplicationService {
    private SnowFlakeService snowFlakeService;
    private VerifyIdFormatService verifyIdFormatService;
    private CommodityInfoRepository commodityInfoRepository;
    private StoreInfoRpc storeInfoRpc;

    @Autowired
    public void setSnowFlakeService(SnowFlakeService snowFlakeService) {
        this.snowFlakeService = snowFlakeService;
    }

    @Autowired
    public void setStoreInfoRpc(StoreInfoRpc storeInfoRpc) {
        this.storeInfoRpc = storeInfoRpc;
    }

    @Autowired
    public void setVerifyIdFormatService(VerifyIdFormatService verifyIdFormatService) {
        this.verifyIdFormatService = verifyIdFormatService;
    }

    @Autowired
    public void setCommodityInfoRepository(CommodityInfoRepository commodityInfoRepository) {
        this.commodityInfoRepository = commodityInfoRepository;
    }

    @Transactional
    public Commodity findByCommodityId(Long commodityId) {
        // ?????? commodityId
        verifyIdFormatService.verifyId(commodityId);
        // ?????????????????? by commodity id
        return commodityInfoRepository.findByCommodityId(commodityId);
    }

    /**
     * ??????????????????????????????????????????
     * @param page
     * @param limit
     * @return
     */
    @Transactional
    public List<Commodity> findPageCreditCanOffsetAPart(Integer page, Integer limit) {
        limit = limit < 0 ? 15 : limit;
        page = page < 0 ? 0 : page * limit;
        return commodityInfoRepository.findPageCreditCanOffsetAPart(page, limit);
    }

    @Transactional
    public List<Commodity> findPageByStoreId(Long storeId, Integer page, Integer limit) {
        // ?????? storeId
        verifyIdFormatService.verifyId(storeId);
        limit = limit < 0 ? 15 : limit;
        page = page < 0 ? 0 : page * limit;
        return commodityInfoRepository.findPageByStoreId(storeId, page, limit);
    }

    @Transactional
    public List<Commodity> findPageNewCommodities(Integer page, Integer limit) {
        // ???????????????
        limit = limit < 0 ? 15 : limit;
        page = page < 0 ? 0 : page * limit;
        return commodityInfoRepository.findPageNewCommodities(page, limit);
    }

    @Transactional
    public void addOne(AddStoreCommodityCommand addStoreCommodityCommand) {
        // ????????????????????? ??????
        long storeId = addStoreCommodityCommand.getStoreId();
        // ????????????id??????
        verifyIdFormatService.verifyId(storeId);
        // rpc ????????????????????? ??????id ???????????????
        StoreIsExistedRpcDTO storeIsExistedRpcDTO = storeInfoRpc.storeIsExisted(storeId);
        // ???????????????
        if (!storeIsExistedRpcDTO.isExisted()) {
            throw new StoreNotExistedException(
                    ResponseStatusEnum.STORENOTEXISTED.getErrorCode(),
                    ResponseStatusEnum.STORENOTEXISTED.getErrorMessage()
            );
        }
        // ??????????????????
        long commodityId = snowFlakeService.snowflakeId();
        // ??????????????????
        Commodity commodity = new Commodity(
                commodityId,
                storeId,
                addStoreCommodityCommand.getCommodityName(),
                addStoreCommodityCommand.getBrand(),
                addStoreCommodityCommand.getPicSmall(),
                addStoreCommodityCommand.getPicBig(),
                addStoreCommodityCommand.getFirstCategory(),
                addStoreCommodityCommand.getSecondCategory(),
                addStoreCommodityCommand.getThirdCategory(),
                addStoreCommodityCommand.getUseTimes(),
                addStoreCommodityCommand.getProductionArea(),
                addStoreCommodityCommand.getOldPrice(),
                addStoreCommodityCommand.getCurrentPrice(),
                addStoreCommodityCommand.getDescription(),
                addStoreCommodityCommand.getStock(),
                0,
                0,
                0,
                addStoreCommodityCommand.getGuarantee(),
                addStoreCommodityCommand.getCreateTime(),
                addStoreCommodityCommand.getCreateTime(),
                Commodity.STATUS_AUDITING,
                addStoreCommodityCommand.getSpecification(),
                0L,
                addStoreCommodityCommand.getDefaultPostCharge(),
                addStoreCommodityCommand.getSubjoinChargePerGood(),
                addStoreCommodityCommand.getCoinCounteractRate()
                );
        // ????????? db
        commodityInfoRepository.addOne(commodity);
    }

    @Transactional
    public void increAddCommodityToCartNumber(Long commodityId, Integer increment) {
        verifyIdFormatService.verifyId(commodityId);
        Commodity commodity = commodityInfoRepository.findByCommodityId(commodityId);
        checkCommodity(commodity);
        commodity.increAddToCartNumber(increment);
        commodityInfoRepository.save(commodity);
    }

    @Transactional
    public void increCommodityCollectNumber(Long commodityId, Integer increment) {
        verifyIdFormatService.verifyId(commodityId);
        Commodity commodity = commodityInfoRepository.findByCommodityId(commodityId);
        checkCommodity(commodity);
        commodity.increCollectNumber(increment);
        commodityInfoRepository.save(commodity);
    }

    private void checkCommodity(Commodity commodity) {
        if (commodity == null) {
            throw new CommodityNotExistedException(
                    ResponseStatusEnum.COMMODITYNOTEXISTED.getErrorCode(),
                    ResponseStatusEnum.COMMODITYNOTEXISTED.getErrorMessage()
            );
        }
    }
}
