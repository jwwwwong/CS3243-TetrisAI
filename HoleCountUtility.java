
public class HoleCountUtility extends Utility {    
    @Override
    public double evaluate(TetrisState predictedState) {        
        int totalHoles = 0;
        int[][] field = predictedState.getField();
        int[] top = predictedState.getTop();
        for(int colIndex = 0; colIndex < State.COLS; colIndex++) {         
            for(int rowIndex = top[colIndex] - 1; rowIndex >= 0; rowIndex--) {
                boolean currSquareIsFilled = false;
                if(field[rowIndex][colIndex] != 0) {
                    currSquareIsFilled = true;
                }
                if(!currSquareIsFilled) {
                    totalHoles++;
                }
            }
        }
        return totalHoles;
    }
    
}