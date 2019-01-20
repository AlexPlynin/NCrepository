package com.myproject.service.PictureService;

import com.myproject.model.Pictures;
import com.myproject.repository.PicturesRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

import static com.myproject.utils.ImageUtil.loadImage;

public class PictureServiceImpl implements PictureService {

    @Autowired
    private PicturesRepository picturesRepository;

    @Override
    public Pictures addPictures(Pictures picture) {
        return null;
    }
//    @SneakyThrows
//    public byte[] loadImage(String filePath) throws URISyntaxException, IOException {
//        return Files.readAllBytes(Paths.get(this.getClass().getResource(filePath).toURI()));
//    }

    public byte [] encodeImage(String filePath) {
        byte [] image = loadImage(filePath);
        //String picture = Base64.getEncoder().encodeToString(image);
        return loadImage(filePath);
    }
}
