package com.sweetcat.storecommodity.infrastructure.repository;

import com.sweetcat.storecommodity.domain.commonditypostcharge.entity.CommodityPostCharge;
import com.sweetcat.storecommodity.domain.commonditypostcharge.repository.CommodityPostChargeRepository;
import com.sweetcat.storecommodity.infrastructure.dao.CommodityPostChargeMapper;
import com.sweetcat.storecommodity.infrastructure.factory.CommodityPostChargeFactory;
import com.sweetcat.storecommodity.infrastructure.po.CommodityPostChargePO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/10-13:14
 * @version: 1.0
 */
@Repository
public class CommodityPostChargeRepositoryImpl implements CommodityPostChargeRepository {
    private CommodityPostChargeFactory chargeFactory;
    private CommodityPostChargeMapper chargeMapper;

    @Autowired
    public void setChargeFactory(CommodityPostChargeFactory chargeFactory) {
        this.chargeFactory = chargeFactory;
    }

    @Autowired
    public void setChargeMapper(CommodityPostChargeMapper chargeMapper) {
        this.chargeMapper = chargeMapper;
    }

    @Override
    public CommodityPostCharge findByPostChargeId(Long postChargeId) {
        CommodityPostChargePO postChargePO = chargeMapper.findByPostChargeId(postChargeId);
        if (postChargePO == null) {
            return null;
        }
        return chargeFactory.create(postChargePO);
    }

    /**
     * 添加一个记录
     *
     * @param charge charge
     */
    @Override
    public void addOne(CommodityPostCharge charge) {
        chargeMapper.addOne(charge);
    }

    /**
     * find post charge recorde by commodityId and provinceCode
     *
     * @param commodityId  commodityId
     * @param provinceCode provinceCode
     * @return
     */
    @Override
    public CommodityPostCharge find(Long commodityId, String provinceCode) {
        CommodityPostChargePO postChargePO = chargeMapper.find(commodityId, provinceCode);
        if (postChargePO == null) {
            return null;
        }
        return chargeFactory.create(postChargePO);
    }

    /**
     * remove post charge recorde by commodity id and province code
     *
     * @param charge charge
     */
    @Override
    public void remove(CommodityPostCharge charge) {
        chargeMapper.delete(charge);
    }
}
