package com.shpp.p2p.cs.azaika.assignment12;

import java.util.function.BiFunction;

public class MathMorphological {

    /**
     * Performs dilation on the given binary image.
     *
     * @param image The binary image (0 for background, 1 for foreground)
     * @param size  The size of the structuring element (must be odd)
     * @return The dilated image
     */
    static int[][] dilate(int[][] image, int size) {
        return getMorphologicalOperationResult(image, size, Math::max);
    }

    /**
     * Performs erosion on the given binary image.
     *
     * @param image The binary image (0 for background, 1 for foreground)
     * @param size  The size of the structuring element (must be odd)
     * @return The eroded image
     */
    static int[][] erode(int[][] image, int size) {
        return getMorphologicalOperationResult(image, size, Math::min);
    }

    private static int[][] getMorphologicalOperationResult(int[][] image, int sizeOfStructuringElement, BiFunction<Integer, Integer, Integer> operator) {
        int height = image.length;
        int width = image[0].length;
        int[][] result = new int[height][width];
        int radius = sizeOfStructuringElement / 2;

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int value = operator.apply(Constants.MAX_LUMINANCE, 0);

                for (int k = -radius; k <= radius; k++) {
                    for (int l = -radius; l <= radius; l++) {
                        int x = i + k;
                        int y = j + l;

                        if (x >= 0 && x < height && y >= 0 && y < width) {
                            value = operator.apply(value, image[x][y]);
                        }
                    }
                }

                result[i][j] = value;
            }
        }
        return result;
    }

    /**
     * Performs opening on the given binary image.
     *
     * @param image The binary image (0 for background, 1 for foreground)
     * @param size  The size of the structuring element (must be odd)
     * @return The opened image
     */
    static int[][] getOpenedArrayOfImage(int[][] image, int size) {
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
    static int[][] getClosedArrayOfImage(int[][] image, int size) {
        int[][] dilated = dilate(image, size);
        return erode(dilated, size);
    }
}
