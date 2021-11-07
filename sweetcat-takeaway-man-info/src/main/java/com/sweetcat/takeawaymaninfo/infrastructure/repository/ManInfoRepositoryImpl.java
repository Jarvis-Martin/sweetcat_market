package com.sweetcat.takeawaymaninfo.infrastructure.repository;

import com.sweetcat.takeawaymaninfo.domain.maninfo.entity.ManInfo;
import com.sweetcat.takeawaymaninfo.domain.maninfo.repository.ManInfoRepository;
import com.sweetcat.takeawaymaninfo.infrastructure.dao.ManInfoMapper;
import com.sweetcat.takeawaymaninfo.infrastructure.factory.ManInfoFactory;
import com.sweetcat.takeawaymaninfo.infrastructure.po.ManInfoPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @Author: Coder_Jarvis
 * @Description:
 * @Date: 2021-11-2021/11/7-14:02
 * @Version: 1.0
 */
@Repository
public class ManInfoRepositoryImpl implements ManInfoRepository {
    private ManInfoMapper manInfoMapper;
    private ManInfoFactory manInfoFactory;

    @Autowired
    public void setManInfoMapper(ManInfoMapper manInfoMapper) {
        this.manInfoMapper = manInfoMapper;
    }

    @Autowired
    public void setManInfoFactory(ManInfoFactory manInfoFactory) {
        this.manInfoFactory = manInfoFactory;
    }

    @Override
    public void addOne(ManInfo mainInfo) {
        manInfoMapper.addOne(mainInfo);
    }

    @Override
    public ManInfo find(Long manId) {
        ManInfoPO manInfoPO = manInfoMapper.getOne(manId);
        return manInfoFactory.create(manInfoPO);
    }
}
