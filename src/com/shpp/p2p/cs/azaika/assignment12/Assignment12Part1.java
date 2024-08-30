package com.shpp.p2p.cs.azaika.assignment12;

import acm.graphics.GImage;

/**
 * This class contains the main method and several helper methods for image processing.
 * The main method reads an image, converts it to grayscale, applies thresholding,
 * and performs morphological operations (opening and closing) to count the number of silhouettes.
 */
public class Assignment12Part1 {
    private static final int MAX_LUMINANCE = 255;

    public static void main(String[] args) {
        GImage image = new GImage("src/com/shpp/p2p/cs/azaika/assignment12/image2.png");

        int[][] grayScalePixels = grayScaleImage(image);
        GImage grayImage = new GImage(grayScalePixels);
        grayImage.saveImage("src/com/shpp/p2p/cs/azaika/assignment12/grayscale2.png");

        int[][] binarizedPixels = process(grayImage);

        int[][] openedPixelsBin = open(binarizedPixels, 4);
        int[][] closedPixelsBin = close(openedPixelsBin, 4);

        System.out.println("Silhouettes:" + getAmountOfSilhouettes(closedPixelsBin));
    }


    /**
     * Counts the number of silhouettes in the given binary image.
     *
     * @param closedPixels The binary image (0 for background, 1 for foreground)
     * @return The number of silhouettes in the image
     */
    private static int getAmountOfSilhouettes(int[][] closedPixels) {
        int count = 0;
        int[][] check = new int[closedPixels.length][closedPixels[0].length];
        for (int i = 0; i < closedPixels.length; i++) {
            for (int j = 0; j < closedPixels[i].length; j++) {
                if (closedPixels[i][j] == 1) {
                    count++;
                    dfs(closedPixels, i, j);
                }
            }
        }
        for (int i = 0; i < closedPixels.length; i++) {
            for (int j = 0; j < closedPixels[i].length; j++) {
                if (closedPixels[i][j] == -1) {
                    check[i][j] = GImage.createRGBPixel(255, 0, 0);
                }
            }
        }
        GImage checkImage = new GImage(check);
        checkImage.saveImage("src/com/shpp/p2p/cs/azaika/assignment12/checked2.png");
        return count;
    }

    /**
     * Performs a depth-first search (DFS) on the given binary image.
     *
     * @param closedPixels The binary image (0 for background, 1 for foreground)
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
     * @param image The grayscale image
     * @return The binarized image (0 for background, 1 for foreground)
     */
    private static int[][] process(GImage image) {
        int[][] pixels = image.getPixelArray();
        int[] histogram = histogramFor(pixels);
        return thresHoldImage(pixels, histogram);
    }

    /**
     * Calculates the histogram of the given grayscale image.
     *
     * @param pixels The grayscale image
     * @return The histogram of the image
     */
    private static int[] histogramFor(int[][] pixels) {
        int[] histogram = new int[MAX_LUMINANCE + 1];

        for (int i = 0; i < pixels.length; i++) {
            for (int j = 0; j < pixels[i].length; j++) {
                int grayValue = GImage.getRed(pixels[i][j]);
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
     * @return The binarized image (0 for background, 1 for foreground)
     */
    private static int[][] thresHoldImage(int[][] pixels, int[] histogram) {
        int height = pixels.length;
        int width = pixels[0].length;
        int totalPixels = width * height;

        float[] probabilities = new float[256];
        for (int i = 0; i < 256; i++) {
            probabilities[i] = (float) histogram[i] / totalPixels;
        }
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

        int[][] binarized = new int[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int grayValue = GImage.getRed(pixels[i][j]);
                binarized[i][j] = grayValue > threshold ? 0 : 1;
            }
        }

        return binarized;
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

    /**
     * Performs dilation on the given binary image.
     *
     * @param image The binary image (0 for background, 1 for foreground)
     * @param size  The size of the structuring element (must be odd)
     * @return The dilated image
     */
    private static int[][] dilate(int[][] image, int size) {
        int height = image.length;
        int width = image[0].length;
        int[][] dilated = new int[height][width];
        int radius = size / 2;

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int maxVal = 0;

                for (int k = -radius; k <= radius; k++) {
                    for (int l = -radius; l <= radius; l++) {
                        int x = i + k;
                        int y = j + l;

                        if (x >= 0 && x < height && y >= 0 && y < width) {
                            maxVal = Math.max(maxVal, image[x][y]);
                        }
                    }
                }

                dilated[i][j] = maxVal;
            }
        }

        return dilated;
    }

    /**
     * Performs erosion on the given binary image.
     *
     * @param image The binary image (0 for background, 1 for foreground)
     * @param size  The size of the structuring element (must be odd)
     * @return The eroded image
     */
    private static int[][] erode(int[][] image, int size) {
        int height = image.length;
        int width = image[0].length;
        int[][] eroded = new int[height][width];
        int radius = size / 2;

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int minVal = MAX_LUMINANCE;

                for (int k = -radius; k <= radius; k++) {
                    for (int l = -radius; l <= radius; l++) {
                        int x = i + k;
                        int y = j + l;

                        if (x >= 0 && x < height && y >= 0 && y < width) {
                            minVal = Math.min(minVal, image[x][y]);
                        }
                    }
                }

                eroded[i][j] = minVal;
            }
        }

        return eroded;
    }

    /**
     * Performs opening on the given binary image.
     *
     * @param image The binary image (0 for background, 1 for foreground)
     * @param size  The size of the structuring element (must be odd)
     * @return The opened image
     */
    private static int[][] open(int[][] image, int size) {
        int[][] eroded = erode(image, size);
        return dilate(eroded, size);
    }

    /**
     * Performs closing on the given binary image.
     *
     * @param image The binary image (0 for background, 1 for foreground)
     * @param size  The size of the structuring element (must be odd)
     * @return The closed image
     */
    private static int[][] close(int[][] image, int size) {
        int[][] dilated = dilate(image, size);
        return erode(dilated, size);
    }
}
