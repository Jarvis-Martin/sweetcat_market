package com.sweetcat.appcarousel.application.service.impl;

import com.sweetcat.appcarousel.application.command.AddCarouselCommand;
import com.sweetcat.appcarousel.application.service.CarouselApplicationService;
import com.sweetcat.appcarousel.domain.carousel.entity.Carousel;
import com.sweetcat.appcarousel.domain.carousel.repository.CarouselRepository;
import com.sweetcat.appcarousel.infrastructure.cache.BloomFilter;
import com.sweetcat.appcarousel.infrastructure.service.snowflake_service.SnowFlakeService;
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

    private CarouselRepository carouselRepository;
    private SnowFlakeService snowFlakeService;
    private BloomFilter bloomFilter;

    @Autowired
    public void setBloomFilter(BloomFilter bloomFilter) {
        this.bloomFilter = bloomFilter;
    }

    @Autowired
    public void setCarouselRepository(CarouselRepository carouselRepository) {
        this.carouselRepository = carouselRepository;
    }

    @Autowired
    public void setSnowFlakeService(SnowFlakeService snowFlakeService) {
        this.snowFlakeService = snowFlakeService;
    }

    @Override
    public List<Carousel> getCarouselPage(Integer page, Integer limit, Long curTimeStamp) {
        page = page < 0 ? 0 : page;
        limit = limit < 0 ? maxCarousel : limit;
        curTimeStamp = curTimeStamp < 0 ? Instant.now().toEpochMilli() : curTimeStamp;

        return carouselRepository.find(page, limit, curTimeStamp);
    }

    @Override
    public void addOne(AddCarouselCommand command) {
        long carouselId = snowFlakeService.snowflakeId();
        bloomFilter.add(carouselId);
        Carousel carousel = new Carousel(carouselId);
        inflateCarousel(command, carousel);
        carouselRepository.addOne(carousel);
    }

    private void inflateCarousel(AddCarouselCommand command, Carousel carousel) {
        carousel.setPicPath(command.getPicPath());
        carousel.setTargetUrl(command.getTargetUrl());
        carousel.setCreateTime(command.getCreateTime());
        carousel.setUpdateTime(command.getCreateTime());
        carousel.setStartTime(command.getStartTime());
        carousel.setDeadline(command.getDeadline());
        carousel.setShowImmediately(command.getShowImmediately());
    }
}
