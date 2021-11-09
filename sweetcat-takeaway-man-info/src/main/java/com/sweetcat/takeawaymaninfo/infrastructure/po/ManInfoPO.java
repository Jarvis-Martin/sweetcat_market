package com.sweetcat.takeawaymaninfo.infrastructure.po;

import java.io.Serializable;
import lombok.Data;

/**
 * t_takeaway_man_info
 * @author 
 */
@Data
public class ManInfoPO implements Serializable {
    /**
     * 骑手编号
     */
    private Long manId;

    /**
     * 骑手姓名
     */
    private String name;

    /**
     * 骑手联系方式
     */
    private String phone;

    /**
     * 创建时间
     */
    private Long createTime;

    private static final long serialVersionUID = 1L;
}