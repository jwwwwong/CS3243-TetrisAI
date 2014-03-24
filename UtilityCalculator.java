
public class UtilityCalculator {
    private int _weights[] = {0, 0, 0};
    private Utility[] _utilityArray = new Utility[]{  
                                        new MaxHeightUtility(),
                                        new HoleCountUtility(),
                                        new RowsClearedUtility()
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
    
    public int getStateUtility(TetrisState predictedState) {
        int utility = 0;
        for(int i = 0; i < _utilityArray.length; i++) {
            utility += _weights[i] * _utilityArray[i].evaluate(predictedState);
        }
        return  utility;
    }   
}
