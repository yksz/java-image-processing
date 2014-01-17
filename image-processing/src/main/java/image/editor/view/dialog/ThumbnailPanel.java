package image.editor.view.dialog;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

@SuppressWarnings("serial")
class ThumbnailPanel extends JPanel {

    private final int width;
    private final int height;
    private Image image;

    public ThumbnailPanel(int width, int height) {
        this.width = width;
        this.height = height;
        setPreferredSize(new Dimension(width, height));
        setBorder(new BevelBorder(BevelBorder.LOWERED));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (image == null)
            return;
        else
            g.drawImage(image, 0, 0, width, height, this);
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

}
