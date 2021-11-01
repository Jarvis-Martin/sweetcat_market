package com.sweetcat.app_carousel.interfaces.web.controller;

import com.sweetcat.app_carousel.domain.carousel.entity.Carousel;
import com.sweetcat.app_carousel.interfaces.facade.Facade;
import com.sweetcat.app_carousel.interfaces.facade.assembler.CarouselAssembler;
import com.sweetcat.app_carousel.interfaces.facade.dto.CarouselDTO;
import com.sweetcat.commons.ResponseStatusEnum;
import com.sweetcat.commons.responsedto.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: Coder_Jarvis
 * @Description:
 * @Date: 2021-10-2021/10/24-16:03
 * @Version: 1.0
 */
@RestController
@RequestMapping("/app")
public class CarouselController {

    @Autowired
    private Facade facade;

    /**
     * 返回分页数据
     *
     * @return 分页数据
     */
    @RequestMapping("/carousel")
    public ResponseDTO getPage() {
        int page = 0;
        int limit = 6;
        Long curTimeStamp = Instant.now().toEpochMilli();
        // 调用 防腐层，获得 领域对象 Carousel实例 集合
        List<Carousel> carouselPage = facade.getCarouselPage(page, limit, curTimeStamp);
        // 领域对象 Carousel 转 CarouseDTO
        List<CarouselDTO> carouselDTOList = carouselPage.stream().collect(
                ArrayList<CarouselDTO>::new,
                (l, carousel) -> l.add(CarouselAssembler.converterToCarouselDTO(carousel)),
                ArrayList::addAll
        );
        // 组装 ResponseDTO
        Map<String, List<CarouselDTO>> carousel = new HashMap<>(16);
        carousel.put("carousel", carouselDTOList);
        return new ResponseDTO(
                ResponseStatusEnum.SUCCESS.getErrorCode(),
                ResponseStatusEnum.SUCCESS.getErrorMessage(),
                "OK",
                carousel);
    }
}
