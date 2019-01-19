package com.myproject.utils;

import lombok.experimental.UtilityClass;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;


@UtilityClass
public class ImageUtil {



    public static byte[] loadImage() {
        try {
            return Files.readAllBytes(Paths.get(ImageUtil.class.getResource("/image.jpeg").toURI()));
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

}
