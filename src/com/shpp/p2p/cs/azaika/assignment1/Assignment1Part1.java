package com.shpp.p2p.cs.azaika.assignment1;

public class Assignment1Part1 extends SuperKarel{

    public void run() throws Exception {
        goToBeeper();
        takeBeeper();
        goToStartPosition();
    }
    /*
    Precondition: Karel facing to east, on left side wall and on a back wall. World had exit on east side
    Result: Karel find exit and stay on beeper
     */
    private void goToBeeper() throws Exception  {
        while (frontIsClear()){
            move();
        }
        turnRight();
        while (leftIsBlocked()){
            move();
        }
        turnLeft();
        while (frontIsClear() && noBeepersPresent()){
            move();
        }
    }
    /*
    Precondition: Karel stay on a beeper.
    Result: Karel take beeper.
     */
    private void takeBeeper() throws Exception {
        if (beepersPresent()) pickBeeper();
    }
    /*
    Precondition: Karel facing to east on the exit of home.
    Result: Karel come back to start position.
     */
    private void goToStartPosition() throws Exception {
        turnAround();
        move();
        while(rightIsBlocked()){
            move();
        }
        turnRight();
        while (frontIsClear()){
            move();
        }
        turnLeft();
        while (frontIsClear()){
            move();
        }
        turnAround();
    }




}
