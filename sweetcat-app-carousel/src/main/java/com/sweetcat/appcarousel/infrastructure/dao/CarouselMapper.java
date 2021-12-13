package com.sweetcat.appcarousel.infrastructure.dao;

import com.sweetcat.appcarousel.domain.carousel.entity.Carousel;
import com.sweetcat.appcarousel.infrastructure.po.CarouselPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CarouselMapper {

    List<CarouselPO> getCarouselPage(
            @Param("page") Integer page,
            @Param("limit") Integer limit,
            @Param("curTimeStamp") Long curTimeStamp);

    void insertOne(Carousel carousel);
}