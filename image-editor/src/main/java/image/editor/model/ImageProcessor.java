package image.editor.model;

import image.processing.ImageBinarizer;
import image.processing.ImageCorrector;
import image.processing.ImageFilter;
import image.processing.ImageFlipper;
import image.processing.ImageRotator;
import image.processing.ImageScaler;

import java.awt.image.BufferedImage;

public class ImageProcessor {

    private static final int MAX_HISTORY_SIZE = 5;

    private BufferedImage image;
    private final History<BufferedImage> history;
    private final Status status;

    public ImageProcessor() {
        history = new History<BufferedImage>(MAX_HISTORY_SIZE);
        status = new Status();
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
        history.add(image);
    }

    public BufferedImage undo() {
        BufferedImage img = history.undo();
        if (img == image)
            return image = history.undo();
        else
            return image = img;
    }

    public BufferedImage redo() {
        return image = history.redo();
    }

    public Status getStatus() {
        return status;
    }

    public void binarize() {
        if (image == null)
            return;

        long start = System.currentTimeMillis();
        int threshold = ImageBinarizer.getThreshold(image);
        image = ImageBinarizer.binarize(image, threshold);
        long end = System.currentTimeMillis();

        status.setProcessingTime(end - start);
        status.setThreshold(threshold);

        history.add(image);
    }

    public void contrast(int value) {
        if (image == null)
            return;

        long start = System.currentTimeMillis();
        image = ImageCorrector.contrast(image, value);
        long end = System.currentTimeMillis();

        status.setProcessingTime(end - start);

        history.add(image);
    }

    public void gammaCorrection(double value) {
        if (image == null)
            return;

        long start = System.currentTimeMillis();
        image = ImageCorrector.gammaCorrection(image, value);
        long end = System.currentTimeMillis();

        status.setProcessingTime(end - start);

        history.add(image);
    }

    public void grayscale() {
        if (image == null)
            return;

        long start = System.currentTimeMillis();
        image = ImageFilter.grayscale(image);
        long end = System.currentTimeMillis();

        status.setProcessingTime(end - start);

        history.add(image);
    }

    public void average() {
        if (image == null)
            return;

        long start = System.currentTimeMillis();
        image = ImageFilter.average(image);
        long end = System.currentTimeMillis();

        status.setProcessingTime(end - start);

        history.add(image);
    }

    public void sharpen() {
        if (image == null)
            return;

        long start = System.currentTimeMillis();
        image = ImageFilter.sharpen(image);
        long end = System.currentTimeMillis();

        status.setProcessingTime(end - start);

        history.add(image);
    }

    public void laplacian() {
        if (image == null)
            return;

        long start = System.currentTimeMillis();
        image = ImageFilter.laplacian(image);
        long end = System.currentTimeMillis();

        status.setProcessingTime(end - start);

        history.add(image);
    }

    public void sobel() {
        if (image == null)
            return;

        long start = System.currentTimeMillis();
        image = ImageFilter.sobel(image);
        long end = System.currentTimeMillis();

        status.setProcessingTime(end - start);

        history.add(image);
    }

    public void median() {
        if (image == null)
            return;

        long start = System.currentTimeMillis();
        image = ImageFilter.median(image, 3, 3);
        long end = System.currentTimeMillis();

        status.setProcessingTime(end - start);

        history.add(image);
    }

    public void color(int red, int green, int blue) {
        if (image == null)
            return;

        long start = System.currentTimeMillis();
        image = ImageFilter.color(image, red, green, blue);
        long end = System.currentTimeMillis();

        status.setProcessingTime(end - start);

        history.add(image);
    }

    public void flipVertical() {
        if (image == null)
            return;

        long start = System.currentTimeMillis();
        image = ImageFlipper.flipVertical(image);
        long end = System.currentTimeMillis();

        status.setProcessingTime(end - start);

        history.add(image);
    }

    public void flipHorizon() {
        if (image == null)
            return;

        long start = System.currentTimeMillis();
        image = ImageFlipper.flipHorizontal(image);
        long end = System.currentTimeMillis();

        status.setProcessingTime(end - start);

        history.add(image);
    }

    public void flipNegativePositive() {
        if (image == null)
            return;

        long start = System.currentTimeMillis();
        image = ImageFlipper.flipNegativePositive(image);
        long end = System.currentTimeMillis();

        status.setProcessingTime(end - start);

        history.add(image);
    }

    public void rotateToRightBy90degree() {
        if (image == null)
            return;

        long start = System.currentTimeMillis();
        image = ImageRotator.rotateToRightBy90degree(image);
        long end = System.currentTimeMillis();

        status.setProcessingTime(end - start);

        history.add(image);
    }

    public void rotateToLeftBy90degree() {
        if (image == null)
            return;

        long start = System.currentTimeMillis();
        image = ImageRotator.rotateToLeftBy90degree(image);
        long end = System.currentTimeMillis();

        status.setProcessingTime(end - start);

        history.add(image);
    }

    public void rotateBy180degree() {
        if (image == null)
            return;

        long start = System.currentTimeMillis();
        image = ImageRotator.rotateBy180degree(image);
        long end = System.currentTimeMillis();

        status.setProcessingTime(end - start);

        history.add(image);
    }

    public void scale(double ratex, double ratey, int interpolation) {
        if (image == null)
            return;

        long start = System.currentTimeMillis();
        image = ImageScaler.scale(image, ratex, ratey, interpolation);
        long end = System.currentTimeMillis();

        status.setProcessingTime(end - start);

        history.add(image);
    }

}
