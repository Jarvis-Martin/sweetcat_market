package com.sweetcat.userinformation.interfaces.facade.restdto;

import lombok.Data;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/25-22:15
 * @version: 1.0
 */
@Data
public class SystemInformationRestDTO extends InformationRestDTO{
    /**
     * 处理时间
     */
    private Long processTime;
    /**
     * 响应标题
     */
    private String responseTitle;
}
