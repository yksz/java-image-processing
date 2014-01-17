package image.editor.view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

@SuppressWarnings("serial")
public class StatusBar extends JLabel {

    private static final String INDENT = "  ";

    public StatusBar(String text) {
        super(text);
        setFont(new Font("Monospaced", Font.PLAIN, 12));
        setBackground(new Color(207, 207, 207));
    }

    public void setStatus(int width, int height) {
        setTexts(getPixelsStatus(width, height));
    }

    public void setStatus(int width, int height, long time) {
        setTexts(getPixelsStatus(width, height),
                getProcessingTimeStatus(time));
    }

    public void setStatus(int width, int height, long time, int threshold) {
        setTexts(getPixelsStatus(width, height),
                getProcessingTimeStatus(time),
                getThresholdStatus(threshold));
    }

    private void setTexts(String... strings) {
        StringBuilder text = new StringBuilder();
        for (String string : strings) {
            text.append(INDENT);
            text.append(string);
        }
        setText(text.toString());
    }

    private String getPixelsStatus(int width, int height) {
        return width + " x " + height + " pixels";
    }

    private String getProcessingTimeStatus(long time){
        int minutes = (int) (time / 1000 / 60);
        int seconds = (int) (time / 1000 % 60);
        int millis = (int) (time % 1000);

        return "[processing time: " + minutes + "m " + seconds + "." + millis + "s]";
    }

    private String getThresholdStatus(int threshold) {
        return "[threshold: " + threshold + "]";
    }

}
