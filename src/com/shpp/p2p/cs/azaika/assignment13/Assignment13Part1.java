package p2p.cs.azaika.assignment13;

import acm.graphics.GImage;
import acm.util.ErrorException;

import java.util.*;
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
            int[][] binarizedPixels = OtsuThresholding.binarizeImage(grayScalePixels);

            // Perform opening morphological operation on the binarized image
            int[][] openedPixelsBin = MathMorphological.getOpenedArrayOfImage(binarizedPixels, Constants.STRUCTURING_ELEMENT_SIZE);

            // Perform closing morphological operation on the opened image
            int[][] closedPixelsBin = MathMorphological.getClosedArrayOfImage(openedPixelsBin, Constants.STRUCTURING_ELEMENT_SIZE);

            // Count the number of silhouettes in the closed image
            System.out.println("Silhouettes:" + getAmountOfSilhouettes(closedPixelsBin));
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


    /**
     * Counts the number of silhouettes in the given binary image.
     *
     * @param closedPixels The binary image (0 for a background, 1 for foreground)
     * @return The number of silhouettes in the image
     */
    private static int getAmountOfSilhouettes(int[][] closedPixels) {
        int count = 0;
        boolean[][] visited = new boolean[closedPixels.length][closedPixels[0].length];
        for (int i = 0; i < closedPixels.length; i++) {
            for (int j = 0; j < closedPixels[i].length; j++) {
                if (closedPixels[i][j] == Constants.BIN_BLACK_PIXEL && !visited[i][j]) {
                    count++;
                    bfs(closedPixels, visited, i, j);
                }
            }
        }
        return count;
    }

    private static void bfs(int[][] closedPixels, boolean[][] visited, int startY, int startX) {
        Queue<int[]> queueOfPixelsThatNeedToBeChecked = new LinkedList<>();
        queueOfPixelsThatNeedToBeChecked.add(new int[]{startY, startX});
        visited[startY][startX] = true;
        while (!queueOfPixelsThatNeedToBeChecked.isEmpty()) {
            int[] currentPixel = queueOfPixelsThatNeedToBeChecked.poll();
            int y = currentPixel[0];
            int x = currentPixel[1];
            for (int i = 0; i < Constants.DIRECTIONS[0].length; i++) {
                int newX = x + Constants.DIRECTIONS[0][i];
                int newY = y + Constants.DIRECTIONS[1][i];
                if (isValid(closedPixels, visited, newY, newX)) {
                    queueOfPixelsThatNeedToBeChecked.add(new int[]{newY, newX});
                    visited[newY][newX] = true;
                }
            }
        }


    }

    // Check if the current position is within bounds and is a closed pixel
    private static boolean isValid(int[][] closedPixels, boolean[][] visited, int y, int x) {
        return y < closedPixels.length
                && y >= 0
                && x < closedPixels[0].length
                && x >= 0
                && closedPixels[y][x] == Constants.BIN_BLACK_PIXEL
                && !visited[y][x];
    }

}
