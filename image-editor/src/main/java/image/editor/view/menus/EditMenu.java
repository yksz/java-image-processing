package image.editor.view.menus;

import image.editor.controller.EditController;
import image.editor.util.LayoutUtils;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

@SuppressWarnings("serial")
public class EditMenu extends JMenu implements ActionListener {

    private EditController controller;

    private JMenuItem undoMenuItem;
    private JMenuItem redoMenuItem;

    public EditMenu(EditController controller) {
        super("Edit");
        this.controller = controller;

        undoMenuItem = LayoutUtils.newJMenuItem("Undo", this, this);
        undoMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_DOWN_MASK));

        redoMenuItem = LayoutUtils.newJMenuItem("Redo", this, this);
        redoMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y, InputEvent.CTRL_DOWN_MASK));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == undoMenuItem) {
            controller.undo();
        } else if (e.getSource() == redoMenuItem) {
            controller.redo();
        } else {
        }
    }

}
