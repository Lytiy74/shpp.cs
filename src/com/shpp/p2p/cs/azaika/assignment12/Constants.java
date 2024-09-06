package com.shpp.p2p.cs.azaika.assignment12;

/**
 * This interface contains various constants used throughout the image processing application.
 * Constants include file paths, image format regex, default file name, luminance values,
 * binary pixel values, and gray scale coefficients for RGB to gray scale conversion.
 */
public interface Constants {
    /* Structuring element is a shape, used to probe or interact with a given image.
                     with the purpose of drawing conclusions on how this shape fits or misses the shapes in the image.
                     It is typically used in morphological operations, such as dilation, erosion, opening,
                     and closing, as well as the hit-or-miss transform.
                     */
    int STRUCTURING_ELEMENT_SIZE = 4;
    // File a path for the image file in a project
    String FILE_PATH = "com/shpp/p2p/cs/azaika/assignment12/";
    String FILE_FORMAT_REGEX = ".+.jpg|png";
    String DEFAULT_FILE_NAME = "test.png";
    int MAX_LUMINANCE = 256;
    int BIN_BLACK_PIXEL = 1;
    int BIN_VISITED_PIXEL = -1;
    int BIN_WHITE_PIXEL = 0;
    double GRAY_SCALE_FOR_RED = 0.299;
    double GRAY_SCALE_FOR_GREEN = 0.587;
    double GRAY_SCALE_FOR_BLUE = 0.114;
    int[][] DIRECTIONS = {
            {0, 0, 1, -1, -1, 1, 1, -1}, // 4 directions (up, down, right, left)
            {1, -1, 0, 0, -1, 1, -1, 1} // diagonal directions, not used here but available
    };

}
