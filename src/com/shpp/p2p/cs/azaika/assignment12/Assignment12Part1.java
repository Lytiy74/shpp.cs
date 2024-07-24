package com.shpp.p2p.cs.azaika.assignment12;

import acm.graphics.GImage;

public class Assignment12Part1 {
    public static void main(String[] args) {
        // Load an image
        GImage image = new GImage("C:\\Users\\User\\IdeaProjects\\ExcelProcess\\shpp.cs\\src\\com\\shpp\\p2p\\cs\\azaika\\assignment12\\image.png");

        // Convert the image to grayscale
        int[][] grayScalePixels = grayScaleImage(image);
        GImage image1 = new GImage(grayScalePixels);
        image1.saveImage("C:\\Users\\User\\IdeaProjects\\ExcelProcess\\shpp.cs\\src\\com\\shpp\\p2p\\cs\\azaika\\assignment12\\grayscale.png");


    }

    private int findSilhouettes(GImage image){
        int[][] pixels = grayScaleImage(image);
        pixels = thresHoldImage(pixels);

    }

    private int[][] thresHoldImage(int[][] pixels) {

    }

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

                int grayScaleValue = (int) (red*0.299 + green*0.587 + blue*0.114);
                grayScalePixels[row][col] = GImage.createRGBPixel(grayScaleValue, grayScaleValue, grayScaleValue);
            }
        }

        return grayScalePixels;
    }
}
