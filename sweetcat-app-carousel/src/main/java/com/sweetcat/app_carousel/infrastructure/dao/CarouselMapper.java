package com.sweetcat.app_carousel.infrastructure.dao;

import com.sweetcat.app_carousel.infrastructure.po.CarouselPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CarouselMapper {

    List<CarouselPO> getCarouselPage(
            @Param("page") Integer page,
            @Param("limit") Integer limit,
            @Param("curTimeStamp") Long curTimeStamp);
}