package com.shpp.p2p.cs.azaika.assignment13;

import java.util.LinkedList;
import java.util.Queue;

import static com.shpp.p2p.cs.azaika.assignment13.Constants.BIN_WHITE_PIXEL;

public class SilhouettesFinder {


    /**
     * Counts the number of silhouettes in the given binary image.
     *
     * @param closedPixels The binary image (0 for a background, 1 for foreground)
     * @return The number of silhouettes in the image
     */
    public static int getAmountOfSilhouettes(boolean[][] closedPixels) {
        int count = 0;
        for (int i = 0; i < closedPixels.length; i++) {
            for (int j = 0; j < closedPixels[i].length; j++) {
                if (closedPixels[i][j]) {
                    count++;
                    bfs(closedPixels, i, j);
                }
            }
        }
        return count;
    }

    /**
     * Performs a breadth-first search (BFS) on a grid of pixels starting from the specified coordinates.
     * This method is typically used for pixel clustering or flood-fill algorithms.
     *
     * @param closedPixels A 2D array representing the pixel grid. Each element indicates whether a pixel is traversable.
     * @param startY       The starting Y-coordinate for the BFS.
     * @param startX       The starting X-coordinate for the BFS.
     */
    private static void bfs(boolean[][] closedPixels, int startY, int startX) {
        Queue<int[]> queueOfPixelsThatNeedToBeChecked = new LinkedList<>();
        queueOfPixelsThatNeedToBeChecked.add(new int[]{startY, startX});
        closedPixels[startY][startX] = BIN_WHITE_PIXEL;
        while (!queueOfPixelsThatNeedToBeChecked.isEmpty()) {
            int[] currentPixel = queueOfPixelsThatNeedToBeChecked.poll();
            int y = currentPixel[0];
            int x = currentPixel[1];
            for (int i = 0; i < Constants.DIRECTIONS[0].length; i++) {
                int newX = x + Constants.DIRECTIONS[0][i];
                int newY = y + Constants.DIRECTIONS[1][i];
                if (isValid(closedPixels, newY, newX)) {
                    queueOfPixelsThatNeedToBeChecked.add(new int[]{newY, newX});
                    closedPixels[newY][newX] = BIN_WHITE_PIXEL;
                }
            }
        }

    }

    // Check if the current position is within bounds and is a closed pixel
    private static boolean isValid(boolean[][] closedPixels, int y, int x) {
        return y < closedPixels.length
                && y >= 0
                && x < closedPixels[0].length
                && x >= 0
                && closedPixels[y][x];
    }
}
