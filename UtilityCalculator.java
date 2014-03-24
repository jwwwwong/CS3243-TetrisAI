
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
        _utilityList.add(new HoleCountUtility());        
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
}
