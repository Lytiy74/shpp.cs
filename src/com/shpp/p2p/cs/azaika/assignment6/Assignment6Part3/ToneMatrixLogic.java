package com.shpp.p2p.cs.azaika.assignment6.Assignment6Part3;

public class ToneMatrixLogic {
    /**
     * Given the contents of the tone matrix, returns a string of notes that should be played
     * to represent that matrix.
     *
     * @param toneMatrix The contents of the tone matrix.
     * @param column     The column number that is currently being played.
     * @param samples    The sound samples associated with each row.
     * @return A sound sample corresponding to all notes currently being played.
     */
    public static double[] matrixToMusic(boolean[][] toneMatrix, int column, double[][] samples) {
        double[] sound = new double[ToneMatrixConstants.sampleSize()];
        double max = Double.NEGATIVE_INFINITY;

        for (int row = 0; row < toneMatrix.length; row++) {
            if (toneMatrix[row][column]){
                for (int i = 0; i < samples[row].length; i++) {
                    sound[i] += samples[row][i];
                    max = Math.max(Math.abs(max)  , Math.abs(sound[i]));
                }
            }
        }
        for (int i = 0; i < sound.length; i++) {
            sound[i] /= max;
        }
        return sound;
    }
}
