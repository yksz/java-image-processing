import image.process.ImageBinarizer;
import image.process.ImageCorrector;
import image.process.ImageFilter;
import image.process.ImageFlipper;
import image.process.ImageRotator;
import image.process.ImageScaler;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;

import javax.imageio.ImageIO;

public class ImageProcessingSample {

    private static final String RESOURCE_DIRECTORY = "src/test/resources/";
    private static final String FILENAME = "lena.jpg";
    private static final String FORMAT = "jpg";

    private static final String INPUT_PATH = RESOURCE_DIRECTORY + "in/" + FILENAME;
    private static final String OUTPUT_PATH = RESOURCE_DIRECTORY + "out/" + FILENAME;

    public static void main(String[] args) throws IOException{
        long startTime = System.currentTimeMillis();
        System.out.println("[before]");
        showMemoryInfo();

        BufferedImage img = ImageIO.read(new File(INPUT_PATH));
        img = process(img);
        ImageIO.write(img, FORMAT, new File(OUTPUT_PATH));

        System.out.println("[after]");
        showMemoryInfo();
        long endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime + "[ms]");
    }

    private static BufferedImage process(BufferedImage img) {
        img = ImageBinarizer.binarize(img, 127);
        img = ImageBinarizer.binarize(img);

        img = ImageCorrector.contrast(img, 100);
        img = ImageCorrector.gammaCorrection(img, 20);

        img = ImageFilter.grayscale(img);
        img = ImageFilter.average(img);
        img = ImageFilter.sharpen(img);
        img = ImageFilter.laplacian(img);
        img = ImageFilter.sobel(img);
        img = ImageFilter.median(img, 3, 3);

        img = ImageFlipper.flipVertical(img);
        img = ImageFlipper.flipNegativePositive(img);

        img = ImageRotator.rotate(img, 20);
        img = ImageRotator.rotateToRightBy90degree(img);

        img = ImageScaler.scale(img, 2.0, 2.0, ImageScaler.TYPE_BICUBIC);
        img = ImageScaler.scale(img, 1024, 1024, ImageScaler.TYPE_BICUBIC);

        return img;
    }

    private static void showMemoryInfo() {
        MemoryMXBean memoryBean = ManagementFactory.getMemoryMXBean();
        MemoryUsage memoryUsage = memoryBean.getHeapMemoryUsage();

        System.out.println("++ Memory Info ++");
        System.out.println(" - Init:      " + memoryUsage.getInit() / 1024 + "[KB]");
        System.out.println(" - Used:      " + memoryUsage.getUsed() / 1024 + "[KB]");
        System.out.println(" - Committed: " + memoryUsage.getCommitted() / 1024 + "[KB]");
        System.out.println(" - Max:       " + memoryUsage.getMax() / 1024 + "[KB]");
        System.out.println();
    }

}
