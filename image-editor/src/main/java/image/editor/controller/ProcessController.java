package image.editor.controller;

import image.editor.model.ImageProcessor;
import image.editor.view.View;
import image.editor.view.dialog.ColorDialog;
import image.editor.view.dialog.ContrastDialog;
import image.editor.view.dialog.GammaCorrectionDialog;
import image.editor.view.dialog.ScaleDialog;

import java.awt.image.BufferedImage;

public class ProcessController {

    private final ImageProcessor processor;
    private final View view;

    public ProcessController(ImageProcessor processor, View view) {
        this.processor = processor;
        this.view = view;
    }

    public void binarize() {
        processor.binarize();
        view.update();
    }

    public void showContrastDialog() {
        BufferedImage img = processor.getImage();
        if (img == null)
            return;

        ContrastDialog dialog = new ContrastDialog(view, true, img, this);
        dialog.setVisible(true);
    }

    public void contrast(int value) {
        processor.contrast(value);
        view.update();
    }

    public void showGammaCorrectionDialog() {
        BufferedImage img = processor.getImage();
        if (img == null)
            return;

        GammaCorrectionDialog dialog = new GammaCorrectionDialog(view, true, img, this);
        dialog.setVisible(true);
    }

    public void gammaCorrection(double value) {
        processor.gammaCorrection(value);
        view.update();
    }

    public void grayscale() {
        processor.grayscale();
        view.update();
    }

    public void average() {
        processor.average();
        view.update();
    }

    public void sharpen() {
        processor.sharpen();
        view.update();
    }

    public void laplacian() {
        processor.laplacian();
        view.update();
    }

    public void sobel() {
        processor.sobel();
        view.update();
    }

    public void median() {
        processor.median();
        view.update();
    }

    public void showColorDialog() {
        BufferedImage img = processor.getImage();
        if (img == null)
            return;

        ColorDialog dialog = new ColorDialog(view, true, img, this);
        dialog.setVisible(true);
    }

    public void color(int red, int green, int blue) {
        processor.color(red, green, blue);
        view.update();
    }

    public void flipVertical() {
        processor.flipVertical();
        view.update();
    }

    public void flipHorizon() {
        processor.flipHorizon();
        view.update();
    }

    public void flipNegativePositive() {
        processor.flipNegativePositive();
        view.update();
    }

    public void rotateToRightBy90degree() {
        processor.rotateToRightBy90degree();
        view.update();
    }

    public void rotateToLeftBy90degree() {
        processor.rotateToLeftBy90degree();
        view.update();
    }

    public void rotateBy180degree() {
        processor.rotateBy180degree();
        view.update();
    }

    public void showScaleDialog() {
        BufferedImage img = processor.getImage();
        if (img == null)
            return;

        ScaleDialog dialog = new ScaleDialog(view, true, this);
        dialog.setVisible(true);
    }

    public void scale(double ratex, double ratey, int interpolation) {
        processor.scale(ratex, ratey, interpolation);
        view.update();
    }

}
