package com.shpp.p2p.cs.azaika.assignment1;

public class Assignment1Part3 extends SuperKarel {

    public void run () throws Exception{
            findMiddle();
    }
    /*
    Main idea of this method is - Karel going to east side while is clear and put beeper at the end
    then turn around and do the same at west side. Thereby Karel move beepers close to middle until he finds it.

    Precondition: Karel stay facing to east on cell without beeper
    Result: Karel went throw all line
     */
    private void findMiddle() throws Exception{
        // karel going through all line while front is clear and there no beepers
            goToEndOfLine();
            turnAround();

            if (beepersPresent()) {
                moveBeeper();
            } else if (frontIsClear()){
                putBeeper();
                move();
                findMiddle();
            } else putBeeper();
    }
    /*
    Precondition: Karel find beeper on his way and he faced to direction where middle is placed
    Result: Karel moved beeper for one cell close to middle
     */
    private void moveBeeper() throws Exception {
        if (beepersPresent()) {
            pickBeeper();
            move();
            if(noBeepersPresent()) {
                putBeeper();
                move();
                findMiddle();
            }
        }
    }
    private void goToEndOfLine() throws Exception {
        while (frontIsClear() && noBeepersPresent()){
            move();
        }
    }


}
