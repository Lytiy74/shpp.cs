package com.shpp.p2p.cs.azaika.assignment1;

public class Assignment1Part4 extends SuperKarel{

    public void run() throws Exception {
        putBeeper();
        chessOrderForLine();
    }


    /*
     * Precondition: Karel stays at the beginning of the line
     * Result: Karel went throw all lines and put beepers in chess order and repeat while front is clear.
     */
    private void chessOrderForLine() throws Exception {
        while (frontIsClear()) {
            if (beepersPresent()) {
                move();
            }
            else {
                move();
                putBeeper();
            }
        }
        manageEndOfLine();
    }
    /*
     * Precondition: Karel stays at the end of line
     * Result: Karel change the line and continue make chess order if it not the end of map
     */
    private void manageEndOfLine() throws Exception {
        if (facingEast() && leftIsClear()) {
            changeRowFacingEast();
        } else if (facingWest() && rightIsClear()) {
            changeRowFacingWest();
        }

        if (frontIsClear()) chessOrderForLine();
    }

    /*
     * Precondition: Karel faced to the wall to east
     * Result: Karel changed row 1 up and facing to west
     */
    private void changeRowFacingEast() throws Exception {
        turnLeft();
        if (beepersPresent()) {
            move();
        } else {
            move();
            putBeeper();
        }
        turnLeft();
        if (frontIsBlocked() && rightIsClear()) changeRowFacingWest();
    }

    /*
     * Precondition: Karel faced to the wall to west
     * Result: Karel changed row one up and facing to east
     */
    private void changeRowFacingWest() throws Exception {
        turnRight();
        if (beepersPresent()) {
            move();
        } else {
            move();
            putBeeper();
        }
        turnRight();
        if (frontIsBlocked() && leftIsClear()) changeRowFacingEast();
    }
}
