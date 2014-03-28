
public class UtilityCalculator {
    private double[] _weights = {0, 0, 0, 0, 0, 0};
    private Utility[] _utilityArray = new Utility[] {  
                                        new HeightUtility(),
                                        new RowsClearedUtility(),
                                        new RowTransitionsUtility(),
                                        new ColumnTransitionsUtility(),
                                        new HoleCountUtility(),
                                        new WellSumUtility()
                                     };
    
    public double[] getWeights() {
        return ArrayHandler.makeCopy(_weights);
    }
    
    public void setWeights(double weights[]) {
        _weights = ArrayHandler.makeCopy(weights);
    }
    
    public double getStateUtility(TetrisState predictedState) {
        double utility = 0;
        for(int i = 0; i < _utilityArray.length; i++) {
            utility += _weights[i] * _utilityArray[i].evaluate(predictedState);
        }
        return  utility;
    }   
}
