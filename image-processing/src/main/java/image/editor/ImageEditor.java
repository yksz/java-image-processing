package image.editor;

import image.editor.model.Model;
import image.editor.view.View;

public class ImageEditor {

    private View view;

    public ImageEditor() {
        Model model = new Model();
        view = new View(model);
    }

    public void show() {
        view.setVisible(true);
    }

    public static void main(String args[]) {
        ImageEditor editor = new ImageEditor();
        editor.show();
    }

}
