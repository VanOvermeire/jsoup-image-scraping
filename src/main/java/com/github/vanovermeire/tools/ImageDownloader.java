package com.github.vanovermeire.tools;

import lombok.extern.log4j.Log4j2;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;

@Log4j2
public class ImageDownloader {

    private final String folder;

    public ImageDownloader(String folder) {
        this.folder = folder;
    }

    public Optional<String> downloadImageToFolder(String emperor, String url) {
        Optional<ImageSuffix> imageSuffixOptional = ImageSuffix.getImageSuffix(url);

        if(!imageSuffixOptional.isPresent()) {
            log.warn("Url {} is not a valid image", url);
        } else {
            try {
                ImageSuffix imageSuffix = imageSuffixOptional.get();

                String name = url.substring(url.lastIndexOf("/"), url.length() - 4).replaceAll("\\.", "");

                log.debug("Downloading {}", url);

                URL image = new URL(url);
                BufferedImage bufferedImage = ImageIO.read(image);
                File file = new File(folder + "/" + emperor + "/" + name + imageSuffix.toString());
                ImageIO.write(bufferedImage, "jpg", file);

                return Optional.of(name);
            } catch (IOException e) {
                log.warn("IO Error while downloading: {}", e);
            }
        }

        return Optional.empty();
    }
}