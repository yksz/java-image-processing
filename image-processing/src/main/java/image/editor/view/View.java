package image.editor.view;

import image.editor.config.Environment;
import image.editor.controller.EditController;
import image.editor.controller.FileController;
import image.editor.controller.HelpController;
import image.editor.controller.PreferencesController;
import image.editor.controller.ProcessController;
import image.editor.model.Background;
import image.editor.model.Images;
import image.editor.model.Model;
import image.editor.view.menus.EditMenu;
import image.editor.view.menus.FileMenu;
import image.editor.view.menus.HelpMenu;
import image.editor.view.menus.PreferencesMenu;
import image.editor.view.menus.ProcessMenu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

@SuppressWarnings("serial")
public class View extends JFrame {

    private static final String TITLE = "Image Editor";

    private Model model;
    private Images images;
    private Background background;

    private ImagePanel imagePanel;
    private StatusBar statusBar;

    private JMenuBar menuBar;
    private JScrollPane scrollPane;

    public View(Model model){
        this.model = model;
        this.images = model.getImages();
        this.background = model.getBackground();

        setLookAndFeel(Environment.LAF_WINDOWS);

        statusBar = new StatusBar(" ");
        this.add(statusBar, BorderLayout.SOUTH);

        imagePanel = new ImagePanel();
        imagePanel.setPreferredSize(new Dimension(
                Environment.CONTENTS_WIDTH, Environment.CONTENTS_HEIGHT));
        imagePanel.setBackground(background.getColor());

        scrollPane = setupScrollPane(imagePanel);
        this.add(scrollPane, BorderLayout.CENTER);

        menuBar = setupMenuBar();
        this.setJMenuBar(menuBar);

        this.setTitle(TITLE);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void update() {
        BufferedImage img = images.getImage();
        if (img != null)
            imagePanel.setImage(img);

        Color color = background.getColor();
        if (color != null)
            imagePanel.setBackground(background.getColor());

        imagePanel.repaint();
    }

    public void updateStatusBar() {
        BufferedImage img = images.getImage();
        if (img != null)
            statusBar.setStatus(img.getWidth(), img.getHeight());
    }

    public void updateStatusBar(long time) {
        BufferedImage img = images.getImage();
        if (img != null)
            statusBar.setStatus(img.getWidth(), img.getHeight(), time);
    }

    public void updateStatusBar(long time, int threshold) {
        BufferedImage img = images.getImage();
        if (img != null)
            statusBar.setStatus(img.getWidth(), img.getHeight(), time, threshold);
    }

    private void setLookAndFeel(String className) {
        try {
            UIManager.setLookAndFeel(className);
            SwingUtilities.updateComponentTreeUI(this);
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    private JMenuBar setupMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(new FileMenu(new FileController(model, this)));
        menuBar.add(new EditMenu(new EditController(model, this)));
        menuBar.add(new PreferencesMenu(new PreferencesController(model, this)));
        menuBar.add(new ProcessMenu(new ProcessController(model, this)));
        menuBar.add(new HelpMenu(new HelpController(model, this)));
        this.setJMenuBar(menuBar);
        return menuBar;
    }

    private JScrollPane setupScrollPane(Component viewport) {
        JScrollPane scrollPane = new JScrollPane(viewport);
        scrollPane.getVerticalScrollBar().setUnitIncrement(10);
        scrollPane.getHorizontalScrollBar().setUnitIncrement(10);
        return scrollPane;
    }

}
