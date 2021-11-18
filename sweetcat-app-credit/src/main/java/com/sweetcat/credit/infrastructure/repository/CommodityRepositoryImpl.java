package com.sweetcat.credit.infrastructure.repository;

import com.sweetcat.credit.domain.commodity.entity.BaseCommodity;
import com.sweetcat.credit.domain.commodity.repository.CommodityRepository;
import com.sweetcat.credit.infrastructure.dao.BaseCommodityMapper;
import com.sweetcat.credit.infrastructure.dao.CouponMapper;
import com.sweetcat.credit.infrastructure.factory.CommodityFactory;
import com.sweetcat.credit.infrastructure.factory.CouponFactory;
import com.sweetcat.credit.infrastructure.po.BaseCommodityPO;
import com.sweetcat.credit.infrastructure.po.CouponPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/16-22:48
 * @version: 1.0
 */
@Repository
public class CommodityRepositoryImpl implements CommodityRepository {
    private CommodityFactory commodityFactory;
    private CouponFactory couponFactory;
    private BaseCommodityMapper baseCommodityInfoMapper;
    private CouponMapper couponMapper;

    @Autowired
    public void setCouponFactory(CouponFactory couponFactory) {
        this.couponFactory = couponFactory;
    }

    @Autowired
    public void setCouponMapper(CouponMapper couponMapper) {
        this.couponMapper = couponMapper;
    }

    @Autowired
    public void setBaseCommodityInfoMapper(BaseCommodityMapper baseCommodityInfoMapper) {
        this.baseCommodityInfoMapper = baseCommodityInfoMapper;
    }

    @Autowired
    public void setCommodityFactory(CommodityFactory commodityFactory) {
        this.commodityFactory = commodityFactory;
    }

    /**
     * 添加一件商品（各种商品共有的一部分数据）
     * @param commodity
     */
    @Override
    public void addOne(BaseCommodity commodity) {
        baseCommodityInfoMapper.addOne(commodity);
    }

    /**
     * 查询所有商品的分页数据
     *
     * @param page
     * @param limit
     * @return
     */
    @Override
    public List<BaseCommodity> findPage(Integer page, Integer limit) {
        return null;
    }

    /**
     * 根据 商品分类查找商品分页数据
     *
     * @param commodityType
     * @param page
     * @param limit
     * @return
     */
    @Override
    public List<BaseCommodity> findPageByCommodityType(Integer commodityType, Integer page, Integer limit) {
        // 查到总分页数据 po
        List<BaseCommodityPO> pOPageByCommodity = baseCommodityInfoMapper.findPageByCommodityType(commodityType, page, limit);
        // 结果集非空判断
        if (pOPageByCommodity == null || pOPageByCommodity.size() <= 0) {
            return null;
        }
        // 根据 commodityType 使用对应的 mapper 从db表 中取数据，并使用对用 factory 构造领域对象
        ArrayList<BaseCommodity> baseCommoditiePage = pOPageByCommodity.stream().collect(
                ArrayList<BaseCommodity>::new,
                (con, baseCommodity) -> {
                    switch (baseCommodity.getCommodityType()) {
                        // 优惠券
                        case 0:
                            CouponPO couponPO = couponMapper.findOneByMarketItemId(baseCommodity.getMarketItemId());
                            if (couponPO == null) {
                                break;
                            }
                            con.add(couponFactory.create(baseCommodity, couponPO));
                            break;
                        // 实物
                        case 1:
                            break;
                        default:
                            break;
                    }
                },
                ArrayList::addAll);
        return baseCommoditiePage;
    }

}
