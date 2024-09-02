package com.shpp.p2p.cs.azaika.assignment12;

public class MathMorphological {
    private static final int MAX_LUMINANCE = 255;
    static int getMaxLuminance(){
        return MAX_LUMINANCE;
    }
    /**
     * Performs dilation on the given binary image.
     *
     * @param image The binary image (0 for background, 1 for foreground)
     * @param size  The size of the structuring element (must be odd)
     * @return The dilated image
     */
    static int[][] dilate(int[][] image, int size) {
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
    static int[][] erode(int[][] image, int size) {
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
    static int[][] open(int[][] image, int size) {
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
    static int[][] close(int[][] image, int size) {
        int[][] dilated = dilate(image, size);
        return erode(dilated, size);
    }
}
