package com.shpp.p2p.cs.azaika.assignment12;

import acm.graphics.GImage;


public class OtsuThresholding {


    /**
     * Applies thresholding to the given grayscale image.
     *
     * @param imagePixels The grayscale image
     * @return The binarized image (0 for a background, 1 for foreground)
     */
    public static int[][] binarizeImage(int[][] imagePixels) {
        int[] histogram = histogramFor(imagePixels);
        return thresholdImage(imagePixels, histogram);
    }

    /**
     * Calculates the histogram of the given grayscale image.
     *
     * @param pixels The grayscale image
     * @return The histogram of the image
     */
    public static int[] histogramFor(int[][] pixels) {
        int[] histogram = new int[Constants.MAX_LUMINANCE];

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
    public static int[][] thresholdImage(int[][] pixels, int[] histogram) {
        int height = pixels.length;
        int width = pixels[0].length;

        int totalPixels = width * height;
        float[] probabilities = new float[Constants.MAX_LUMINANCE];
        for (int i = 0; i < Constants.MAX_LUMINANCE; i++) {
            probabilities[i] = (float) histogram[i] / totalPixels;
        }
        int threshold = getThreshold(probabilities);

        int[][] binarized = new int[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int grayValue = GImage.getRed(pixels[i][j]);
                binarized[i][j] = grayValue > threshold ? Constants.BIN_WHITE_PIXEL : Constants.BIN_BLACK_PIXEL;
            }
        }

        return binarized;
    }

    public record WeightsAndSums(float w0, float w1, float sum0, float sum1) {
    }


    public static int getThreshold(float[] probabilities) {
        float maxVariance = 0;
        int threshold = 0;

        for (int t = 0; t < Constants.MAX_LUMINANCE; t++) {
            // Use a record to hold weights and sums for the current threshold
            WeightsAndSums ws = calculateWeightsAndSums(probabilities, t);

            // Skip the iteration if either w0 or w1 is zero
            if (ws.w0() == 0 || ws.w1() == 0) continue;

            // Calculate the mean values for grayscale values less than or equal to the current threshold and greater than the current threshold
            float mean0 = ws.sum0() / ws.w0();
            float mean1 = ws.sum1() / ws.w1();

            // Calculate the inter-class variance
            float variance = ws.w0() * ws.w1() * (mean0 - mean1) * (mean0 - mean1);

            // Update the maximum variance and the optimal threshold if the current variance is greater
            if (variance > maxVariance) {
                maxVariance = variance;
                threshold = t;
            }
        }

        return threshold;
    }

    // Helper method to calculate weights and sums for a given threshold
    private static WeightsAndSums calculateWeightsAndSums(float[] probabilities, int t) {
        float w0 = 0, w1 = 0;
        float sum0 = 0, sum1 = 0;

        // Calculate the sum of probabilities and weighted sum for grayscale values less than or equal to the current threshold
        for (int i = 0; i <= t; i++) {
            w0 += probabilities[i];
            sum0 += i * probabilities[i];
        }

        // Calculate the sum of probabilities and weighted sum for grayscale values greater than the current threshold
        for (int i = t + 1; i < Constants.MAX_LUMINANCE; i++) {
            w1 += probabilities[i];
            sum1 += i * probabilities[i];
        }

        return new WeightsAndSums(w0, w1, sum0, sum1);
    }

    /**
     * Converts the given color image to grayscale.
     *
     * @param image The color image
     * @return The grayscale image
     */
    public static int[][] grayScaleImage(GImage image) {
        int[][] pixels = image.getPixelArray();
        int numRows = pixels.length;
        int numCols = pixels[0].length;
        int[][] grayScalePixels = new int[numRows][numCols];

        for (int row = 0; row < numRows; ++row) {
            for (int col = 0; col < numCols; ++col) {
                int red = GImage.getRed(pixels[row][col]);
                int green = GImage.getGreen(pixels[row][col]);
                int blue = GImage.getBlue(pixels[row][col]);

                int grayScaleValue = (int) (red * Constants.GRAY_SCALE_FOR_RED + green * Constants.GRAY_SCALE_FOR_GREEN + blue * Constants.GRAY_SCALE_FOR_BLUE);
                grayScalePixels[row][col] = GImage.createRGBPixel(grayScaleValue, grayScaleValue, grayScaleValue);
            }
        }

        return grayScalePixels;
    }


}