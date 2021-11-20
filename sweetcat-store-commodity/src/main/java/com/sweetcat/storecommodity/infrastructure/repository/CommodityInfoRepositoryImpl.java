package com.sweetcat.storecommodity.infrastructure.repository;

import com.sweetcat.storecommodity.domain.commodityinfo.entity.CommodityInfo;
import com.sweetcat.storecommodity.domain.commodityinfo.repository.CommodityInfoRepository;
import com.sweetcat.storecommodity.infrastructure.dao.CommodityInfoMapper;
import com.sweetcat.storecommodity.infrastructure.factory.CommodityInfoFactory;
import com.sweetcat.storecommodity.infrastructure.po.CommodityInfoPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Coder_Jarvis
 * @Description:
 * @Date: 2021-11-2021/11/7-22:39
 * @Version: 1.0
 */
@Repository
public class CommodityInfoRepositoryImpl implements CommodityInfoRepository {
    private CommodityInfoMapper commodityInfoMapper;
    private CommodityInfoFactory commodityInfoFactory;

    @Autowired
    public void setCommodityInfoFactory(CommodityInfoFactory commodityInfoFactory) {
        this.commodityInfoFactory = commodityInfoFactory;
    }

    @Autowired
    public void setCommodityInfoMapper(CommodityInfoMapper commodityInfoMapper) {
        this.commodityInfoMapper = commodityInfoMapper;
    }

    @Override
    public CommodityInfo findByCommodityId(Long commodityId) {
        CommodityInfoPO commodityInfoPO = commodityInfoMapper.findByCommodityId(commodityId);
        if (commodityInfoPO == null) {
            return null;
        }
        return commodityInfoFactory.create(commodityInfoPO);
    }

    @Override
    public List<CommodityInfo> findPageByStoreId(Long storeId, Integer page, Integer limit) {
        List<CommodityInfoPO> commodityInfoPOPage = commodityInfoMapper.findPageByStoreId(storeId, page, limit);
        if (commodityInfoPOPage == null) {
            return null;
        }
        return commodityInfoPOPage.stream().collect(
                ArrayList<CommodityInfo>::new,
                (con, commodityInfoPO) -> con.add(commodityInfoFactory.create(commodityInfoPO)),
                ArrayList::addAll
        );
    }

    @Override
    public List<CommodityInfo> findPageNewCommodities(Integer page, Integer limit) {
        List<CommodityInfoPO> newCommodityInfoPOPage = commodityInfoMapper.findPageNewCommodities(page, limit);
        if (newCommodityInfoPOPage == null) {
            return null;
        }
        return newCommodityInfoPOPage.stream().collect(
                ArrayList<CommodityInfo>::new,
                (con, commodityInfoPO) -> con.add(commodityInfoFactory.create(commodityInfoPO)),
                ArrayList::addAll
        );
    }

    @Override
    public void addOne(CommodityInfo commodityInfo) {
        commodityInfoMapper.addOne(commodityInfo);
    }


    /**
     * 查找积分可抵扣部分金额的商品
     * @param page
     * @param limit
     * @return
     */
    @Override
    public List<CommodityInfo> findPageCreditCanOffsetAPart(Integer page, Integer limit) {
        List<CommodityInfoPO> commodityPageCreditCanOffsetAPart = commodityInfoMapper.findPageCreditCanOffsetAPart(page, limit);
        if (commodityPageCreditCanOffsetAPart == null) {
            return null;
        }
        return commodityPageCreditCanOffsetAPart.stream().collect(
                ArrayList<CommodityInfo>::new,
                (con, commodityInfoPO) -> con.add(commodityInfoFactory.create(commodityInfoPO)),
                ArrayList::addAll
        );
    }
}
