package com.github.vanovermeire.tools;

import org.junit.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


public class ImageSuffixTest {

    @Test
    public void testImageSuffixJPGName() {
        assertThat(ImageSuffix.JPG.toString()).isEqualTo(".jpg");
    }

    @Test
    public void testImageSuffixPNGName() {
        assertThat(ImageSuffix.PNG.toString()).isEqualTo(".png");
    }

    @Test
    public void testGetImageSuffixJPG() {
        String example = "somejpgfile.jpg";

        Optional<ImageSuffix> imageSuffix = ImageSuffix.getImageSuffix(example);

        assertThat(imageSuffix).isPresent()
            .contains(ImageSuffix.JPG);
    }

    @Test
    public void testGetImageSuffixPNG() {
        String example = "somepngfile.png";

        Optional<ImageSuffix> imageSuffix = ImageSuffix.getImageSuffix(example);

        assertThat(imageSuffix).isPresent()
                .contains(ImageSuffix.PNG);
    }

    @Test
    public void testGetUnknownImageSuffix() {
        String example = "somepngfile.gif";

        Optional<ImageSuffix> imageSuffix = ImageSuffix.getImageSuffix(example);

        assertThat(imageSuffix).isEmpty();
    }
}
