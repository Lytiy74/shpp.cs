package com.shpp.p2p.cs.azaika.assignment6.Assignment6Part1;

import acm.graphics.*;

public class SteganographyLogic {
    /**
     * Given a GImage containing a hidden message, finds the hidden message
     * contained within it and returns a boolean array containing that message.
     * <p/>
     * A message has been hidden in the input image as follows.  For each pixel
     * in the image, if that pixel has a red component that is an even number,
     * the message value at that pixel is false.  If the red component is an odd
     * number, the message value at that pixel is true.
     *
     * @param source The image containing the hidden message.
     * @return The hidden message, expressed as a boolean array.
     */
    public static boolean[][] findMessage(GImage source) {
        int[][] pixelArray = source.getPixelArray();
        boolean[][] result = new boolean[pixelArray.length][pixelArray[0].length];
        for (int i = 0; i < pixelArray.length; i++) {
            for (int j = 0; j < pixelArray[i].length; j++) {
                if (GImage.getRed(pixelArray[i][j]) % 2 == 0) result[i][j] = false;
                else result[i][j] = true;
            }
        }
        return result;
    }

    /**
     * Hides the given message inside the specified image.
     * <p/>
     * The image will be given to you as a GImage of some size, and the message will
     * be specified as a boolean array of pixels, where each white pixel is denoted
     * false and each black pixel is denoted true.
     * <p/>
     * The message should be hidden in the image by adjusting the red channel of all
     * the pixels in the original image.  For each pixel in the original image, you
     * should make the red channel an even number if the message color is white at
     * that position, and odd otherwise.
     * <p/>
     * You can assume that the dimensions of the message and the image are the same.
     * <p/>
     *
     * @param message The message to hide.
     * @param source  The source image.
     * @return A GImage whose pixels have the message hidden within it.
     */
    public static GImage hideMessage(boolean[][] message, GImage source) {
        int[][] pixelArray = source.getPixelArray();
        for (int i = 0; i < pixelArray.length; i++) {
            for (int j = 0; j < pixelArray[i].length; j++) {
                int red = GImage.getRed(pixelArray[i][j]);
                int green = GImage.getGreen(pixelArray[i][j]);
                int blue = GImage.getBlue(pixelArray[i][j]);
                if (message[i][j] && (GImage.getRed(pixelArray[i][j]) % 2) == 0) {
                    red = Math.min(red + 1, 255);
                    pixelArray[i][j] = GImage.createRGBPixel(red, green, blue);
                } else if (!message[i][j] && (GImage.getRed(pixelArray[i][j]) % 2) == 1) {
                    red = Math.max(red - 1, 0);
                    pixelArray[i][j] = GImage.createRGBPixel(red, green, blue);
                }
            }
        }
        return new GImage(pixelArray);
    }
}
