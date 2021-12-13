package com.sweetcat.appcarousel.application.service.impl;

import com.sweetcat.appcarousel.application.service.CarouselApplicationService;
import com.sweetcat.appcarousel.domain.carousel.entity.Carousel;
import com.sweetcat.appcarousel.domain.carousel.repository.CarouselRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

/**
 * @Author: Coder_Jarvis
 * @Description:
 * @Date: 2021-10-2021/10/24-15:37
 * @Version: 1.0
 */
@Service
public class CarouselApplicationServiceImpl implements CarouselApplicationService {
    @Value("${max-carousel}")
    private Integer maxCarousel;

    @Autowired
    private CarouselRepository carouselRepository;

    @Override
    public List<Carousel> getCarouselPage(Integer page, Integer limit, Long curTimeStamp) {
        page = page < 0 ? 0 : page;
        limit = limit < 0 ? maxCarousel : limit;
        curTimeStamp = curTimeStamp < 0 ? Instant.now().toEpochMilli() : curTimeStamp;

        return carouselRepository.find(page, limit, curTimeStamp);
    }


}
