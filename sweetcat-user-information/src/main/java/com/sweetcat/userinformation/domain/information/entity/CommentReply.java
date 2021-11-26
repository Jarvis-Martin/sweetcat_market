package com.sweetcat.userinformation.domain.information.entity;

import com.sweetcat.commons.ResponseStatusEnum;
import com.sweetcat.commons.exception.ParameterFormatIllegalityException;
import com.sweetcat.userinformation.domain.information.vo.Commodity;
import com.sweetcat.userinformation.domain.information.vo.Store;
import lombok.Getter;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/25-16:35
 * @version: 1.0
 */
@Getter
public class CommentReply extends Information{

    /**
     * 通知id
     */
    private Long informationId;
    /**
     * 商品评论的回复
     */
    public Commodity commodity;
    /**
     * 商品所属的店家
     */
    private Store store;
    /**
     * 目标url
     */
    private String targetUrl;

    public CommentReply(Long informationId) {
        super(informationId);
        this.setInformationId(informationId);
    }

    @Override
    public void setInformationId(Long informationId) {
        if (informationId == null || informationId < 0) {
            throw new ParameterFormatIllegalityException(
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorCode(),
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorMessage()
            );
        }
        this.informationId = informationId;
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

    public void setStore(Store store) {
        if (store == null) {
            throw new ParameterFormatIllegalityException(
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorCode(),
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorMessage()
            );
        }
        this.store = store;
    }

    public void setTargetUrl(String targetUrl) {
        this.targetUrl = targetUrl;
    }
}
