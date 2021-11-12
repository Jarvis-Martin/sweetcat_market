package com.sweetcat.recommend.domain.recommendform.entity;

import com.sweetcat.commons.ResponseStatusEnum;
import com.sweetcat.commons.exception.ParameterFormatIllegalityException;
import lombok.Getter;

import java.util.List;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/12-16:55
 * @version: 1.0
 */
@Getter
public class RecommendForm {
    public static final Integer STAR_MIN = 0;
    public static final Integer STAR_MAX = 5;

    /**
     * 记录id
     */
    private Long recordId;

    /**
     * 推荐人
     */
    private Referrer referrer;

    /**
     * 推荐的商品
     */
    private Commodity commodity;

    /**
     * 推荐原因
     */
    private String reason;

    /**
     * 推荐时配图
     */
    private List<String> commodityPics;

    /**
     * 记录创建时间
     */
    private Long createTime;

    /**
     * 推荐星级
     */
    private Integer star;

    public RecommendForm(Long recordId) {
        this.setRecordId(recordId);
    }

    public RecommendForm(Long recordId, Referrer referrer, Commodity commodity, String reason, List<String> commodityPics, Long createTime, Integer star) {
        this.setRecordId(recordId);
        this.setReferrer(referrer);
        this.setCommodity(commodity);
        this.setReason(reason);
        this.setCommodityPics(commodityPics);
        this.setCreateTime(createTime);
        this.setStar(star);
    }

    public void setRecordId(Long recordId) {
        if (recordId == null || recordId < 0) {
            throw new ParameterFormatIllegalityException(
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorCode(),
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorMessage()
            );
        }
        this.recordId = recordId;
    }

    public void setReferrer(Referrer referrer) {
        if (referrer == null) {
            throw new ParameterFormatIllegalityException(
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorCode(),
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorMessage()
            );
        }
        this.referrer = referrer;
    }

    public void setCommodity(Commodity commodity) {
        if (commodity == null) {
            throw new ParameterFormatIllegalityException(
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorCode(),
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorMessage()
            );
        }
        this.commodity = commodity;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setCommodityPics(List<String> commodityPics) {
        this.commodityPics = commodityPics;
    }

    public void setCreateTime(Long createTime) {
        if (createTime == null || createTime < 0) {
            throw new ParameterFormatIllegalityException(
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorCode(),
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorMessage()
            );
        }
        this.createTime = createTime;
    }

    public void setStar(Integer star) {
        if (star == null || star < STAR_MIN || STAR_MAX > 5) {
            throw new ParameterFormatIllegalityException(
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorCode(),
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorMessage()
            );
        }
        this.star = star;
    }
}
