package com.sweetcat.fileupload.controller.restDTO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashMap;
import java.util.List;

/**
 * @Author: Coder_Jarvis
 * @Description:
 * @Date: 2021-11-2021/11/8-11:22
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
public class FileUploadResult {
    private String name;
    private String status;
    private String url;
}
