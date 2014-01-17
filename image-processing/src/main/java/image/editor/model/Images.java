package image.editor.model;

import image.editor.util.Undo;

import java.awt.image.BufferedImage;

public class Images {

    private static final int MAX_UNDO_SIZE = 5;

    private BufferedImage image;
    private Undo<BufferedImage> undo;

    public Images() {
        undo = new Undo<BufferedImage>(MAX_UNDO_SIZE);
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
        undo.add(image);
    }

    public BufferedImage undo() {
        BufferedImage img = undo.undo();
        if (img == image)
            return image = undo.undo();
        else
            return image = img;
    }

    public BufferedImage redo() {
        return image = undo.redo();
    }

}
