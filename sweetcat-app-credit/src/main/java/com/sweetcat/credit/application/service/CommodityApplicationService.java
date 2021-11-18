package com.sweetcat.credit.application.service;

import com.sweetcat.credit.domain.commodity.entity.BaseCommodity;
import com.sweetcat.credit.domain.commodity.repository.CommodityRepository;
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

    @Autowired
    public void setCommodityRepository(CommodityRepository commodityRepository) {
        this.commodityRepository = commodityRepository;
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
