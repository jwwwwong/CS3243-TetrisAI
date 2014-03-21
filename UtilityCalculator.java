
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

public class UtilityCalculator {
    private int _weights[] = {-1, 0};
    private final ArrayList<Utility> _utilityList = new ArrayList<>();
    {
        _utilityList.add(new MaxHeightUtility());
        _utilityList.add(new NumberOfHolesUtility());        
    }
    
    public int [] getWeights() {
        return _weights;
    }
    
    public void setWeights(int weights[]) {
        _weights = weights;
    }
    
    public int getMoveUtility(State currentState, int move) {
        TetrisState predictedState = new TetrisState();
        predictedState.copyState(currentState);
        predictedState.makeMove(move);
        int utility = 0;
        for(int i = 0; i < _utilityList.size(); i++) {
            utility += _weights[i] * _utilityList.get(i).evaluate(predictedState);
        }
        return  utility;
    }
    
    private int getMaxHeight(int[] top) {
        int maxHeight = 0;
        for(int i = 0; i < top.length; i++) {
            int currHeight = top[i];
            if(currHeight > maxHeight) {
                maxHeight = currHeight;
            }
        }
        return maxHeight;
	}
    
    private int getNumberOfHoles(int[][] field) {
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
    
    private abstract class Utility {
        public abstract int evaluate(TetrisState predictedState);
    }
     
    private class MaxHeightUtility extends Utility {
        @Override
        public int evaluate(TetrisState predictedState) {
            return getMaxHeight(predictedState.getTop());
        }
    }
    
    private class NumberOfHolesUtility extends Utility {
        @Override
        public int evaluate(TetrisState predictedState) {
            return getNumberOfHoles(predictedState.getField());
        }
    }
}
