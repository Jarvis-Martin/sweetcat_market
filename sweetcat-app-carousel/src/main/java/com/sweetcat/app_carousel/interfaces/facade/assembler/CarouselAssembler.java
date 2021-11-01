package com.sweetcat.app_carousel.interfaces.facade.assembler;

import com.sweetcat.app_carousel.domain.carousel.entity.Carousel;
import com.sweetcat.app_carousel.interfaces.facade.dto.CarouselDTO;
import org.springframework.stereotype.Component;

/**
 * @Author: Coder_Jarvis
 * @Description:
 * @Date: 2021-10-2021/10/16-10:55
 * @Version: 1.0
 */
@Component
public class CarouselAssembler {
    public static CarouselDTO converterToCarouselDTO(Carousel carouselDO) {
        CarouselDTO carouselDTO = new CarouselDTO();
        carouselDTO.setPicId(carouselDO.getCarouselId());
        carouselDTO.setTargetUrl(carouselDO.getTargetUrl());
        carouselDTO.setPicPath(carouselDO.getPicPath());
        carouselDTO.setStartTime(carouselDO.getStartTime());
        carouselDTO.setDeadline(carouselDO.getDeadline());
        return carouselDTO;
    }
}

