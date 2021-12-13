package com.sweetcat.appcarousel.infrastructure.factory;

import com.sweetcat.appcarousel.domain.carousel.entity.Carousel;
import com.sweetcat.appcarousel.infrastructure.po.CarouselPO;
import org.springframework.stereotype.Component;

/**
 * @Author: Coder_Jarvis
 * @Description:
 * @Date: 2021-10-2021/10/24-15:16
 * @Version: 1.0
 */
@Component
public class CarouselFactory {
/// 该类用户 构建，装载 CarouselAggregate 对象

    public Carousel create(CarouselPO carouselPO) {
        return converterToCarousel(carouselPO);
    }

    private Carousel converterToCarousel(CarouselPO carouselPO) {
        Carousel carouselDO = new Carousel(carouselPO.getCarouselId());
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
