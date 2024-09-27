package com.shpp.p2p.cs.azaika.assignment13;

import java.util.function.BiFunction;


public class MathMorphological {

    /**
     * Performs dilation on the given binary image.
     *
     * @param image The binary image (0 for background, 1 for foreground)
     * @return The dilated image
     */
    static boolean[][] dilate(boolean[][] image) {
        // Dilation uses max operator, starting with the lowest possible value (0).
        return getMorphologicalOperationResult(image, (a, b) -> a || b, Constants.BIN_WHITE_PIXEL);
    }

    /**
     * Performs erosion on the given binary image.
     *
     * @param image The binary image (0 for background, 1 for foreground)
     * @return The eroded image as a 2D array of booleans
     */
    static boolean[][] erode(boolean[][] image) {
        // Erosion uses min operator, starting with the highest possible value (1 for binary images).
        return getMorphologicalOperationResult(image, (a, b) -> a && b, Constants.BIN_BLACK_PIXEL);
    }

    /**
     * Applies a morphological operation to a binary image using the specified structuring element and operator.
     *
     * @param image        The binary image (2D array of booleans) where true represents the foreground and false represents the background
     * @param operator     The binary function representing the morphological operation to perform (AND for erosion, OR for dilation)
     * @param initialValue The initial value to use for the operation, typically true for erosion and false for dilation
     * @return A new binary image (2D array of booleans) after applying the morphological operation
     */
    private static boolean[][] getMorphologicalOperationResult(boolean[][] image, BiFunction<Boolean, Boolean, Boolean> operator, boolean initialValue) {

        int height = image.length;
        int width = image[0].length;
        boolean[][] result = new boolean[height][width];
        int radius = Constants.STRUCTURING_ELEMENT_SIZE / 2;

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                // Use the provided initial value for the operation (0 for dilation, 1 for erosion).
                boolean value = initialValue;

                for (int k = -radius; k <= radius; k++) {
                    for (int l = -radius; l <= radius; l++) {
                        int x = i + k;
                        int y = j + l;

                        // Only consider valid indices within the image bounds.
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
     * @return The opened image
     */
    static boolean[][] getOpenedArrayOfImage(boolean[][] image) {
        boolean[][] eroded = erode(image);
        return dilate(eroded);
    }

    /**
     * Performs closing on the given binary image.
     *
     * @param image The binary image (0 for background, 1 for foreground)
     * @return The closed image
     */
    static boolean[][] getClosedArrayOfImage(boolean[][] image) {
        boolean[][] dilated = dilate(image);
        return erode(dilated);
    }
}
