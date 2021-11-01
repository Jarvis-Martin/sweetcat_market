package com.sweetcat.app_carousel.infrastructure.repository;

import com.sweetcat.app_carousel.domain.carousel.entity.Carousel;
import com.sweetcat.app_carousel.infrastructure.dao.CarouselMapper;
import com.sweetcat.app_carousel.infrastructure.factory.CarouselFactory;
import com.sweetcat.app_carousel.infrastructure.po.CarouselPO;
import com.sweetcat.app_carousel.domain.carousel.repository.CarouselRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Coder_Jarvis
 * @Description:
 * @Date: 2021-10-2021/10/24-15:14
 * @Version: 1.0
 */
@Repository
public class CarouselRepositoryImpl implements CarouselRepository {

    @Autowired
    private CarouselMapper carouselMapper;

    @Autowired
    private CarouselFactory carouselFactory;

    @Override
    public List<Carousel> find(Integer page, Integer limit, Long curTimeStamp) {
        // 获取分页数据
        List<CarouselPO> carouselPOPage = carouselMapper.getCarouselPage(page, limit, curTimeStamp);
        // carouselPO 转 carousel list并返回
        return carouselPOPage.stream().collect(ArrayList<Carousel>::new,
                (l, carouselPO) -> l.add(carouselFactory.create(carouselPO)),
                ArrayList::addAll);

    }
}
