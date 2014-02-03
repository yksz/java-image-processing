package image.util;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

public final class ImageUtil {

    private ImageUtil() {
    }

    public static BufferedImage toBufferedImage(Image img) {
        int width  = img.getWidth(null);
        int height = img.getHeight(null);
        BufferedImage bimg = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);
        Graphics g = bimg.createGraphics();
        g.drawImage(img, 0, 0, null);
        g.dispose();
        return bimg;
    }

}
