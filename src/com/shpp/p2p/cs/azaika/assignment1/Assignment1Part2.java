package com.shpp.p2p.cs.azaika.assignment1;

public class Assignment1Part2 extends SuperKarel {
    /*
    Precondition: Karel stay at the south-west corner and facing east.
    Result: Karel stay at the south-east corner and facing east.
     */
    public void run() throws Exception {
        while (frontIsClear()){
            makeColumn();
            goToNextColumn();
        }
        makeColumn();
    }


    /*
   Precondition: Karel facing to east,
   Result: Karel made column and come back to start position and facing east
    */
    private void makeColumn() throws Exception{
        turnLeft();
        while (frontIsClear()) {
            if (noBeepersPresent()) {
                putBeeper();
            }
            move();
        }
        if (noBeepersPresent()){
            putBeeper();
        }
        goBackDown();
    }
    /*
   Precondition: Karel made column and stay on the top of column
   Result: Karel come down to the start point of the column
    */
    private void goBackDown() throws Exception {
        turnAround();
        while (frontIsClear()){
            move();
        }
        turnLeft();
    }

    /*
    We know that column build every 3 cells.
    Precondition: Karel facing east near to south wall.
    Result: Karel facing east, moved 3 cells near to south wall.
     */
    private void goToNextColumn() throws Exception{
        for (int i = 0; i <= 3; i++) {
            move();
        }
    }
}
