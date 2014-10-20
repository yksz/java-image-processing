package image.editor.controller;

import java.awt.image.BufferedImage;

import image.editor.model.Images;
import image.editor.model.Model;
import image.editor.model.Status;
import image.editor.view.View;
import image.editor.view.dialog.ColorDialog;
import image.editor.view.dialog.ContrastDialog;
import image.editor.view.dialog.GammaCorrectionDialog;
import image.editor.view.dialog.ScaleDialog;
import image.processing.ImageBinarizer;
import image.processing.ImageCorrector;
import image.processing.ImageFilter;
import image.processing.ImageFlipper;
import image.processing.ImageRotator;
import image.processing.ImageScaler;

public class ProcessController {

    private final Model model;
    private final View view;

    public ProcessController(Model model, View view) {
        this.model = model;
        this.view = view;
    }

    public void binarize() {
        Images images = model.getImages();
        BufferedImage img = images.getImage();
        if (img == null)
            return;

        long start = System.currentTimeMillis();
        int threshold = ImageBinarizer.getThreshold(img);
        img = ImageBinarizer.binarize(img, threshold);
        long end = System.currentTimeMillis();

        images.setImage(img);
        Status status = model.getStatus();
        status.setProcessingTime(end - start);
        status.setThreshold(threshold);
        view.update();
    }

    public void showContrastDialog() {
        Images images = model.getImages();
        BufferedImage img = images.getImage();
        if (img == null)
            return;

        ContrastDialog dialog = new ContrastDialog(view, true, img, this);
        dialog.setVisible(true);
    }

    public void contrast(int value) {
        Images images = model.getImages();
        BufferedImage img = images.getImage();
        if (img == null)
            return;

        long start = System.currentTimeMillis();
        img = ImageCorrector.contrast(img, value);
        long end = System.currentTimeMillis();

        images.setImage(img);
        Status status = model.getStatus();
        status.setProcessingTime(end - start);
        view.update();
    }

    public void showGammaCorrectionDialog() {
        Images images = model.getImages();
        BufferedImage img = images.getImage();
        if (img == null)
            return;

        GammaCorrectionDialog dialog = new GammaCorrectionDialog(view, true, img, this);
        dialog.setVisible(true);
    }

    public void gammaCorrection(double value) {
        Images images = model.getImages();
        BufferedImage img = images.getImage();
        if (img == null)
            return;

        long start = System.currentTimeMillis();
        img = ImageCorrector.gammaCorrection(img, value);
        long end = System.currentTimeMillis();

        images.setImage(img);
        Status status = model.getStatus();
        status.setProcessingTime(end - start);
        view.update();
    }

    public void grayscale() {
        Images images = model.getImages();
        BufferedImage img = images.getImage();
        if (img == null)
            return;

        long start = System.currentTimeMillis();
        img = ImageFilter.grayscale(img);
        long end = System.currentTimeMillis();

        images.setImage(img);
        Status status = model.getStatus();
        status.setProcessingTime(end - start);
        view.update();
    }

    public void average() {
        Images images = model.getImages();
        BufferedImage img = images.getImage();
        if (img == null)
            return;

        long start = System.currentTimeMillis();
        img = ImageFilter.average(img);
        long end = System.currentTimeMillis();

        images.setImage(img);
        Status status = model.getStatus();
        status.setProcessingTime(end - start);
        view.update();
    }

    public void sharpen() {
        Images images = model.getImages();
        BufferedImage img = images.getImage();
        if (img == null)
            return;

        long start = System.currentTimeMillis();
        img = ImageFilter.sharpen(img);
        long end = System.currentTimeMillis();

        images.setImage(img);
        Status status = model.getStatus();
        status.setProcessingTime(end - start);
        view.update();
    }

    public void laplacian() {
        Images images = model.getImages();
        BufferedImage img = images.getImage();
        if (img == null)
            return;

        long start = System.currentTimeMillis();
        img = ImageFilter.laplacian(img);
        long end = System.currentTimeMillis();

        images.setImage(img);
        Status status = model.getStatus();
        status.setProcessingTime(end - start);
        view.update();
    }

