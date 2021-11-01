package com.sweetcat.app_carousel.infrastructure.factory;

import com.sweetcat.app_carousel.domain.carousel.entity.Carousel;
import com.sweetcat.app_carousel.infrastructure.dao.CarouselMapper;
import com.sweetcat.app_carousel.infrastructure.po.CarouselPO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Coder_Jarvis
 * @Description:
 * @Date: 2021-10-2021/10/24-15:16
 * @Version: 1.0
 */
@Component
public class CarouselFactory {
/// 该类用户 构建，装载 CarouselAggregate 对象

    private  final Logger logger = LoggerFactory.getLogger(CarouselFactory.class);

    @Autowired
    private CarouselMapper carouselMapper;

    public Carousel create(CarouselPO carouselPO) {
        return converterToCarousel(carouselPO);
    }

    private Carousel converterToCarousel(CarouselPO carouselPO) {
        Carousel carouselDO = new Carousel();
        carouselDO.setCarouselId(carouselPO.getCarouselId());
        carouselDO.setPicPath(carouselPO.getPicPath());
        carouselDO.setTargetUrl(carouselPO.getTargetUrl());
        carouselDO.setCreateTime(carouselPO.getCreateTime());
        carouselDO.setUpdateTime(carouselPO.getUpdateTime());
        carouselDO.setStartTime(carouselPO.getStartTime());
        carouselDO.setDeadline(carouselPO.getDeadline());
        carouselDO.setShowImmediately(carouselPO.getShowImmediately());
        return carouselDO;
    }
}
