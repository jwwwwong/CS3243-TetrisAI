
public class UtilityCalculator {
    private int[] _weights = {0, 0, 0};
    private Utility[] _utilityArray = new Utility[] {  
                                        new MaxHeightUtility(),
                                        new HoleCountUtility(),
                                        new RowsClearedUtility()
                                     };
    
    public int[] getWeights() {
        return ArrayHandler.makeCopy(_weights);
    }
    
    public void setWeights(int weights[]) {
        _weights = ArrayHandler.makeCopy(weights);
    }
    
    public int getStateUtility(TetrisState predictedState) {
        int utility = 0;
        for(int i = 0; i < _utilityArray.length; i++) {
            utility += _weights[i] * _utilityArray[i].evaluate(predictedState);
        }
        return  utility;
    }   
}
