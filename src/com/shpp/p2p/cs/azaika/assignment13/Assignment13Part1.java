package com.shpp.p2p.cs.azaika.assignment13;

import acm.graphics.GImage;
import acm.util.ErrorException;

import java.util.regex.Pattern;


/**
 * This class contains the main method and several helper methods for image processing.
 * The main method reads an image, converts it to grayscale, applies thresholding,
 * and performs morphological operations (opening and closing) to count the number of silhouettes.
 */
public class Assignment13Part1 {


    /**
     * The main method of the Assignment12Part1 class. It reads an image, converts it to grayscale, applies thresholding,
     * and performs morphological operations (opening and closing) to count the number of silhouettes.
     *
     * @param args The command-line arguments.
     */
    public static void main(String[] args) {
        try {
            // Retrieve the input file name for the image processing program
            String fileName = getInputFileName(args);

            // Load the image from the specified file path
            GImage image = new GImage(Constants.FILE_PATH + fileName);

            // Convert the color image to grayscale
            int[][] grayScalePixels = OtsuThresholding.grayScaleImage(image);

            // Apply thresholding to the grayscale image
            boolean[][] binarizedPixels = OtsuThresholding.binarizeImage(grayScalePixels);

            // Perform opening morphological operation on the binarized image
            boolean[][] openedPixelsBin = MathMorphological.getOpenedArrayOfImage(binarizedPixels);

            // Perform closing morphological operation on the opened image
            boolean[][] closedPixelsBin = MathMorphological.getClosedArrayOfImage(openedPixelsBin);

            // Count the number of silhouettes in the closed image
            System.out.println("Silhouettes:" + SilhouettesFinder.getAmountOfSilhouettes(closedPixelsBin));
        } catch (IllegalArgumentException | ErrorException e) {
            // Print the error message if an exception occurs
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
        Pattern pattern = Pattern.compile(Constants.FILE_FORMAT_REGEX);
        String fileName = Constants.DEFAULT_FILE_NAME; // Set default file name

        if (args == null || args.length == 0) {
            System.out.println("No valid file name provided. Using default file: " + fileName);
            return fileName;
        }

        if (!pattern.matcher(args[0]).matches()) {
            System.out.println("Usage: java Assignment12Part1 <imageFileName>.jpg | .png");
            System.out.println("Invalid file format. Using default file: " + fileName);
        } else {
            fileName = args[0];
        }

        return fileName;
    }

}
