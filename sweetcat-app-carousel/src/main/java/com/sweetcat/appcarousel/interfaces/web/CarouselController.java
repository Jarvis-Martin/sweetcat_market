package com.sweetcat.appcarousel.interfaces.web;

import com.sweetcat.appcarousel.application.command.AddCarouselCommand;
import com.sweetcat.appcarousel.domain.carousel.entity.Carousel;
import com.sweetcat.appcarousel.interfaces.facade.AppCarouselFacade;
import com.sweetcat.appcarousel.interfaces.facade.assembler.CarouselAssembler;
import com.sweetcat.appcarousel.interfaces.facade.restdto.CarouselDTO;
import com.sweetcat.commons.ResponseStatusEnum;
import com.sweetcat.commons.responsedto.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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

    private AppCarouselFacade facade;

    @Autowired
    public void setFacade(AppCarouselFacade facade) {
        this.facade = facade;
    }

    /**
     * 返回分页数据
     *
     * @return 分页数据
     */
    @RequestMapping("/carousels")
    public ResponseDTO getPage() {
        int page = 0;
        int limit = 6;
        Long curTimeStamp = Instant.now().toEpochMilli();
        // 调用 防腐层，获得 领域对象 Carousel实例 集合
        List<Carousel> carouselPage = facade.getCarouselPage(page, limit, curTimeStamp);
        if (carouselPage == null || carouselPage.size() <= 0) {
            return response("查询平台轮播图分页数据成功", "{}");
        }
        // 领域对象 Carousel 转 CarouseDTO
        List<CarouselDTO> carouselDTOList = carouselPage.stream().collect(
                ArrayList<CarouselDTO>::new,
                (l, carousel) -> l.add(CarouselAssembler.converterToCarouselDTO(carousel)),
                ArrayList::addAll
        );
        // 组装 ResponseDTO
        Map<String, List<CarouselDTO>> dataSection = new HashMap<>(16);
        dataSection.put("carousel", carouselDTOList);
        return response("查询平台轮播图分页数据成功", dataSection);
    }

    @PostMapping("/carousel")
    public ResponseDTO addOne(AddCarouselCommand command) {
        facade.addOne(command);
        return response("插入成功", "{}");
    }

    /**
     * 通用的放回 ResponseDTO
     *
     * @param tip  用户提示信息
     * @param data 数据部分
     * @return ResponseDTO
     */
    private ResponseDTO response(String tip, Object data) {
        return new ResponseDTO(
                ResponseStatusEnum.SUCCESS.getErrorCode(),
                ResponseStatusEnum.SUCCESS.getErrorMessage(),
                tip,
                data
        );
    }
}
