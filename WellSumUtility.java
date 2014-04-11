public class WellSumUtility extends Utility {
    @Override
    public double evaluate(TetrisState predictedState) {
        int wellSum = 0;
        int[][] field = predictedState.getField();
        int[] top = predictedState.getTop();
        for(int colIndex = 0; colIndex < TetrisState.COLS; colIndex++) {
            int rowStartIndex = 0;
            if(colIndex == 0) {
                rowStartIndex = top[1];
            } else if(colIndex == TetrisState.COLS - 1) {
                rowStartIndex = top[TetrisState.COLS - 2];
            } else {
                rowStartIndex = top[colIndex - 1];
                if(top[colIndex + 1] < rowStartIndex) {
                    rowStartIndex = top[colIndex + 1];
                }
            }
            rowStartIndex = rowStartIndex - 1;
            for(int rowIndex = rowStartIndex; rowIndex >= 0; rowIndex--) {                                
                boolean isSquareEmpty = true;
                if(field[rowIndex][colIndex] != 0) {
                    isSquareEmpty = false;
                }
                boolean isSquareOnLeftFilled = false;
                boolean isSquareOnRightFilled = false;
                if(colIndex == 0 || field[rowIndex][colIndex - 1] != 0) {
                    isSquareOnLeftFilled = true;
                }
                if(colIndex == TetrisState.COLS-1 || field[rowIndex][colIndex + 1] != 0) {
                    isSquareOnRightFilled = true;
                }
                if(isSquareEmpty && isSquareOnLeftFilled && isSquareOnRightFilled) {                    
                    wellSum++;
                    for(int wellIndex = rowIndex - 1; wellIndex >= 0; wellIndex--) {
                        if(field[wellIndex][colIndex] == 0) {
                            wellSum++;
                        } else {
                            break;
                        }
                    }
                }
            }
        }
        return wellSum;
    }
}
