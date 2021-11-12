package com.sweetcat.favorite.infrastructure.po;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * t_user_favorate
 * @author 
 */
@Data
public class UserFavoritePO implements Serializable {
    private Long favoriteId;

    private Long userId;

    private Long commodityId;

    private BigDecimal priceWhenFavorite;

    private Long createTime;

    private static final long serialVersionUID = 1L;
}