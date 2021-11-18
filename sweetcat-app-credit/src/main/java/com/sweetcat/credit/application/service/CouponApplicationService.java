package com.sweetcat.credit.application.service;

import com.sweetcat.credit.application.command.AddBaseCommodityCommand;
import com.sweetcat.credit.application.command.AddCouponCommand;
import com.sweetcat.credit.domain.commodity.entity.Coupon;
import com.sweetcat.credit.domain.commodity.repository.CouponRepository;
import com.sweetcat.credit.domain.commodity.vo.Creator;
import com.sweetcat.credit.infrastructure.service.id_format_verfiy_service.VerifyIdFormatService;
import com.sweetcat.credit.infrastructure.service.snowflake_service.SnowFlakeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/18-17:19
 * @version: 1.0
 */
@Service
public class CouponApplicationService {
    private CouponRepository couponRepository;
    private CommodityApplicationService commodityApplicationService;
    private VerifyIdFormatService verifyIdFormatService;
    private SnowFlakeService snowFlakeService;

    @Autowired
    public void setCouponRepository(CouponRepository couponRepository) {
        this.couponRepository = couponRepository;
    }

    @Autowired
    public void setCommodityApplicationService(CommodityApplicationService commodityApplicationService) {
        this.commodityApplicationService = commodityApplicationService;
    }

    @Autowired
    public void setVerifyIdFormatService(VerifyIdFormatService verifyIdFormatService) {
        this.verifyIdFormatService = verifyIdFormatService;
    }

    @Autowired
    public void setSnowFlakeService(SnowFlakeService snowFlakeService) {
        this.snowFlakeService = snowFlakeService;
    }

    /**
     * 添加一种优惠券
     *
     * @param command
     */
    public void addOne(AddCouponCommand command) {
        Long creatorId = command.getCreatorId();
        // 检查 creatorId
        verifyIdFormatService.verifyIds(creatorId);
        // 生成 marketItemId
        long marketItemId = snowFlakeService.snowflakeId();
        // 生成优惠券id
        long couponId = snowFlakeService.snowflakeId();
        // 提取数据
        String creatorName = command.getCreatorName();
        long stock = command.getStock();
        long createTime = command.getCreateTime();
        int creditNumber = command.getCreditNumber();
        int commodityType = command.getCommodityType();
        BigDecimal thresholdPrice = command.getThresholdPrice();
        BigDecimal counteractPrice = command.getCounteractPrice();
        int targetType = command.getTargetType();
        long storeId = command.getStoreId() == null ? 0L : command.getStoreId();
        String storeName = command.getStoreName();
        long commodityId = command.getCommodityId() == null ? 0L : command.getCommodityId();
        List<String> commodityPicSmall = command.getCommodityPicSmall();
        String commodityName = command.getCommodityName();
        int timeType = command.getTimeType();
        long validDuration = command.getValidDuration();
        long startTime = command.getStartTime() == null ? 0L : command.getStartTime();
        long deadline = command.getDeadline() == null ? 0L : command.getDeadline();

        // 构建 AddBaseCommodityCommand
        AddBaseCommodityCommand addBaseCommodityCommand = new AddBaseCommodityCommand();
        addBaseCommodityCommand.setMarketItemId(marketItemId);
        addBaseCommodityCommand.setCreatorId(creatorId);
        addBaseCommodityCommand.setCreatorName(creatorName);
        addBaseCommodityCommand.setStock(stock);
        addBaseCommodityCommand.setCreateTime(createTime);
        addBaseCommodityCommand.setUpdateTime(createTime);
        addBaseCommodityCommand.setCreditNumber(creditNumber);
        addBaseCommodityCommand.setCommodityType(commodityType);
        // 添加一个 BaseCommodity
        commodityApplicationService.addOne(addBaseCommodityCommand);

        // 构建 Coupon # creator
        Creator creator = new Creator(creatorId);
        creator.setCreatorName(creatorName);
        // 构建 Coupon
        Coupon coupon = new Coupon(
                marketItemId,
                creator,
                stock,
                createTime,
                createTime,
                creditNumber,
                commodityType
        );
        coupon.setCouponId(couponId);
        coupon.setThresholdPrice(thresholdPrice);
        coupon.setCounteractPrice(counteractPrice);
        coupon.setTargetType(targetType);
        coupon.setStoreId(storeId);
        coupon.setStoreName(storeName);
        coupon.setCommodityId(commodityId);
        coupon.setCommodityPicSmall(commodityPicSmall);
        coupon.setCommodityName(commodityName);
        coupon.setTimeType(timeType);
        coupon.setValidDuration(validDuration);
        coupon.setStartTime(startTime);
        coupon.setDeadline(deadline);
        // 加入db
        couponRepository.addOne(coupon);
    }

    /**
     * find data of coupon by targetType of coupon
     *
     * @param targetType
     * @param page
     * @param limit
     * @return
     */
    public List<Coupon> findPageByTargetType(Long targetType, Integer page, Integer limit) {
        // page limit 检查
        limit = limit == null || limit < 0 ? 15 : limit;
        page = page == null || page < 0 ? 0 : page * limit;
        // 查询并返回
        return couponRepository.findPageByTargetType(targetType, page, limit);
    }

    /**
     * find page data of timeType of coupon
     *
     * @param timeType
     * @param page
     * @param limit
     * @return
     */
    public List<Coupon> findPageByTimeType(Long timeType, Integer page, Integer limit) {
        // page limit 检查
        limit = limit == null || limit < 0 ? 15 : limit;
        page = page == null || page < 0 ? 0 : page * limit;
        // 查询并返回
        return couponRepository.findPageByTimeType(timeType, page, limit);
    }

}
