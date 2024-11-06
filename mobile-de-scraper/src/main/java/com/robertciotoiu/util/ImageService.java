package com.robertciotoiu.util;


import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

@Service
public class ImageService {
    public void downloadImage(String imageUrl, String id, String downloadPath) {
        try (InputStream in = new URL(imageUrl).openStream();
             FileOutputStream out = new FileOutputStream(downloadPath + id + ".jpg")) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }
            System.out.println("Image downloaded successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}