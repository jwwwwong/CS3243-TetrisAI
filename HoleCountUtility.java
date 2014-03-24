/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author johnwong
 */
public class HoleCountUtility extends Utility {    
    @Override
    public int evaluate(TetrisState predictedState) {
        return getNumberOfHoles(predictedState.getField());
    }
     public static int getNumberOfHoles(int[][] field) {
        int totalHoles = 0;
        for(int col = 0; col < State.COLS; col++) {
            int colHoles = 0;
            boolean prevSquareIsHole = false;
            int currHoles = 0;
            for(int j = 0; j < State.ROWS; j++) {
                int currSquare = field[j][col];
                if(prevSquareIsHole && currSquare != 0) {
                    colHoles += currHoles;
                    currHoles = 0;
                }                    
                if(currSquare == 0) {
                    currHoles++;
                    prevSquareIsHole = true;
                }  else {
                    prevSquareIsHole = false;
                }
            }
            totalHoles += colHoles;
        }
        return totalHoles;
	}
}