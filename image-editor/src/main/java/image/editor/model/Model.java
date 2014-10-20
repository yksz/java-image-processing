package image.editor.model;

import java.awt.Color;

public class Model {

    private final Images images;
    private Color background;
    private Status status;

    public Model() {
        images = new Images();
        background = Color.GRAY;
        status = new Status();
    }

    public Images getImages() {
        return images;
    }

    public Color getBackground() {
        return background;
    }

    public void setBackground(Color background) {
        this.background = background;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

}
