package image.process;

import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.util.Arrays;

public final class ImageFilter {

    private ImageFilter() {
    }

    public static BufferedImage grayscale(BufferedImage img) {
        BufferedImage newImg = new BufferedImage(
                img.getWidth(), img.getHeight(), img.getType());
        for (int y = 0; y < img.getHeight(); y++) {
            for (int x = 0; x < img.getWidth(); x++) {
                int rgb = img.getRGB(x, y);
                int a = (rgb >> 24) & 0xff;
                int r = (rgb >> 16) & 0xff;
                int g = (rgb >>  8) & 0xff;
                int b = (rgb >>  0) & 0xff;
                int average = (r + g + b) / 3;
                newImg.setRGB(x, y, getRGB(a, average, average, average));
            }
        }
        return newImg;
    }

    public static BufferedImage filter(BufferedImage img, float[] kernel,
            int kernelWidth, int kernelHeight) {
        ConvolveOp op = new ConvolveOp(new Kernel(kernelWidth, kernelHeight, kernel));
        return op.filter(img, null);
    }

    public static BufferedImage average(BufferedImage img) {
        float[] kernel = { 0.11f, 0.11f, 0.11f,
                           0.11f, 0.11f, 0.11f,
                           0.11f, 0.11f, 0.11f };

        return filter(img, kernel, 3, 3);
    }

    public static BufferedImage sharpen(BufferedImage img) {
        float[] kernel = {  0, -1,  0,
                           -1,  5, -1,
                            0, -1,  0 };

        return filter(img, kernel, 3, 3);
    }

    public static BufferedImage laplacian(BufferedImage img) {
        float[] kernel = { -1, -1, -1,
                           -1,  8, -1,
                           -1, -1, -1 };

        return filter(img, kernel, 3, 3);
    }

    public static BufferedImage sobel(BufferedImage img) {
        int[] xSobel = { -1,  0,  1,
                         -2,  0,  2,
                         -1,  0,  1 };

        int[] ySobel = {  1,  2,  1,
                          0,  0,  0,
                         -1, -2, -1 };

        int size = 3;

        BufferedImage newImg = new BufferedImage(
                img.getWidth(), img.getHeight(), img.getType());
        int edge = size / 2;
        for (int y = edge; y < img.getHeight() - edge; y++) {
            for (int x = edge; x < img.getWidth() - edge; x++) {

                int xSum = 0, ySum = 0;
                for (int fy = 0; fy < size; fy++) {
                    for (int fx = 0; fx < size; fx++) {
                        int rgb = img.getRGB(x + fx - edge, y + fy - edge);
                        int r = (rgb >> 16) & 0xff;
                        int g = (rgb >> 8) & 0xff;
                        int b = (rgb >> 0) & 0xff;
                        int average = (r + g + b) / 3;

                        xSum += average * xSobel[fx + fy * size];
                        ySum += average * ySobel[fx + fy * size];
                    }
                }

                int sum = (int) (Math.sqrt(xSum * xSum + ySum * ySum));
                sum = limit(sum, 0, 255);
                newImg.setRGB(x, y, getRGB(255, sum, sum, sum));
            }
        }
        return newImg;
    }

    public static BufferedImage median(BufferedImage img,
            int kernelWidth, int kernelHeight) {
        BufferedImage newImg = new BufferedImage(
                img.getWidth(), img.getHeight(), img.getType());
        int edgex = kernelWidth / 2;
        int edgey = kernelHeight / 2;
        for (int y = edgey; y < img.getHeight() - edgey; y++) {
            for (int x = edgex; x < img.getWidth() - edgex; x++) {

                int[] rArray = new int[kernelWidth * kernelHeight];
                int[] gArray = new int[kernelWidth * kernelHeight];
                int[] bArray = new int[kernelWidth * kernelHeight];
                for (int fy = 0; fy < kernelHeight; fy++) {
                    for (int fx = 0; fx < kernelWidth; fx++) {
                        int rgb = img.getRGB(x + fx - edgex, y + fy - edgey);
                        int r = (rgb >> 16) & 0xff;
                        int g = (rgb >> 8) & 0xff;
                        int b = (rgb >> 0) & 0xff;
                        rArray[fx + fy * kernelWidth] = r;
                        gArray[fx + fy * kernelWidth] = g;
                        bArray[fx + fy * kernelWidth] = b;
                    }
                }

                int r = getMedian(rArray);
                int g = getMedian(gArray);
                int b = getMedian(bArray);
                newImg.setRGB(x, y, getRGB(255, r, g, b));
            }
        }
        return newImg;
    }

    private static int getMedian(int[] data) {
        Arrays.sort(data);
        return data[data.length / 2];
    }

    public static BufferedImage color(BufferedImage img, int red, int green, int blue) {
        BufferedImage newImg = new BufferedImage(
                img.getWidth(), img.getHeight(), img.getType());
        for (int y = 0; y < img.getHeight(); y++) {
            for (int x = 0; x < img.getWidth(); x++) {
                int rgb = img.getRGB(x, y);
                int a = (rgb >> 24) & 0xff;
                int r = (rgb >> 16) & 0xff;
                int g = (rgb >>  8) & 0xff;
                int b = (rgb >>  0) & 0xff;
                r = limit(r + red,   0, 255);
                g = limit(g + green, 0, 255);
                b = limit(b + blue,  0, 255);
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
