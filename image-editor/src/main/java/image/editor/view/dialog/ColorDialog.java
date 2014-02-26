package image.editor.view.dialog;

import image.editor.controller.ProcessController;
import image.editor.util.LayoutUtils;
import image.process.ImageFilter;

import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

@SuppressWarnings("serial")
public class ColorDialog extends JDialog implements ChangeListener, ActionListener {

    private static final String TITLE = "Color Dialog";

    private BufferedImage srcImage;
    private ProcessController controller;

    private ThumbnailPanel thumbnailPanel;
    private JSlider redSlider;
    private JSlider greenSlider;
    private JSlider blueSlider;
    private JButton okButton;
    private JButton cancelButton;

    public ColorDialog(Frame owner, boolean modal, BufferedImage image,
            ProcessController controller) {
        super(owner, modal);
        this.srcImage = image;
        this.controller = controller;

        layoutThis();

        setTitle(TITLE);
        setSize(420, 270);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        if (e.getSource() == redSlider
                || e.getSource() == greenSlider
                || e.getSource() == blueSlider) {
            BufferedImage dstImage = ImageFilter.color(srcImage,
                    redSlider.getValue(),
                    greenSlider.getValue(),
                    blueSlider.getValue());
            thumbnailPanel.setImage(dstImage);
            thumbnailPanel.repaint();
        } else {
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == okButton) {
            controller.color(redSlider.getValue(),
                    greenSlider.getValue(),
                    blueSlider.getValue());
            dispose();
        } else if (e.getSource() == cancelButton) {
            dispose();
        } else {
        }
    }

    private void layoutThis() {
        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.weightx = 100.0;
        constraints.weighty = 100.0;
        constraints.fill = GridBagConstraints.NONE;

        thumbnailPanel = new ThumbnailPanel(150, 150);
        thumbnailPanel.setImage(srcImage);
        constraints.anchor = GridBagConstraints.CENTER;
        LayoutUtils.setGridBag(this, thumbnailPanel, layout, constraints, 0, 0, 3, 3);

        JLabel redLabel = new JLabel("Red");
        constraints.anchor = GridBagConstraints.EAST;
        LayoutUtils.setGridBag(this, redLabel, layout, constraints, 3, 0, 1, 1);

        redSlider = newJSlider();
        constraints.anchor = GridBagConstraints.SOUTH;
        LayoutUtils.setGridBag(this, redSlider, layout, constraints, 4, 0, 2, 1);

        JLabel greenLabel = new JLabel("Green");
        constraints.anchor = GridBagConstraints.EAST;
        LayoutUtils.setGridBag(this, greenLabel, layout, constraints, 3, 1, 1, 1);

        greenSlider = newJSlider();
        constraints.anchor = GridBagConstraints.SOUTH;
        LayoutUtils.setGridBag(this, greenSlider, layout, constraints, 4, 1, 2, 1);

        JLabel blueLabel = new JLabel("Blue");
        constraints.anchor = GridBagConstraints.EAST;
        LayoutUtils.setGridBag(this, blueLabel, layout, constraints, 3, 2, 1, 1);

        blueSlider = newJSlider();
        constraints.anchor = GridBagConstraints.SOUTH;
        LayoutUtils.setGridBag(this, blueSlider, layout, constraints, 4, 2, 2, 1);

        okButton = new JButton("OK");
        okButton.addActionListener(this);
        constraints.anchor = GridBagConstraints.CENTER;
        LayoutUtils.setGridBag(this, okButton, layout, constraints, 4, 3, 1, 1);

        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(this);
        constraints.anchor = GridBagConstraints.CENTER;
        LayoutUtils.setGridBag(this, cancelButton, layout, constraints, 5, 3, 1, 1);

        setLayout(layout);
    }

    private JSlider newJSlider() {
        JSlider slider = new JSlider(SwingConstants.HORIZONTAL, -250, 250, 0);
        slider.setMajorTickSpacing(100);
        slider.setMinorTickSpacing(50);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.addChangeListener(this);
        return slider;
    }

}
