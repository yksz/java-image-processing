package image.editor;

import image.editor.view.View;

public class ImageEditor {

    private final View view;

    public ImageEditor() {
        view = new View();
    }

    public void show() {
        view.setVisible(true);
    }

    public void hide() {
        view.setVisible(false);
    }

}
