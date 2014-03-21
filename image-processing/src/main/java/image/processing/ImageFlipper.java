package image.processing;

import java.awt.image.BufferedImage;

public final class ImageFlipper {

    private ImageFlipper() {
    }

    public static BufferedImage flipVertical(BufferedImage img) {
        BufferedImage newImg = new BufferedImage(
                img.getWidth(), img.getHeight(), img.getType());
        for (int y = 0; y < img.getHeight(); y++) {
            for (int x = 0; x < img.getWidth(); x++) {
                int rgb = img.getRGB(x, y);
                newImg.setRGB(x, img.getHeight() - 1 - y, rgb);
            }
        }
        return newImg;
    }

    public static BufferedImage flipHorizontal(BufferedImage img) {
        BufferedImage newImg = new BufferedImage(
                img.getWidth(), img.getHeight(), img.getType());
        for (int y = 0; y < img.getHeight(); y++) {
            for (int x = 0; x < img.getWidth(); x++) {
                int rgb = img.getRGB(x, y);
                newImg.setRGB(img.getWidth() - 1 - x, y, rgb);
            }
        }
        return newImg;
    }

    public static BufferedImage flipNegativePositive(BufferedImage img) {
        BufferedImage newImg = new BufferedImage(
                img.getWidth(), img.getHeight(), img.getType());
        for (int y = 0; y < img.getHeight(); y++) {
            for (int x = 0; x < img.getWidth(); x++) {
                int rgb = img.getRGB(x, y);
                int a = (rgb >> 24) & 0xff;
                int r = (rgb >> 16) & 0xff;
                int g = (rgb >>  8) & 0xff;
                int b = (rgb >>  0) & 0xff;
                newImg.setRGB(x, y, getRGB(a, 255 - r, 255 - g, 255 - b));
            }
        }
        return newImg;
    }

    private static int getRGB(int a, int r, int g, int b) {
        return (a << 24) | (r << 16) | (g << 8) | (b << 0);
    }

}
