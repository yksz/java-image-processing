package image.process;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

public final class ImageScaler {

    public static final int TYPE_NEAREST_NEIGHBOR = 1;
    public static final int TYPE_BILINEAR = 2;
    public static final int TYPE_BICUBIC = 3;

    private ImageScaler() {
    }

    public static BufferedImage scale(BufferedImage img, double ratex, double ratey,
            int interpolation) {

        AffineTransform affine = AffineTransform.getScaleInstance(ratex, ratey);
        AffineTransformOp op = new AffineTransformOp(affine, interpolation);

        int newWidth  = (int) (img.getWidth()  * ratex + 0.5);
        int newHeihgt = (int) (img.getHeight() * ratey + 0.5);
        BufferedImage newImg = new BufferedImage(newWidth, newHeihgt, img.getType());
        return op.filter(img, newImg);
    }

    public static BufferedImage scale(BufferedImage img, int width, int height,
            int interpolation) {

        double ratex = (double) width / img.getWidth();
        double ratey = (double) height / img.getHeight();

        return scale(img, ratex, ratey, interpolation);
    }

}
