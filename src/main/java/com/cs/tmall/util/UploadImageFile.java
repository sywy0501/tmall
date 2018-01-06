package com.cs.tmall.util;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author cs
 * @version V1.0
 * @Title: tmall_ssm
 * @Package com.cs.tmall.util
 * @Description: TODO
 * @date 2017/12/27 上午 11:19
 */
public class UploadImageFile {
    MultipartFile image;

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }
}
