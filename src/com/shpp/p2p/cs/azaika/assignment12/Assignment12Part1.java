package com.shpp.p2p.cs.azaika.assignment12;

import acm.graphics.GImage;
import acm.util.ErrorException;

import java.util.regex.Pattern;

import static com.shpp.p2p.cs.azaika.assignment12.MathMorphological.*;

/**
 * This class contains the main method and several helper methods for image processing.
 * The main method reads an image, converts it to grayscale, applies thresholding,
 * and performs morphological operations (opening and closing) to count the number of silhouettes.
 */
public class Assignment12Part1 {
    private static final int STRUCTURING_ELEMENT_SIZE = 4;

    public static void main(String[] args) {
        try {
            String fileName = getInputFileName(args);
            GImage image = new GImage("com/shpp/p2p/cs/azaika/assignment12/" + fileName);
            int[][] grayScalePixels = grayScaleImage(image);
            int[][] binarizedPixels = binarizeImage(grayScalePixels);
            int[][] openedPixelsBin = open(binarizedPixels, STRUCTURING_ELEMENT_SIZE);
            int[][] closedPixelsBin = close(openedPixelsBin, STRUCTURING_ELEMENT_SIZE);

            System.out.println("Silhouettes:" + getAmountOfSilhouettes(closedPixelsBin));
        }catch (IllegalArgumentException | ErrorException e){
            System.out.println(e.getMessage());
        }
    }

    /**
 * This method retrieves the input file name for the image processing program.
 * If no argument is provided, it defaults to "test.jpg".
 * If an argument is provided, it must match the pattern "\\w+.jpg" (a word followed by ".jpg").
 * If the argument does not match the pattern, an IllegalArgumentException is thrown.
 *
 * @param args The command-line arguments.
 * @return The input file name for the image processing program.
 * @throws IllegalArgumentException If the argument does not match the pattern "\\w+.jpg".
 */
private static String getInputFileName(String[] args) throws IllegalArgumentException {
    Pattern pattern = Pattern.compile("\\w+.jpg");
    String fileName;
    if (args.length > 0 && !pattern.matcher(args[0]).matches()) {
        System.out.println("Usage: java Assignment12Part1 <image_file_name>.jpg");
        throw new IllegalArgumentException("Wrong argument passed");
    } else if (args.length == 0) {
        fileName = "test.jpg";
    } else {
        fileName = args[0];
    }
    return fileName;
}


    /**
     * Counts the number of silhouettes in the given binary image.
     *
     * @param closedPixels The binary image (0 for a background, 1 for foreground)
     * @return The number of silhouettes in the image
     */
    private static int getAmountOfSilhouettes(int[][] closedPixels) {
        int count = 0;
        for (int i = 0; i < closedPixels.length; i++) {
            for (int j = 0; j < closedPixels[i].length; j++) {
                if (closedPixels[i][j] == 1) {
                    count++;
                    dfs(closedPixels, i, j);
                }
            }
        }
        return count;
    }

    /**
     * Performs a depth-first search (DFS) on the given binary image.
     *
     * @param closedPixels The binary image (0 for a background, 1 for foreground)
     * @param y            The y-coordinate of the starting pixel
     * @param x            The x-coordinate of the starting pixel
     */
    private static void dfs(int[][] closedPixels, int y, int x) {
        int[] dx = {0, 0, 1, -1, -1, 1, 1, -1};
        int[] dy = {1, -1, 0, 0, -1, 1, -1, 1};
        if (x < 0 || y >= closedPixels.length || y < 0 || x >= closedPixels[0].length || closedPixels[y][x] != 1) {
            return;
        }
        closedPixels[y][x] = -1;
        for (int i = 0; i < 4; i++) {
            int newX = x + dx[i];
            int newY = y + dy[i];
            dfs(closedPixels, newY, newX);
        }
    }

    /**
     * Applies thresholding to the given grayscale image.
     *
     * @param imagePixels The grayscale image
     * @return The binarized image (0 for a background, 1 for foreground)
     */
    private static int[][] binarizeImage(int[][] imagePixels) {
        int[] histogram = histogramFor(imagePixels);
        return thresHoldImage(imagePixels, histogram);
    }

    /**
     * Calculates the histogram of the given grayscale image.
     *
     * @param pixels The grayscale image
     * @return The histogram of the image
     */
    private static int[] histogramFor(int[][] pixels) {
        int[] histogram = new int[getMaxLuminance() + 1];

        for (int[] pixel : pixels) {
            for (int i : pixel) {
                int grayValue = GImage.getRed(i);
                histogram[grayValue]++;
            }
        }
        return histogram;
    }

    /**
     * Applies thresholding to the given grayscale image based on the calculated histogram.
     *
     * @param pixels    The grayscale image
     * @param histogram The histogram of the image
     * @return The binarized image (0 for a background, 1 for foreground)
     */
    private static int[][] thresHoldImage(int[][] pixels, int[] histogram) {
        int height = pixels.length;
        int width = pixels[0].length;
        int totalPixels = width * height;

        float[] probabilities = new float[256];
        for (int i = 0; i < 256; i++) {
            probabilities[i] = (float) histogram[i] / totalPixels;
        }
        int threshold = getThreshold(probabilities);

        int[][] binarized = new int[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int grayValue = GImage.getRed(pixels[i][j]);
                binarized[i][j] = grayValue > threshold ? 0 : 1;
            }
        }

        return binarized;
    }

    private static int getThreshold(float[] probabilities) {
        float maxVariance = 0;
        int threshold = 0;
        for (int t = 0; t < 256; t++) {
            float w0 = 0, w1 = 0;
            float sum0 = 0, sum1 = 0;

            for (int i = 0; i <= t; i++) {
                w0 += probabilities[i];
                sum0 += i * probabilities[i];
            }
            for (int i = t + 1; i < 256; i++) {
                w1 += probabilities[i];
                sum1 += i * probabilities[i];
            }

            if (w0 == 0 || w1 == 0) continue;

            float mean0 = sum0 / w0;
            float mean1 = sum1 / w1;
            float variance = w0 * w1 * (mean0 - mean1) * (mean0 - mean1);

            if (variance > maxVariance) {
                maxVariance = variance;
                threshold = t;
            }
        }
        return threshold;
    }

    /**
     * Converts the given color image to grayscale.
     *
     * @param image The color image
     * @return The grayscale image
     */
    private static int[][] grayScaleImage(GImage image) {
        int[][] pixels = image.getPixelArray();
        int numRows = pixels.length;
        int numCols = pixels[0].length;
        int[][] grayScalePixels = new int[numRows][numCols];

        for (int row = 0; row < numRows; ++row) {
            for (int col = 0; col < numCols; ++col) {
                int red = GImage.getRed(pixels[row][col]);
                int green = GImage.getGreen(pixels[row][col]);
                int blue = GImage.getBlue(pixels[row][col]);

                int grayScaleValue = (int) (red * 0.299 + green * 0.587 + blue * 0.114);
                grayScalePixels[row][col] = GImage.createRGBPixel(grayScaleValue, grayScaleValue, grayScaleValue);
            }
        }

        return grayScalePixels;
    }


}
