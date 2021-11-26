package com.sweetcat.userinformation.interfaces.facade.restdto;

import lombok.Data;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/25-22:14
 * @version: 1.0
 */
@Data
public class CommentReplyRestDTO extends InformationRestDTO{

    /**
     * 商品评论的回复
     */
    public CommodityRestDTO commodity;
    /**
     * 商品所属的店家
     */
    private StoreRestDTO store;
    /**
     * 目标url
     */
    private String targetUrl;

}
