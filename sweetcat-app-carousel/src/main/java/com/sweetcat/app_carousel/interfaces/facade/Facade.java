package com.sweetcat.app_carousel.interfaces.facade;

import com.sweetcat.app_carousel.domain.carousel.entity.Carousel;

import java.util.List;

/**
 * @Author: Coder_Jarvis
 * @Description:
 * @Date: 2021-10-2021/10/24-15:49
 * @Version: 1.0
 */
public interface Facade {
    List<Carousel> getCarouselPage(Integer page, Integer limit, Long curTimeStamp);
}
