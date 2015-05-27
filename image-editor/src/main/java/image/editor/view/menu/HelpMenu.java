package image.editor.view.menu;

import image.editor.controller.HelpController;
import image.editor.util.LayoutUtils;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

@SuppressWarnings("serial")
public class HelpMenu extends JMenu implements ActionListener {

    private HelpController controller;

    private JMenuItem aboutMenuItem;

    public HelpMenu(HelpController controller){
        super("Help");
        this.controller = controller;

        aboutMenuItem = LayoutUtils.newJMenuItem("About", this, this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == aboutMenuItem) {
            controller.showAbout();
        }
    }

}
