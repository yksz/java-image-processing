package image.process;

import java.awt.image.BufferedImage;

public final class ImageBinarizer {

    public static BufferedImage binarize(BufferedImage img, int threshold) {

        int black = getRGB(255, 0, 0, 0);
        int white = getRGB(255, 255, 255, 255);

        BufferedImage newImg = new BufferedImage(
                img.getWidth(), img.getHeight(), img.getType());
        for (int y = 0; y < img.getHeight(); y++) {
            for (int x = 0; x < img.getWidth(); x++) {
                int rgb = img.getRGB(x, y);
                int r = (rgb >> 16) & 0xff;
                int g = (rgb >>  8) & 0xff;
                int b = (rgb >>  0) & 0xff;
                int average = (r + g + b) / 3;
                if (average > threshold)
                    newImg.setRGB(x, y, white);
                else
                    newImg.setRGB(x, y, black);
            }
        }
        return newImg;
    }

    public static BufferedImage binarize(BufferedImage img) {

        int threshold = getThreshold(img);
        return binarize(img, threshold);
    }

    public static int getThreshold(BufferedImage img) {
        // discriminant analysis method

        int[] histogram = createHistogram(img, 256);
        int sum = 0;
        for (int i = 0; i < histogram.length; i++)
            sum += histogram[i];

        double[] p = new double[histogram.length]; // probability
        double[] w = new double[histogram.length]; // pixel number
        double[] u = new double[histogram.length]; // mean

        p[0] = (double) histogram[0] / sum;
        w[0] = u[0] = p[0];
        for (int i = 1; i < histogram.length; i++) {
            p[i] = (double) histogram[i] / sum;
            w[i] = w[i - 1] + p[i];
            u[i] = u[i - 1] + i * p[i];
        }

        double[] betweenClassVariance = new double[histogram.length];
        for (int i = 0; i < histogram.length; i++)
            betweenClassVariance[i] = Math.pow(u[histogram.length - 1] * w[i] - u[i], 2)
                    / (w[i] * (1 - w[i]));

        int threshold = 0;
        double max = 0;
        for (int i = 0; i < histogram.length; i++) {
            if (betweenClassVariance[i] > max) {
                max = betweenClassVariance[i];
                threshold = i;
            }
        }
        return threshold;
    }

    private static int[] createHistogram(BufferedImage img, int size) {

        int[] histogram = new int[size];
        for (int y = 0; y < img.getHeight(); y++) {
            for (int x = 0; x < img.getWidth(); x++) {
                int rgb = img.getRGB(x, y);
                int r = (rgb >> 16) & 0xff;
                int g = (rgb >>  8) & 0xff;
                int b = (rgb >>  0) & 0xff;
                int average = (r + g + b) / 3;
                histogram[average]++;
            }
        }
        return histogram;
    }

    private static int getRGB(int a, int r, int g, int b) {
        return (a << 24) | (r << 16) | (g << 8) | (b << 0);
    }

}
