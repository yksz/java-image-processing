package image.editor.view.menu;

import image.editor.controller.ProcessController;
import image.editor.view.LayoutUtils;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

@SuppressWarnings("serial")
public class ProcessMenu extends JMenu implements ActionListener {

    private ProcessController controller;

    private JMenuItem binaraizeMenuItem;
    private JMenu correctionMenu;
    private JMenuItem contrastMenuItem;
    private JMenuItem gammaCorrectionMenuItem;
    private JMenu filterMenu;
    private JMenuItem grayscaleMenuItem;
    private JMenuItem averageMenuItem;
    private JMenuItem sharpenMenuItem;
    private JMenuItem laplacianMenuItem;
    private JMenuItem sobelMenuItem;
    private JMenuItem medianMenuItem;
    private JMenuItem colorMenuItem;
    private JMenu flipMenu;
    private JMenuItem flipVerticalMenuItem;
    private JMenuItem flipHorizonMenuItem;
    private JMenuItem negativePositiveMenuItem;
    private JMenu rotateMenu;
    private JMenuItem rotateToRightMenuItem;
    private JMenuItem rotateToLeftMenuItem;
    private JMenuItem rotateBy180MenuItem;
    private JMenuItem scaleMenuItem;

    public ProcessMenu(ProcessController controller) {
        super("Process");
        this.controller = controller;

        binaraizeMenuItem = LayoutUtils.newJMenuItem("Binaraize", this, this);
        layoutCorrectionMenu();
        layoutFilterMenu();
        layoutFlipMenu();
        layoutRotateMenu();
        scaleMenuItem = LayoutUtils.newJMenuItem("Scale...", this, this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == binaraizeMenuItem) {
            controller.binarize();
        } else if (e.getSource() == contrastMenuItem) {
            controller.showContrastDialog();
        } else if (e.getSource() == gammaCorrectionMenuItem) {
            controller.showGammaCorrectionDialog();
        } else if (e.getSource() == grayscaleMenuItem) {
            controller.grayscale();
        } else if (e.getSource() == averageMenuItem) {
            controller.average();
        } else if (e.getSource() == sharpenMenuItem) {
            controller.sharpen();
        } else if (e.getSource() == laplacianMenuItem) {
            controller.laplacian();
        } else if (e.getSource() == sobelMenuItem) {
            controller.sobel();
        } else if (e.getSource() == medianMenuItem) {
            controller.median();
        } else if (e.getSource() == colorMenuItem) {
            controller.showColorDialog();
        } else if (e.getSource() == flipVerticalMenuItem) {
            controller.flipVertical();
        } else if (e.getSource() == flipHorizonMenuItem) {
            controller.flipHorizon();
        } else if (e.getSource() == negativePositiveMenuItem) {
            controller.flipNegativePositive();
        } else if (e.getSource() == rotateToRightMenuItem) {
            controller.rotateToRightBy90degree();
        } else if (e.getSource() == rotateToLeftMenuItem) {
            controller.rotateToLeftBy90degree();
        } else if (e.getSource() == rotateBy180MenuItem) {
            controller.rotateBy180degree();
        } else if (e.getSource() == scaleMenuItem) {
            controller.showScaleDialog();
        } else {
        }
    }

    private void layoutCorrectionMenu() {
        correctionMenu = new JMenu("Correction");
        add(correctionMenu);

        contrastMenuItem = LayoutUtils.newJMenuItem("Contrast...", correctionMenu, this);
        gammaCorrectionMenuItem = LayoutUtils.newJMenuItem("Gamma correction...", correctionMenu, this);
    }

    private void layoutFilterMenu() {
        filterMenu = new JMenu("Filter");
        add(filterMenu);

        grayscaleMenuItem = LayoutUtils.newJMenuItem("Grayscale", filterMenu, this);
        averageMenuItem = LayoutUtils.newJMenuItem("Average filter", filterMenu, this);
        sharpenMenuItem = LayoutUtils.newJMenuItem("Sharpen filter", filterMenu, this);
        laplacianMenuItem = LayoutUtils.newJMenuItem("Laplacian filter", filterMenu, this);
        sobelMenuItem = LayoutUtils.newJMenuItem("Sobel filter", filterMenu, this);
        medianMenuItem = LayoutUtils.newJMenuItem("Median filter", filterMenu, this);
        colorMenuItem = LayoutUtils.newJMenuItem("Coler filter...", filterMenu, this);
    }

    private void layoutFlipMenu() {
        flipMenu = new JMenu("Flip");
        add(flipMenu);

        flipVerticalMenuItem = LayoutUtils.newJMenuItem("Flip vertical", flipMenu, this);
        flipHorizonMenuItem = LayoutUtils.newJMenuItem("Flip horizon", flipMenu, this);
        negativePositiveMenuItem = LayoutUtils.newJMenuItem("Negative-Positive", flipMenu, this);
    }

    private void layoutRotateMenu() {
        rotateMenu = new JMenu("Rotate");
        add(rotateMenu);

        rotateToRightMenuItem = LayoutUtils.newJMenuItem("Rotate to right by 90 degree", rotateMenu, this);
        rotateToLeftMenuItem = LayoutUtils.newJMenuItem("Rotate to left by 90 degree", rotateMenu, this);
        rotateBy180MenuItem = LayoutUtils.newJMenuItem("Rotate by 180 degree", rotateMenu, this);
    }

}
