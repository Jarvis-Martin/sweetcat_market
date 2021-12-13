package com.sweetcat.appcarousel.application.service;

import com.sweetcat.appcarousel.domain.carousel.entity.Carousel;

import java.util.List;

/**
 * @Author: Coder_Jarvis
 * @Description:
 * @Date: 2021-10-2021/10/24-15:34
 * @Version: 1.0
 */
public interface CarouselApplicationService {
    List<Carousel> getCarouselPage(Integer page, Integer limit, Long curTimeStamp);
}
