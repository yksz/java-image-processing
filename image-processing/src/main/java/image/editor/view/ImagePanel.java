package image.editor.view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ImagePanel extends JPanel {

    private BufferedImage image;

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        if (image == null)
            return;
        else
            g.drawImage(image, getInsets().left, getInsets().top, this);
    }

    public BufferedImage getImage(){
        return image;
    }

    public void setImage(BufferedImage image){
        if (image == null)
            return;

        this.image = image;
        setPreferredSize(new Dimension(image.getWidth(this), image.getHeight(this)));
    }

}
