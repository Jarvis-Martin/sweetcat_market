package com.sweetcat.appcarousel.interfaces.facade;

import com.sweetcat.appcarousel.application.service.CarouselApplicationService;
import com.sweetcat.appcarousel.domain.carousel.entity.Carousel;
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
public class AppCarouselFacade {

    private CarouselApplicationService carouselApplicationService;

    @Autowired
    public void setCarouselApplicationService(CarouselApplicationService carouselApplicationService) {
        this.carouselApplicationService = carouselApplicationService;
    }

    public List<Carousel> getCarouselPage(Integer page, Integer limit, Long curTimeStamp) {
        return carouselApplicationService.getCarouselPage(page, limit, curTimeStamp);
    }
}
