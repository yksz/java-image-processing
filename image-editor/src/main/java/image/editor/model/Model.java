package image.editor.model;

import java.awt.Color;

public class Model {

    private final Images images;
    private final Background background;
    private final Status status;

    public Model() {
        images = new Images();
        background = new Background(Color.GRAY);
        status = new Status();
    }

    public Images getImages() {
        return images;
    }

    public Background getBackground() {
        return background;
    }

    public Status getStatus() {
        return status;
    }

}
