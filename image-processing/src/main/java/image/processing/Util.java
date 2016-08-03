package image.processing;

final class Util {

    private Util() {
    }

    public static int newRGB(int a, int r, int g, int b) {
        return (a << 24) | (r << 16) | (g << 8) | (b << 0);
    }

    public static int constrain(int value, int min, int max) {
        if (value < min)
            return min;
        else if (value > max)
            return max;
        else
            return value;
    }

}
