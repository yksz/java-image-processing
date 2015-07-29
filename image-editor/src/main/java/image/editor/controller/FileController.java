package image.editor.controller;

import image.editor.model.ImageProcessor;
import image.editor.view.View;

import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class FileController {

    private static final String DEFAULT_FORMAT = "png";

    private final ImageProcessor processor;
    private final View view;

    public FileController(ImageProcessor processor, View view) {
        this.processor = processor;
        this.view = view;
    }

    public void open() {
        BufferedImage img = null;
        try {
            img = openImageWithDialog();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (img == null)
            return;

        processor.setImage(img);
        view.update();
    }

    public void save() {
        BufferedImage img = processor.getImage();
        if (img == null)
            return;

        try {
            saveImageWithDialog(img);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private BufferedImage openImageWithDialog() throws IOException {
        FileDialog dialog = new FileDialog(new Frame(), "Open", FileDialog.LOAD);
        dialog.setVisible(true);
        String filename = dialog.getDirectory() + dialog.getFile();

        if (dialog.getFile() != null) {
            try {
                return ImageIO.read(new File(filename));
            } finally {
                dialog.dispose();
            }
        } else {
            dialog.dispose();
        }
        return null;
    }

    public void saveImageWithDialog(BufferedImage img) throws IOException {
        FileDialog dialog = new FileDialog(new Frame(), "Save", FileDialog.SAVE);
        dialog.setVisible(true);
        String filename = dialog.getDirectory() + dialog.getFile();

        if (dialog.getFile() != null) {
            String format = getExtension(filename);
            if (format == null) {
                format = DEFAULT_FORMAT;
                filename = filename + "." + format;
            }
            try {
                ImageIO.write(img, format, new File(filename));
            } finally {
                dialog.dispose();
            }
        } else {
            dialog.dispose();
        }
    }

    private static String getExtension(String filename) {
        int index = filename.lastIndexOf(".");
        if (index == -1)
            return null;
        return filename.substring(index + 1);
    }

}
