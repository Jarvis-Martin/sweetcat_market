package com.sweetcat.appcarousel.domain.carousel.repository;

import com.sweetcat.appcarousel.domain.carousel.entity.Carousel;

import java.util.List;

/**
 * @Author: Coder_Jarvis
 * @Description:
 * @Date: 2021-10-2021/10/24-14:59
 * @Version: 1.0
 */
public interface CarouselRepository {
    /**
     * 获得分页 carousel
     * @param page page
     * @param limit limit
     * @param curTimeStamp curTimeStamp
     * @return 分页 carousel
     */
    List<Carousel> find(Integer page, Integer limit, Long curTimeStamp);
}
