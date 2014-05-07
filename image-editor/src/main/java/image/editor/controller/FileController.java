package image.editor.controller;

import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import image.editor.model.Images;
import image.editor.model.Model;
import image.editor.view.View;

public class FileController {

    private static final String DEFAULT_FORMAT = "png";

    private final Images images;
    private final View view;

    public FileController(Model model, View view) {
        this.images = model.getImages();
        this.view = view;
    }

    public void open() {
        BufferedImage img = null;
        try {
            img = openImageByDialog();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (img == null)
            return;

        images.setImage(img);
        view.update();
    }

    public void save() {
        BufferedImage img = images.getImage();
        if (img == null)
            return;

        try {
            saveImageByDialog(img);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private BufferedImage openImageByDialog()
            throws IOException {
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

    public void saveImageByDialog(BufferedImage img)
            throws IOException {
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
