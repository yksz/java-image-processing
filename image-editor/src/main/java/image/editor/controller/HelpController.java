package image.editor.controller;

import javax.swing.JOptionPane;

public class HelpController {

    private static final String INDENT = "    ";

    public HelpController() {
    }

    public void showAbout() {
        String title = "About";
        String message = "Image Editor\n" + INDENT + "- Version 1.0";
        String options[] = { INDENT + "OK" + INDENT };

        JOptionPane.showOptionDialog(null,
                message,
                title,
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                options,
                options[0]);
    }

}
