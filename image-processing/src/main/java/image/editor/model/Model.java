package image.editor.model;

import java.awt.Color;

public class Model {

    private final Images images;
    private final Background background;

    public Model() {
        images = new Images();
        background = new Background(Color.GRAY);
    }

    public Images getImages() {
        return images;
    }

    public Background getBackground() {
        return background;
    }

}