    public void sobel() {
        Images images = model.getImages();
        BufferedImage img = images.getImage();
        if (img == null)
            return;

        long start = System.currentTimeMillis();
        img = ImageFilter.sobel(img);
        long end = System.currentTimeMillis();

        images.setImage(img);
        Status status = model.getStatus();
        status.setProcessingTime(end - start);
        view.update();
    }

    public void median() {
        Images images = model.getImages();
        BufferedImage img = images.getImage();
        if (img == null)
            return;

        long start = System.currentTimeMillis();
        img = ImageFilter.median(img, 3, 3);
        long end = System.currentTimeMillis();

        images.setImage(img);
        Status status = model.getStatus();
        status.setProcessingTime(end - start);
        view.update();
    }

    public void showColorDialog() {
        Images images = model.getImages();
        BufferedImage img = images.getImage();
        if (img == null)
            return;

        ColorDialog dialog = new ColorDialog(view, true, img, this);
        dialog.setVisible(true);
    }

    public void color(int red, int green, int blue) {
        Images images = model.getImages();
        BufferedImage img = images.getImage();
        if (img == null)
            return;

        long start = System.currentTimeMillis();
        img = ImageFilter.color(img, red, green, blue);
        long end = System.currentTimeMillis();

        images.setImage(img);
        Status status = model.getStatus();
        status.setProcessingTime(end - start);
        view.update();
    }

    public void flipVertical() {
        Images images = model.getImages();
        BufferedImage img = images.getImage();
        if (img == null)
            return;

        long start = System.currentTimeMillis();
        img = ImageFlipper.flipVertical(img);
        long end = System.currentTimeMillis();

        images.setImage(img);
        Status status = model.getStatus();
        status.setProcessingTime(end - start);
        view.update();
    }

    public void flipHorizon() {
        Images images = model.getImages();
        BufferedImage img = images.getImage();
        if (img == null)
            return;

        long start = System.currentTimeMillis();
        img = ImageFlipper.flipHorizontal(img);
        long end = System.currentTimeMillis();

        images.setImage(img);
        Status status = model.getStatus();
        status.setProcessingTime(end - start);
        view.update();
    }

    public void flipNegativePositive() {
        Images images = model.getImages();
        BufferedImage img = images.getImage();
        if (img == null)
            return;

        long start = System.currentTimeMillis();
        img = ImageFlipper.flipNegativePositive(img);
        long end = System.currentTimeMillis();

        images.setImage(img);
        Status status = model.getStatus();
        status.setProcessingTime(end - start);
        view.update();
    }

    public void rotateToRightBy90degree() {
        Images images = model.getImages();
        BufferedImage img = images.getImage();
        if (img == null)
            return;

        long start = System.currentTimeMillis();
        img = ImageRotator.rotateToRightBy90degree(img);
        long end = System.currentTimeMillis();

        images.setImage(img);
        Status status = model.getStatus();
        status.setProcessingTime(end - start);
        view.update();
    }

    public void rotateToLeftBy90degree() {
        Images images = model.getImages();
        BufferedImage img = images.getImage();
        if (img == null)
            return;

        long start = System.currentTimeMillis();
        img = ImageRotator.rotateToLeftBy90degree(img);
        long end = System.currentTimeMillis();

        images.setImage(img);
        Status status = model.getStatus();
        status.setProcessingTime(end - start);
        view.update();
    }

    public void rotateBy180degree() {
        Images images = model.getImages();
        BufferedImage img = images.getImage();
        if (img == null)
            return;

        long start = System.currentTimeMillis();
        img = ImageRotator.rotateBy180degree(img);
        long end = System.currentTimeMillis();

        images.setImage(img);
        Status status = model.getStatus();
        status.setProcessingTime(end - start);
        view.update();
    }

    public void showScaleDialog() {
        Images images = model.getImages();
        BufferedImage img = images.getImage();
        if (img == null)
            return;

        ScaleDialog dialog = new ScaleDialog(view, true, this);
        dialog.setVisible(true);
    }

    public void scale(double ratex, double ratey, int interpolation) {
        Images images = model.getImages();
        BufferedImage img = images.getImage();
        if (img == null)
            return;

        long start = System.currentTimeMillis();
        img = ImageScaler.scale(img, ratex, ratey, interpolation);
        long end = System.currentTimeMillis();

        images.setImage(img);
        Status status = model.getStatus();
        status.setProcessingTime(end - start);
        view.update();
    }

}
