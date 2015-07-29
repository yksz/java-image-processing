package image.editor.view;

import image.editor.config.Environment;
import image.editor.controller.EditController;
import image.editor.controller.FileController;
import image.editor.controller.HelpController;
import image.editor.controller.PreferencesController;
import image.editor.controller.ProcessController;
import image.editor.model.ImageProcessor;
import image.editor.model.Status;
import image.editor.view.menu.EditMenu;
import image.editor.view.menu.FileMenu;
import image.editor.view.menu.HelpMenu;
import image.editor.view.menu.PreferencesMenu;
import image.editor.view.menu.ProcessMenu;

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

    private static final String LOOK_AND_FEEL = Environment.LAF_METAL;
    private static final String TITLE = "Image Editor";

    private final ImageProcessor processor;

    private final ImagePanel imagePanel;
    private final StatusBar statusBar;
    private final JMenuBar menuBar;
    private final JScrollPane scrollPane;

    public View(){
        this.processor = new ImageProcessor();

        setLookAndFeel(LOOK_AND_FEEL);

        statusBar = new StatusBar(" ");
        this.add(statusBar, BorderLayout.SOUTH);

        imagePanel = new ImagePanel();
        imagePanel.setPreferredSize(new Dimension(
                Environment.CONTENTS_WIDTH, Environment.CONTENTS_HEIGHT));
        imagePanel.setBackground(Color.GRAY);

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
        BufferedImage img = processor.getImage();
        if (img != null) {
            imagePanel.setImage(img);

            Status status = processor.getStatus();
            statusBar.setStatus(img.getWidth(), img.getHeight(),
                    status.getProcessingTime(), status.getThreshold());
            status.clear();
        }
        imagePanel.repaint();
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
        menuBar.add(new FileMenu(new FileController(processor, this)));
        menuBar.add(new EditMenu(new EditController(processor, this)));
        menuBar.add(new PreferencesMenu(new PreferencesController(this)));
        menuBar.add(new ProcessMenu(new ProcessController(processor, this)));
        menuBar.add(new HelpMenu(new HelpController()));
        this.setJMenuBar(menuBar);
        return menuBar;
    }

    private JScrollPane setupScrollPane(Component viewport) {
        JScrollPane scrollPane = new JScrollPane(viewport);
        scrollPane.getVerticalScrollBar().setUnitIncrement(10);
        scrollPane.getHorizontalScrollBar().setUnitIncrement(10);
        return scrollPane;
    }

    public ImagePanel getImagePanel() {
        return imagePanel;
    }

}
