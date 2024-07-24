package com.shpp.p2p.cs.azaika.assignment12;

import acm.graphics.GImage;


public class Assignment12Part1 {
    private static final int MAX_LUMINANCE = 255;
    public static void main(String[] args) {
        // Завантаження зображення
        GImage image = new GImage("C:\\Users\\User\\IdeaProjects\\ExcelProcess\\shpp.cs\\src\\com\\shpp\\p2p\\cs\\azaika\\assignment12\\image.png");

        // Перетворення зображення у градації сірого
        int[][] grayScalePixels = grayScaleImage(image);
        GImage grayImage = new GImage(grayScalePixels);
        grayImage.saveImage("C:\\Users\\User\\IdeaProjects\\ExcelProcess\\shpp.cs\\src\\com\\shpp\\p2p\\cs\\azaika\\assignment12\\grayscale.png");

        // Бінаризація зображення
        int[][] binarizedPixels = findSilhouettes(grayImage);
        GImage binarizedImage = new GImage(binarizedPixels);
        binarizedImage.saveImage("C:\\Users\\User\\IdeaProjects\\ExcelProcess\\shpp.cs\\src\\com\\shpp\\p2p\\cs\\azaika\\assignment12\\binarized.png");
    }

    private static int[][] findSilhouettes(GImage image) {
        int[][] pixels = image.getPixelArray();
        int[] histogram = histogramFor(pixels);
        return thresHoldImage(pixels, histogram);
    }


    private static int[] histogramFor(int[][] pixels) {
        int[] histogram = new int[MAX_LUMINANCE + 1];

        for (int i = 0; i < pixels.length; i++) {
            for (int j = 0; j < pixels[i].length; j++) {
                int grayValue = GImage.getRed(pixels[i][j]); // Отримання значення яскравості
                histogram[grayValue]++;
            }
        }
        return histogram;
    }


    private static int[][] thresHoldImage(int[][] pixels, int[] histogram) {
        int height = pixels.length;
        int width = pixels[0].length;
        int totalPixels = width  * height;


        // Обчислення ймовірностей
        float[] probabilities = new float[256];
        for (int i = 0; i < 256; i++) {
            probabilities[i] = (float) histogram[i] / totalPixels;
        }

        // Пошук оптимального порогового значення
        float maxVariance = 0;
        int threshold = 0;
        for (int t = 0; t < 256; t++) {
            float w0 = 0, w1 = 0;
            float sum0 = 0, sum1 = 0;

            for (int i = 0; i <= t; i++) {
                w0 += probabilities[i];
                sum0 += i * probabilities[i];
            }
            for (int i = t + 1; i < 256; i++) {
                w1 += probabilities[i];
                sum1 += i * probabilities[i];
            }

            if (w0 == 0 || w1 == 0) continue;

            float mean0 = sum0 / w0;
            float mean1 = sum1 / w1;
            float variance = w0 * w1 * (mean0 - mean1) * (mean0 - mean1);

            if (variance > maxVariance) {
                maxVariance = variance;
                threshold = t;
            }
        }

        // Бінаризація зображення
        int[][] binarized = new int[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int grayValue = GImage.getRed(pixels[i][j]);
                binarized[i][j] = grayValue > threshold ? GImage.createRGBPixel(MAX_LUMINANCE, MAX_LUMINANCE, MAX_LUMINANCE) : GImage.createRGBPixel(0, 0, 0);
            }
        }

        return binarized;
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
