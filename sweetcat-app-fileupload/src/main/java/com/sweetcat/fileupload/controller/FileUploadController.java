package com.sweetcat.fileupload.controller;

import com.sweetcat.commons.ResponseStatusEnum;
import com.sweetcat.commons.exception.SaveFileFailException;
import com.sweetcat.fileupload.controller.restDTO.FileUploadResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @Author: Coder_Jarvis
 * @Description:
 * @Date: 2021-11-2021/11/8-11:17
 * @Version: 1.0
 */
@RestController
public class FileUploadController {
    @Value("${upload-file-path}")
    private String uploadFilePath;
    @Value("${static-server-address}")
    private String staticServerAddress;
    @Value("${avatar-img-path}")
    private String avatarImgPath;

    @PostMapping("/file")
    public FileUploadResult uploadFile(MultipartFile file) {
        // 上传文件的文件名
        String randomId = UUID.randomUUID().toString();
        String fileName = file.getOriginalFilename();
        String destFileName = randomId + fileName;
        File dest = new File(uploadFilePath + '/' + destFileName);
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
            throwSaveFileException();
        }
        String fullFilePath = staticServerAddress + avatarImgPath + destFileName;
        return new FileUploadResult(fullFilePath, "done", fullFilePath);
    }

    private void throwSaveFileException() {
        throw new SaveFileFailException(
                ResponseStatusEnum.SAVEFILEFAILE.getErrorCode(),
                ResponseStatusEnum.SAVEFILEFAILE.getErrorMessage()
        );
    }
}
