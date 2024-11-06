package com.robertciotoiu;

import com.robertciotoiu.util.ImageService;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class ImageServiceTest {

    private final ImageService imageService = new ImageService();

    @Test
    void downloadImageSuccessfully() throws IOException {
        String imageUrl = "https:\u002F\u002Fimg.classistatic.de\u002Fapi\u002Fv1\u002Fmo-prod\u002Fimages\u002Ff9\u002Ff919fb88-0dd1-43e7-8da2-07ff3eca92f1?rule=mo-160.jpg";
        String id = "400685894";
        String downloadPath = "mobile-de-scraper/src/test/resources";
        imageService.downloadImage(imageUrl, id, downloadPath);
        File file = new File(downloadPath + id + ".jpg");
        assertTrue(file.exists());
        assertTrue(file.length() > 0);
        file.delete();
    }

    @Test
    void downloadImageInvalidUrl() {
        String imageUrl = "https://google.com/image.jpg";
        String id = "invalidImage";
        String downloadPath = "mobile-de-scraper/src/test/resources";
        assertThrows(IOException.class, () -> imageService.downloadImage(imageUrl, id, downloadPath));
    }

    @Test
    void downloadImageEmptyUrl() {
        String imageUrl = "";
        String id = "emptyUrlImage";
        String downloadPath = "mobile-de-scraper/src/test/resources";
        assertThrows(IOException.class, () -> imageService.downloadImage(imageUrl, id, downloadPath));
    }

    @Test
    void downloadImageNullUrl() {
        String imageUrl = null;
        String id = "nullUrlImage";
        String downloadPath = "mobile-de-scraper/src/test/resources";
        assertThrows(NullPointerException.class, () -> imageService.downloadImage(imageUrl, id, downloadPath));
    }
}