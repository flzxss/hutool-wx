package com.iceyyy.icewx.test;

import com.icexxx.icewx.menu.MediaService;

public class UploadTest {

    public static void main(String[] args) {
        String localFile = "C:/Users/Administrator/Desktop/icewx/icewx.png";
        String imageUrl = MediaService.uploadImage(localFile);
        System.out.println(imageUrl);
    }

}
