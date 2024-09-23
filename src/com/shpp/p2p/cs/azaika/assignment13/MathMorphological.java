package p2p.cs.azaika.assignment13;

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
        // Dilation uses max operator, starting with the lowest possible value (0).
        return getMorphologicalOperationResult(image, size, Math::max, Constants.BIN_WHITE_PIXEL);
    }

    static int[][] erode(int[][] image, int size) {
        // Erosion uses min operator, starting with the highest possible value (1 for binary images).
        return getMorphologicalOperationResult(image, size, Math::min, Constants.BIN_BLACK_PIXEL);
    }

    private static int[][] getMorphologicalOperationResult(int[][] image, int sizeOfStructuringElement, BiFunction<Integer, Integer, Integer> operator, int initialValue) {

        int height = image.length;
        int width = image[0].length;
        int[][] result = new int[height][width];
        int radius = sizeOfStructuringElement / 2;

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                // Use the provided initial value for the operation (0 for dilation, 1 for erosion).
                int value = initialValue;

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
