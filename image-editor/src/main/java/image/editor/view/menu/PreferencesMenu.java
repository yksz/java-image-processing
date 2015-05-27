package image.editor.view.menu;

import image.editor.config.Environment;
import image.editor.controller.PreferencesController;
import image.editor.util.LayoutUtils;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;

@SuppressWarnings("serial")
public class PreferencesMenu extends JMenu implements ActionListener {

    private PreferencesController controller;

    private JMenuItem backgroundMenuItem;
    private JMenu lookAndFeelMenu;
    private JRadioButtonMenuItem metalMenuItem;
    private JRadioButtonMenuItem motifMenuItem;
    private JRadioButtonMenuItem windowsMenuItem;
    private JRadioButtonMenuItem windowsClassicMenuItem;

    public PreferencesMenu(PreferencesController controller) {
        super("Preferences");
        this.controller = controller;

        backgroundMenuItem = LayoutUtils.newJMenuItem("Background", this, this);

        layoutLookAndFeelMenu();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backgroundMenuItem) {
            controller.selectBackgroundColor();
        } else if (e.getSource() == metalMenuItem) {
            controller.setLookAndFeel(Environment.LAF_METAL);
        } else if (e.getSource() == motifMenuItem) {
            controller.setLookAndFeel(Environment.LAF_MOTIF);
        } else if (e.getSource() == windowsMenuItem) {
            controller.setLookAndFeel(Environment.LAF_WINDOWS);
        } else if (e.getSource() == windowsClassicMenuItem) {
            controller.setLookAndFeel(Environment.LAF_WINDOWS_CLASSIC);
        } else {
        }
    }

    private void layoutLookAndFeelMenu() {
        lookAndFeelMenu = new JMenu("Look and Feel");
        add(lookAndFeelMenu);
        ButtonGroup group = new ButtonGroup();

        metalMenuItem = LayoutUtils.newJRadioButtonMenuItem("Metal",
                lookAndFeelMenu, this);
        metalMenuItem.setSelected(true);
        group.add(metalMenuItem);

        motifMenuItem = LayoutUtils.newJRadioButtonMenuItem("Motif",
                lookAndFeelMenu, this);
        group.add(motifMenuItem);

        windowsMenuItem = LayoutUtils.newJRadioButtonMenuItem("Windows",
                lookAndFeelMenu, this);
        group.add(windowsMenuItem);

        windowsClassicMenuItem = LayoutUtils.newJRadioButtonMenuItem("Windows Classic",
                lookAndFeelMenu, this);
        group.add(windowsClassicMenuItem);
    }

}
