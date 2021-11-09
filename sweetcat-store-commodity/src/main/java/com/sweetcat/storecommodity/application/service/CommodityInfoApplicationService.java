package com.sweetcat.storecommodity.application.service;

import com.sweetcat.commons.ResponseStatusEnum;
import com.sweetcat.commons.exception.StoreNotExistedException;
import com.sweetcat.api.rpcdto.storeinfo.StoreIsExistedRpcDTO;
import com.sweetcat.commons.responsedto.ResponseDTO;
import com.sweetcat.storecommodity.application.command.AddStoreCommodityCommand;
import com.sweetcat.storecommodity.application.rpc.StoreInfoRpc;
import com.sweetcat.storecommodity.domain.commodityinfo.entity.CommodityInfo;
import com.sweetcat.storecommodity.domain.commodityinfo.repository.CommodityInfoRepository;
import com.sweetcat.storecommodity.infrastructure.service.id_format_verfiy_service.VerifyIdFormatService;
import com.sweetcat.storecommodity.infrastructure.service.snowflake_service.SnowFlakeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

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

    public CommodityInfo findByCommodityId(Long commodityId) {
        // 检查 commodityId
        verifyIdFormatService.verifyId(commodityId);
        // 查找商品信息 by commodity id
        return commodityInfoRepository.findByCommodityId(commodityId);
    }

    public List<CommodityInfo> findPageByStoreId(Long storeId, Integer page, Integer limit) {
        // 检查 storeId
        verifyIdFormatService.verifyId(storeId);
        limit = limit < 0 ? 15 : limit;
        page = page < 0 ? 0 : page * limit;
        return commodityInfoRepository.findPageByStoreId(storeId, page, limit);
    }

    public List<CommodityInfo> findPageNewCommodities(Integer page, Integer limit) {
        // 见参数检查
        limit = limit < 0 ? 15 : limit;
        page = page < 0 ? 0 : page * limit;
        return commodityInfoRepository.findPageNewCommodities(page, limit);
    }

    public void addOne(AddStoreCommodityCommand addStoreCommodityCommand) {
        // 获得需要检查的 参数
        long storeId = addStoreCommodityCommand.getStoreId();
        // 检查店家id格式
        verifyIdFormatService.verifyId(storeId);
        // rpc 检查商品对应的 商家id 是否已注册
        StoreIsExistedRpcDTO storeIsExistedRpcDTO = storeInfoRpc.storeIsExisted(storeId);
        // 店铺不存在
        if (!storeIsExistedRpcDTO.isExisted()) {
            throw new StoreNotExistedException(
                    ResponseStatusEnum.STORENOTEXISTED.getErrorCode(),
                    ResponseStatusEnum.STORENOTEXISTED.getErrorMessage()
            );
        }
        // 生成商品编号
        long commodityId = snowFlakeService.snowflakeId();
        // 构造商品对象
        CommodityInfo commodityInfo = new CommodityInfo(
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
                CommodityInfo.STATUS_AUDITING,
                addStoreCommodityCommand.getSpecification(),
                0L,
                addStoreCommodityCommand.getDefaultPostCharge(),
                addStoreCommodityCommand.getSubjoinChargePerGood(),
                addStoreCommodityCommand.getCoinCounteractRate()
                );
        // 添加如 db
        commodityInfoRepository.addOne(commodityInfo);
    }

}
