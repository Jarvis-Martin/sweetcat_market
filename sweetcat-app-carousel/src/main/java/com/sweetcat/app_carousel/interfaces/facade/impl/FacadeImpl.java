package com.sweetcat.app_carousel.interfaces.facade.impl;

import com.sweetcat.app_carousel.application.service.CarouselApplicationService;
import com.sweetcat.app_carousel.domain.carousel.entity.Carousel;
import com.sweetcat.app_carousel.interfaces.facade.Facade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: Coder_Jarvis
 * @Description:
 * @Date: 2021-10-2021/10/24-15:50
 * @Version: 1.0
 */
@Component
public class FacadeImpl implements Facade {

    @Autowired
    private CarouselApplicationService carouselApplicationService;

    @Override
    public List<Carousel> getCarouselPage(Integer page, Integer limit, Long curTimeStamp) {
        return carouselApplicationService.getCarouselPage(page, limit, curTimeStamp);
    }
}
