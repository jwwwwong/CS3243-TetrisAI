
public class UtilityCalculator {
    private int _weights[] = {-1, 0};
    private Utility[] _utilityArray = new Utility[]{  
                                        new MaxHeightUtility(),
                                        new HoleCountUtility()
                                     };
    
    public int[] getWeights() {
        return _weights;
    }
    
    public void setWeights(int weights[]) {
        _weights = weights;
    }
    
    public Utility[] getUtilityArray() {
        return _utilityArray;
    }
    
    public void setUtilityArray(Utility[] utilityArray) {
        _utilityArray = utilityArray;
    }
    
    public int getMoveUtility(State currentState, int move) {
        TetrisState predictedState = new TetrisState();
        predictedState.copyState(currentState);
        predictedState.makeMove(move);
        int utility = 0;
        for(int i = 0; i < _utilityArray.length; i++) {
            utility += _weights[i] * _utilityArray[i].evaluate(predictedState);
        }
        return  utility;
    }   
}
