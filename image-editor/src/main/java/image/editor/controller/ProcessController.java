package image.editor.controller;

import java.awt.image.BufferedImage;

import image.editor.model.Images;
import image.editor.model.Model;
import image.editor.view.View;
import image.editor.view.dialog.ColorDialog;
import image.editor.view.dialog.ContrastDialog;
import image.editor.view.dialog.GammaCorrectionDialog;
import image.editor.view.dialog.ScaleDialog;
import image.process.ImageBinarizer;
import image.process.ImageCorrector;
import image.process.ImageFilter;
import image.process.ImageFlipper;
import image.process.ImageRotator;
import image.process.ImageScaler;

public class ProcessController {

    private final Images images;
    private final View view;

    public ProcessController(Model model, View view) {
        this.images = model.getImages();
        this.view = view;
    }

    public void binarize() {
        BufferedImage img = images.getImage();
        if (img == null)
            return;

        long start = System.currentTimeMillis();
        int threshold = ImageBinarizer.getThreshold(img);
        img = ImageBinarizer.binarize(img, threshold);
        long end = System.currentTimeMillis();

        images.setImage(img);
        view.update();
        view.updateStatusBar(end - start, threshold);
    }

    public void showContrastDialog() {
        BufferedImage img = images.getImage();
        if (img == null)
            return;

        ContrastDialog dialog = new ContrastDialog(view, true, img, this);
        dialog.setVisible(true);
    }

    public void contrast(int value) {
        BufferedImage img = images.getImage();
        if (img == null)
            return;

        long start = System.currentTimeMillis();
        img = ImageCorrector.contrast(img, value);
        long end = System.currentTimeMillis();

        images.setImage(img);
        view.update();
        view.updateStatusBar(end - start);
    }

    public void showGammaCorrectionDialog() {
        BufferedImage img = images.getImage();
        if (img == null)
            return;

        GammaCorrectionDialog dialog = new GammaCorrectionDialog(view, true, img, this);
        dialog.setVisible(true);
    }

    public void gammaCorrection(double value) {
        BufferedImage img = images.getImage();
        if (img == null)
            return;

        long start = System.currentTimeMillis();
        img = ImageCorrector.gammaCorrection(img, value);
        long end = System.currentTimeMillis();

        images.setImage(img);
        view.update();
        view.updateStatusBar(end - start);
    }

    public void grayscale() {
        BufferedImage img = images.getImage();
        if (img == null)
            return;

        long start = System.currentTimeMillis();
        img = ImageFilter.grayscale(img);
        long end = System.currentTimeMillis();

        images.setImage(img);
        view.update();
        view.updateStatusBar(end - start);
    }

    public void average() {
        BufferedImage img = images.getImage();
        if (img == null)
            return;

        long start = System.currentTimeMillis();
        img = ImageFilter.average(img);
        long end = System.currentTimeMillis();

        images.setImage(img);
        view.update();
        view.updateStatusBar(end - start);
    }

    public void sharpen() {
        BufferedImage img = images.getImage();
        if (img == null)
            return;

        long start = System.currentTimeMillis();
        img = ImageFilter.sharpen(img);
        long end = System.currentTimeMillis();

        images.setImage(img);
        view.update();
        view.updateStatusBar(end - start);
    }

    public void laplacian() {
        BufferedImage img = images.getImage();
        if (img == null)
            return;

        long start = System.currentTimeMillis();
        img = ImageFilter.laplacian(img);
        long end = System.currentTimeMillis();

        images.setImage(img);
        view.update();
        view.updateStatusBar(end - start);
    }

    public void sobel() {
        BufferedImage img = images.getImage();
        if (img == null)
            return;

        long start = System.currentTimeMillis();
        img = ImageFilter.sobel(img);
        long end = System.currentTimeMillis();

        images.setImage(img);
        view.update();
        view.updateStatusBar(end - start);
    }

    public void median() {
        BufferedImage img = images.getImage();
        if (img == null)
            return;

        long start = System.currentTimeMillis();
        img = ImageFilter.median(img, 3, 3);
        long end = System.currentTimeMillis();

        images.setImage(img);
        view.update();
        view.updateStatusBar(end - start);
    }

    public void showColorDialog() {
        BufferedImage img = images.getImage();
        if (img == null)
            return;

        ColorDialog dialog = new ColorDialog(view, true, img, this);
        dialog.setVisible(true);
    }

    public void color(int red, int green, int blue) {
        BufferedImage img = images.getImage();
        if (img == null)
            return;

        long start = System.currentTimeMillis();
        img = ImageFilter.color(img, red, green, blue);
        long end = System.currentTimeMillis();

        images.setImage(img);
        view.update();
        view.updateStatusBar(end - start);
    }

    public void flipVertical() {
        BufferedImage img = images.getImage();
        if (img == null)
            return;

        long start = System.currentTimeMillis();
        img = ImageFlipper.flipVertical(img);
        long end = System.currentTimeMillis();

        images.setImage(img);
        view.update();
        view.updateStatusBar(end - start);
    }

    public void flipHorizon() {
        BufferedImage img = images.getImage();
        if (img == null)
            return;

        long start = System.currentTimeMillis();
        img = ImageFlipper.flipHorizontal(img);
        long end = System.currentTimeMillis();

        images.setImage(img);
        view.update();
        view.updateStatusBar(end - start);
    }

    public void flipNegativePositive() {
        BufferedImage img = images.getImage();
        if (img == null)
            return;

        long start = System.currentTimeMillis();
        img = ImageFlipper.flipNegativePositive(img);
        long end = System.currentTimeMillis();

        images.setImage(img);
        view.update();
        view.updateStatusBar(end - start);
    }

    public void rotateToRightBy90degree() {
        BufferedImage img = images.getImage();
        if (img == null)
            return;

        long start = System.currentTimeMillis();
        img = ImageRotator.rotateToRightBy90degree(img);
        long end = System.currentTimeMillis();

        images.setImage(img);
        view.update();
        view.updateStatusBar(end - start);
    }

    public void rotateToLeftBy90degree() {
        BufferedImage img = images.getImage();
        if (img == null)
            return;

        long start = System.currentTimeMillis();
        img = ImageRotator.rotateToLeftBy90degree(img);
        long end = System.currentTimeMillis();

        images.setImage(img);
        view.update();
        view.updateStatusBar(end - start);
    }

    public void rotateBy180degree() {
        BufferedImage img = images.getImage();
        if (img == null)
            return;

        long start = System.currentTimeMillis();
        img = ImageRotator.rotateBy180degree(img);
        long end = System.currentTimeMillis();

        images.setImage(img);
        view.update();
        view.updateStatusBar(end - start);
    }

    public void showScaleDialog() {
        BufferedImage img = images.getImage();
        if (img == null)
            return;

        ScaleDialog dialog = new ScaleDialog(view, true, this);
        dialog.setVisible(true);
    }

    public void scale(double ratex, double ratey, int interpolation) {
        BufferedImage img = images.getImage();
        if (img == null)
            return;

        long start = System.currentTimeMillis();
        img = ImageScaler.scale(img, ratex, ratey, interpolation);
        long end = System.currentTimeMillis();

        images.setImage(img);
        view.update();
        view.updateStatusBar(end - start);
    }

}