package image.editor.controller;

import java.awt.Color;

import image.editor.model.Background;
import image.editor.model.Model;
import image.editor.view.View;

import javax.swing.JColorChooser;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class PreferencesController {

    private final Background background;
    private final View view;

    public PreferencesController(Model model, View view) {
        this.background = model.getBackground();
        this.view = view;
    }

    public void selectBackgroundColor() {
        Color color = JColorChooser.showDialog(view, "Select background color",
                background.getColor());
        if (color == null)
            return;

        background.setColor(color);
        view.update();
    }

    public void setLookAndFeel(String className) {
        try {
            UIManager.setLookAndFeel(className);
            SwingUtilities.updateComponentTreeUI(view);
        } catch(Exception e){
            e.printStackTrace();
        }
    }

}
