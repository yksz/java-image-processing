package image.editor.controller;

import image.editor.model.ImageProcessor;
import image.editor.view.View;

public class EditController {

    private final ImageProcessor processor;
    private final View view;

    public EditController(ImageProcessor processor, View view) {
        this.processor = processor;
        this.view = view;
    }

    public void undo() {
        processor.undo();
        view.update();
    }

    public void redo() {
        processor.redo();
        view.update();
    }

}
