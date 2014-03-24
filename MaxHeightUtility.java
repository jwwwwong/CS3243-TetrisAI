/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author johnwong
 */
public class MaxHeightUtility extends Utility {
    @Override
    public int evaluate(TetrisState predictedState) {
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
