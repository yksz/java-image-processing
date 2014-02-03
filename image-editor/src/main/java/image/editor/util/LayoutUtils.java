package image.editor.util;

import java.awt.Component;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;

public class LayoutUtils {

    public static JMenuItem newJMenuItem(String name, JMenu parent,
            ActionListener listener) {
        JMenuItem menuItem = new JMenuItem(name);
        menuItem.addActionListener(listener);
        parent.add(menuItem);
        return menuItem;
    }

    public static JRadioButtonMenuItem newJRadioButtonMenuItem(String name,
            JMenu parent, ActionListener listener) {
        JRadioButtonMenuItem menuItem = new JRadioButtonMenuItem(name);
        menuItem.addActionListener(listener);
        parent.add(menuItem);
        return menuItem;
    }

    public static void setGridBag(Container container, Component component,
            GridBagLayout layout, GridBagConstraints constraints,
            int gx, int gy, int gw, int gh) {
        constraints.gridx = gx;
        constraints.gridy = gy;
        constraints.gridwidth = gw;
        constraints.gridheight = gh;
        layout.setConstraints(component, constraints);
        container.add(component);
    }

}
