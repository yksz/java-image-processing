package image.editor.controller;

import image.editor.view.View;

import java.awt.Color;

import javax.swing.JColorChooser;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class PreferencesController {

    private final View view;

    public PreferencesController(View view) {
        this.view = view;
    }

    public void selectBackgroundColor() {
        Color color = JColorChooser.showDialog(view, "Select background color",
                view.getImagePanel().getBackground());
        if (color == null)
            return;

        view.getImagePanel().setBackground(color);
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
