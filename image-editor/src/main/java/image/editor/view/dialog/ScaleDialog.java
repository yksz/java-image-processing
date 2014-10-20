package image.editor.view.dialog;

import image.editor.controller.ProcessController;
import image.editor.util.LayoutUtils;
import image.processing.ImageScaler;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

@SuppressWarnings("serial")
public class ScaleDialog extends JDialog implements ChangeListener, ActionListener {

    private static final String TITLE = "Scale Dialog";

    private double ratex = 100;
    private double ratey = 100;
    private String interpolation;

    private ProcessController controller;

    private JPanel sizePanel;
    private JSpinner ratexSpinner;
    private JSpinner rateySpinner;
    private SpinnerNumberModel ratexSpinnerNumberModel;
    private SpinnerNumberModel rateySpinnerNumberModel;
    private JComboBox comboBox;
    private JButton okButton;
    private JButton cancelButton;

    public ScaleDialog(Frame owner, boolean modal, ProcessController controller) {
        super(owner, modal);
        this.controller = controller;

        layoutThis();

        setTitle(TITLE);
        setSize(320, 180);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        if (e.getSource() == ratexSpinner) {
            Integer integer = (Integer) ratexSpinnerNumberModel.getValue();
            ratex = integer.doubleValue();
        } else if (e.getSource() == rateySpinner) {
            Integer integer = (Integer) rateySpinnerNumberModel.getValue();
            ratey = integer.doubleValue();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == comboBox) {
            interpolation = (String) comboBox.getSelectedItem();
        } else if (e.getSource() == okButton) {
            int mode;
            if (interpolation.equals("Nearest Neighbor"))
                mode = ImageScaler.TYPE_NEAREST_NEIGHBOR;
            else if (interpolation.equals("Bilinear"))
                mode = ImageScaler.TYPE_BILINEAR;
            else if (interpolation.equals("Bicubic"))
                mode = ImageScaler.TYPE_BICUBIC;
            else
                throw new AssertionError("Unknown interpolation: " + interpolation);
            controller.scale(ratex / 100 , ratey / 100, mode);
            dispose();
        } else if (e.getSource() == cancelButton) {
            dispose();
        }
    }

    private void layoutThis() {
        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.weightx = 100.0;
        constraints.weighty = 100.0;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.fill = GridBagConstraints.NONE;

        setupSizePanel();
        LayoutUtils.setGridBag(this, sizePanel, layout, constraints, 0, 0, 2, 1);

        JLabel label = new JLabel("Interpolation:");
        constraints.anchor = GridBagConstraints.EAST;
        LayoutUtils.setGridBag(this, label, layout, constraints, 0, 1, 1, 1);

        String[] comboData = { "Nearest Neighbor", "Bilinear", "Bicubic" };
        comboBox = new JComboBox(comboData);
        comboBox.setSelectedIndex(2);
        interpolation = comboData[2];
        constraints.anchor = GridBagConstraints.CENTER;
        LayoutUtils.setGridBag(this, comboBox, layout, constraints, 1, 1, 1, 1);

        okButton = new JButton("OK");
        okButton.addActionListener(this);
        constraints.anchor = GridBagConstraints.EAST;
        LayoutUtils.setGridBag(this, okButton, layout, constraints, 0, 2, 1, 1);

        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(this);
        constraints.anchor = GridBagConstraints.CENTER;
        LayoutUtils.setGridBag(this, cancelButton, layout, constraints, 1, 2, 1, 1);

        setLayout(layout);
    }

    private void setupSizePanel() {
        sizePanel = new JPanel();
        LineBorder lineBorder = new LineBorder(Color.gray);
        sizePanel.setBorder(new TitledBorder(lineBorder, "Size"));
        sizePanel.setPreferredSize(new Dimension(270, 60));

        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.weightx = 100.0;
        constraints.weighty = 100.0;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.fill = GridBagConstraints.NONE;

        JLabel label1 = new JLabel("width:");
        LayoutUtils.setGridBag(sizePanel, label1, layout, constraints, 0, 0, 1, 1);

        ratexSpinnerNumberModel = new SpinnerNumberModel(100, 1, 200, 1);
        ratexSpinner = new JSpinner(ratexSpinnerNumberModel);
        ratexSpinner.addChangeListener(this);
        LayoutUtils.setGridBag(sizePanel, ratexSpinner, layout, constraints, 1, 0, 1, 1);

        JLabel label2 = new JLabel("%");
        LayoutUtils.setGridBag(sizePanel, label2, layout, constraints, 2, 0, 1, 1);

        JLabel label3 = new JLabel(" x ");
        LayoutUtils.setGridBag(sizePanel, label3, layout, constraints, 3, 0, 1, 1);

        JLabel label4 = new JLabel("height:");
        LayoutUtils.setGridBag(sizePanel, label4, layout, constraints, 4, 0, 1, 1);

        rateySpinnerNumberModel = new SpinnerNumberModel(100, 1, 200, 1);
        rateySpinner = new JSpinner(rateySpinnerNumberModel);
        rateySpinner.addChangeListener(this);
        LayoutUtils.setGridBag(sizePanel, rateySpinner, layout, constraints, 5, 0, 1, 1);

        JLabel label5 = new JLabel("%");
        LayoutUtils.setGridBag(sizePanel, label5, layout, constraints, 6, 0, 1, 1);
    }

}
