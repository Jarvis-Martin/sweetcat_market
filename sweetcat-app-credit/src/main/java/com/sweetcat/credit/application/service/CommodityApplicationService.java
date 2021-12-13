package com.sweetcat.credit.application.service;

import com.sweetcat.credit.application.command.AddBaseCommodityCommand;
import com.sweetcat.credit.domain.commodity.entity.BaseCommodity;
import com.sweetcat.credit.domain.commodity.repository.CommodityRepository;
import com.sweetcat.credit.domain.commodity.vo.Creator;
import com.sweetcat.credit.infrastructure.cache.BloomFilter;
import com.sweetcat.credit.infrastructure.service.id_format_verfiy_service.VerifyIdFormatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/17-20:38
 * @version: 1.0
 */
@Service
public class CommodityApplicationService {
    private CommodityRepository commodityRepository;
    private VerifyIdFormatService verifyIdFormatService;
    private BloomFilter bloomFilter;

    @Autowired
    public void setBloomFilter(BloomFilter bloomFilter) {
        this.bloomFilter = bloomFilter;
    }

    @Autowired
    public void setVerifyIdFormatService(VerifyIdFormatService verifyIdFormatService) {
        this.verifyIdFormatService = verifyIdFormatService;
    }

    @Autowired
    public void setCommodityRepository(CommodityRepository commodityRepository) {
        this.commodityRepository = commodityRepository;
    }

    /**
     * 添加一件商品（各种商品共有的一部分数据）
     *
     * @param commodity
     */
    public void addOne(AddBaseCommodityCommand commodity) {
        Long creatorId = commodity.getCreatorId();
        Long marketItemId = commodity.getMarketItemId();
        // 创建创建人id
        verifyIdFormatService.verifyIds(creatorId, marketItemId);
        bloomFilter.add(marketItemId);
        // 构建 creator
        Creator creator = new Creator(commodity.getCreatorId());
        creator.setCreatorName(commodity.getCreatorName());
        // 构建 BaseCommodity
        BaseCommodity baseCommodity = new BaseCommodity(
                marketItemId,
                creator,
                commodity.getStock(),
                commodity.getCreateTime(),
                commodity.getUpdateTime(),
                commodity.getCreditNumber(),
                commodity.getCommodityType()
        );
        // 加入db
        commodityRepository.addOne(baseCommodity);
    }

    /**
     * 根据 商品分类查找商品分页数据
     *
     * @param commodityType
     * @param page
     * @param limit
     * @return
     */
    public List<BaseCommodity> findPageByCommodityType(Integer commodityType, Integer page, Integer limit) {
        // page limit检查
        limit = limit == null || limit < 0 ? 15 : limit;
        page = page == null || page < 0 ? 0 : page * limit;
        // 查询并返回
        return commodityRepository.findPageByCommodityType(commodityType, page, limit);
    }

}
