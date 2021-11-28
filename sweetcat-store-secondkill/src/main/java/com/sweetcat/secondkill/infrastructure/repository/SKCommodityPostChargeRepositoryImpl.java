package com.sweetcat.secondkill.infrastructure.repository;

import com.sweetcat.secondkill.domain.commonditypostcharge.entity.SKCommodityPostCharge;
import com.sweetcat.secondkill.domain.commonditypostcharge.repository.SKCommodityPostChargeRepository;
import com.sweetcat.secondkill.infrastructure.dao.SKCommodityPostChargeMapper;
import com.sweetcat.secondkill.infrastructure.factory.SKCommodityPostChargeFactory;
import com.sweetcat.secondkill.infrastructure.po.SKCommodityPostChargePO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/10-13:14
 * @version: 1.0
 */
@Repository
public class SKCommodityPostChargeRepositoryImpl implements SKCommodityPostChargeRepository {
    private SKCommodityPostChargeFactory chargeFactory;
    private SKCommodityPostChargeMapper chargeMapper;

    @Autowired
    public void setChargeFactory(SKCommodityPostChargeFactory chargeFactory) {
        this.chargeFactory = chargeFactory;
    }

    @Autowired
    public void setChargeMapper(SKCommodityPostChargeMapper chargeMapper) {
        this.chargeMapper = chargeMapper;
    }

    @Override
    public SKCommodityPostCharge findByPostChargeId(Long postChargeId) {
        SKCommodityPostChargePO postChargePO = chargeMapper.findOneByPostChargeId(postChargeId);
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
    public void addOne(SKCommodityPostCharge charge) {
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
    public SKCommodityPostCharge find(Long commodityId, String provinceCode) {
        SKCommodityPostChargePO postChargePO = chargeMapper.findOne(commodityId, provinceCode);
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
    public void remove(SKCommodityPostCharge charge) {
        chargeMapper.deleteOne(charge);
    }
}
