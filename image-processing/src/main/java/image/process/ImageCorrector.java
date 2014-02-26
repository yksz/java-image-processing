package image.process;

import java.awt.image.BufferedImage;

public final class ImageCorrector {

    private ImageCorrector() {
    }

    public static BufferedImage contrast(BufferedImage img, int value) {
        // y = ax + b
        //
        // a: (contrast + 127.5) / 127.5
        // b: -contrast

        int[] conversionTable = new int[256];
        for (int i = 0; i < conversionTable.length; i++) {
            conversionTable[i] = (int) ((value + 127.5) / 127.5 * i - value);
            conversionTable[i] = limit(conversionTable[i], 0, 255);
        }
        return convert(img, conversionTable);
    }

    public static BufferedImage gammaCorrection(BufferedImage img, double value) {
        // y = (x / 255)^(1 / gamma) * 255

        int[] conversionTable = new int[256];
        for (int i = 0; i < conversionTable.length; i++)
            conversionTable[i] = (int) (Math.pow(i / 255.0, 1 / value) * 255);
        return convert(img, conversionTable);
    }

    private static BufferedImage convert(BufferedImage img, int[] table) {
        BufferedImage newImg = new BufferedImage(
                img.getWidth(), img.getHeight(), img.getType());
        for (int y = 0; y < img.getHeight(); y++) {
            for (int x = 0; x < img.getWidth(); x++) {
                int rgb = img.getRGB(x, y);
                int a = (rgb >> 24) & 0xff;
                int r = (rgb >> 16) & 0xff;
                int g = (rgb >>  8) & 0xff;
                int b = (rgb >>  0) & 0xff;
                r = table[r];
                g = table[g];
                b = table[b];
                newImg.setRGB(x, y, getRGB(a, r, g, b));
            }
        }
        return newImg;
    }

    private static int getRGB(int a, int r, int g, int b) {
        return (a << 24) | (r << 16) | (g << 8) | (b << 0);
    }

    public static int limit(int value, int min, int max) {
        if (value < min)
            return min;
        else if (value > max)
            return max;
        else
            return value;
    }

}
