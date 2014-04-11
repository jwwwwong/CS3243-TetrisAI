
public class MaxHeightUtility extends Utility {
    @Override
    public double evaluate(TetrisState predictedState) {
        return getMaxHeight(predictedState.getTop());
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
    
}
