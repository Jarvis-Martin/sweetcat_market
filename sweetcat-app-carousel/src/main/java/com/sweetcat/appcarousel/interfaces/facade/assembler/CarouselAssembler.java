package com.sweetcat.appcarousel.interfaces.facade.assembler;

import com.sweetcat.appcarousel.domain.carousel.entity.Carousel;
import com.sweetcat.appcarousel.interfaces.facade.restdto.CarouselDTO;
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
        inflateCarouselDTO(carouselDO, carouselDTO);
        return carouselDTO;
    }

    private static void inflateCarouselDTO(Carousel carouselDO, CarouselDTO carouselDTO) {
        carouselDTO.setPicId(carouselDO.getCarouselId());
        carouselDTO.setTargetUrl(carouselDO.getTargetUrl());
        carouselDTO.setPicPath(carouselDO.getPicPath());
        carouselDTO.setStartTime(carouselDO.getStartTime());
        carouselDTO.setDeadline(carouselDO.getDeadline());
    }
}

