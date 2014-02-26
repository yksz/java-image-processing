package image.editor.view.dialog;

import image.editor.controller.ProcessController;
import image.editor.util.LayoutUtils;
import image.process.ImageCorrector;

import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.Dictionary;
import java.util.Hashtable;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

@SuppressWarnings("serial")
public class GammaCorrectionDialog extends JDialog implements ChangeListener, ActionListener {

    private static final String TITLE = "Gamma correction Dialog";

    private BufferedImage srcImage;
    private ProcessController controller;

    private ThumbnailPanel thumbnailPanel;
    private JSlider slider;
    private JButton okButton;
    private JButton cancelButton;

    public GammaCorrectionDialog(Frame owner, boolean modal,
            BufferedImage image, ProcessController controller) {
        super(owner, modal);
        this.srcImage = image;
        this.controller = controller;

        layoutThis();

        setTitle(TITLE);
        setSize(400, 200);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        if (e.getSource() == slider) {
            BufferedImage dstImage = ImageCorrector.gammaCorrection(srcImage,
                    slider.getValue() / 10.0);
            thumbnailPanel.setImage(dstImage);
            thumbnailPanel.repaint();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == okButton) {
            controller.gammaCorrection(slider.getValue() / 10.0);
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

        slider = newJSlider();
        constraints.anchor = GridBagConstraints.SOUTH;
        LayoutUtils.setGridBag(this, slider, layout, constraints, 3, 1, 2, 1);

        okButton = new JButton("OK");
        okButton.addActionListener(this);
        constraints.anchor = GridBagConstraints.CENTER;
        LayoutUtils.setGridBag(this, okButton, layout, constraints, 3, 2, 1, 1);

        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(this);
        constraints.anchor = GridBagConstraints.CENTER;
        LayoutUtils.setGridBag(this, cancelButton, layout, constraints, 4, 2, 1, 1);

        setLayout(layout);
    }

    private JSlider newJSlider() {
        Dictionary<Integer, JLabel> dictionary = new Hashtable<Integer, JLabel>();
        dictionary.put(new Integer(5), new JLabel("0.5"));
        dictionary.put(new Integer(10), new JLabel("1.0"));
        dictionary.put(new Integer(15), new JLabel("1.5"));
        dictionary.put(new Integer(20), new JLabel("2.0"));

        JSlider slider = new JSlider(SwingConstants.HORIZONTAL, 5, 20, 10);
        slider.setMajorTickSpacing(5);
        slider.setMinorTickSpacing(1);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setLabelTable(dictionary);
        slider.addChangeListener(this);
        return slider;
    }

}
