package image.editor.controller;

import image.editor.model.Images;
import image.editor.model.Model;
import image.editor.view.View;

public class EditController {

    private final Model model;
    private final View view;

    public EditController(Model model, View view) {
        this.model = model;
        this.view = view;
    }

    public void undo() {
        Images images = model.getImages();
        images.undo();
        view.update();
    }

    public void redo() {
        Images images = model.getImages();
        images.redo();
        view.update();
    }

}
