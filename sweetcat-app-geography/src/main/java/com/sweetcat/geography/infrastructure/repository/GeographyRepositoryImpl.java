package com.sweetcat.geography.infrastructure.repository;

import com.sweetcat.geography.domain.geography.entity.Geography;
import com.sweetcat.geography.domain.geography.repository.GeographyRepository;
import com.sweetcat.geography.infrastructure.dao.GeographyMapper;
import com.sweetcat.geography.infrastructure.factory.GeographyFactory;
import com.sweetcat.geography.infrastructure.po.GeographyPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/10-14:10
 * @version: 1.0
 */
@Repository
public class GeographyRepositoryImpl implements GeographyRepository {
    private GeographyFactory geographyFactory;
    private GeographyMapper geographyMapper;

    @Autowired
    public void setGeographyMapper(GeographyMapper geographyMapper) {
        this.geographyMapper = geographyMapper;
    }

    @Autowired
    public void setGeographyFactory(GeographyFactory geographyFactory) {
        this.geographyFactory = geographyFactory;
    }

    /**
     * 添加一条geography
     * @param geography geography
     */
    @Override
    public void addOne(Geography geography) {
        geographyMapper.addOne(geography);
    }

    /**
     * 根据 addressCode 查找一个 geography
     * @param addressCode addressCode
     * @return
     */
    @Override
    public Geography find(String addressCode) {
        GeographyPO geographyPO = geographyMapper.find(addressCode);
        if (geographyPO == null) {
            return null;
        }
        return geographyFactory.create(geographyPO);
    }

    /**
     * 移除一条geography
     * @param geography geography
     */
    @Override
    public void remove(Geography geography) {
        geographyMapper.delete(geography);
    }
}
