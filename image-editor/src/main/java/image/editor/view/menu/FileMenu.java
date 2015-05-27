package image.editor.view.menu;

import image.editor.controller.FileController;
import image.editor.util.LayoutUtils;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

@SuppressWarnings("serial")
public class FileMenu extends JMenu implements ActionListener {

    private FileController controller;

    private JMenuItem openMenuItem;
    private JMenuItem saveMenuItem;
    private JMenuItem closeMenuItem;

    public FileMenu(FileController controller) {
        super("File");
        this.controller = controller;

        openMenuItem = LayoutUtils.newJMenuItem("Open...", this, this);
        openMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK));

        saveMenuItem = LayoutUtils.newJMenuItem("Save...", this, this);
        saveMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));

        closeMenuItem = LayoutUtils.newJMenuItem("Close", this, this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == closeMenuItem) {
            System.exit(0);
        } else if (e.getSource() == openMenuItem) {
            controller.open();
        } else if (e.getSource() == saveMenuItem) {
            controller.save();
        } else {
        }
    }

}
