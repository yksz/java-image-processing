package image.editor.controller;

import image.editor.model.Images;
import image.editor.model.Model;
import image.editor.view.View;

public class EditController {

    private final Images images;
    private final View view;

    public EditController(Model model, View view) {
        this.images = model.getImages();
        this.view = view;
    }

    public void undo() {
        images.undo();
        view.update();
    }

    public void redo() {
        images.redo();
        view.update();
    }

}
