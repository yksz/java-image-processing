package image.process;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

public final class ImageRotator {

    private ImageRotator() {
    }

    public static BufferedImage rotate(BufferedImage img, int degree) {

        double radian = degree * Math.PI / 180;

        AffineTransform affine = new AffineTransform();
        affine.setToRotation(radian, img.getWidth() / 2.0, img.getHeight() / 2.0);
        AffineTransformOp op = new AffineTransformOp(affine, AffineTransformOp.TYPE_BILINEAR);

        BufferedImage newImg = new BufferedImage(
                img.getWidth(), img.getHeight(), img.getType());
        return op.filter(img, newImg);
    }

    public static BufferedImage rotateToRightBy90degree(BufferedImage img) {

        BufferedImage newImg = new BufferedImage(
                img.getHeight(), img.getWidth(), img.getType());
        for (int y = 0; y < img.getHeight(); y++) {
            for (int x = 0; x < img.getWidth(); x++) {
                int rgb = img.getRGB(x, y);
                newImg.setRGB(img.getHeight() - 1 - y, x, rgb);
            }
        }
        return newImg;
    }

    public static BufferedImage rotateBy180degree(BufferedImage img) {

        BufferedImage newImg = new BufferedImage(
                img.getWidth(), img.getHeight(), img.getType());
        for (int y = 0; y < img.getHeight(); y++) {
            for (int x = 0; x < img.getWidth(); x++) {
                int rgb = img.getRGB(x, y);
                newImg.setRGB(img.getWidth() - 1 - x, img.getHeight() - 1 - y, rgb);
            }
        }
        return newImg;
    }

    public static BufferedImage rotateToRightBy270degree(BufferedImage img) {

        BufferedImage newImg = new BufferedImage(
                img.getHeight(), img.getWidth(), img.getType());
        for (int y = 0; y < img.getHeight(); y++) {
            for (int x = 0; x < img.getWidth(); x++) {
                int rgb = img.getRGB(x, y);
                newImg.setRGB(y, img.getWidth() - 1 - x, rgb);
            }
        }
        return newImg;
    }

    public static BufferedImage rotateToLeftBy90degree(BufferedImage img) {
        return rotateToRightBy270degree(img);
    }

    public static BufferedImage rotateToLeftBy270degree(BufferedImage img) {
        return rotateToRightBy90degree(img);
    }

}
