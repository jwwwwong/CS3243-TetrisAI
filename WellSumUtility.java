public class WellSumUtility extends Utility {
    @Override
    public int evaluate(TetrisState predictedState) {
        int wellSum = 0;
        int[][] field = predictedState.getField();
        int[] top = predictedState.getTop();
        for(int colIndex = 0; colIndex < TetrisState.COLS; colIndex++) {
            for(int rowIndex = top[colIndex]-1; rowIndex > 0; rowIndex--) {
                boolean isWellSquare = false;
                boolean isSquareEmpty = false;
                if(field[rowIndex][colIndex] != 0) {
                    isSquareEmpty = true;
                }
                boolean isSquareOnLeftFilled = false;
                boolean isSquareOnRightFilled = false;
                if(colIndex == 0 || field[rowIndex][colIndex - 1] != 0) {
                    isSquareOnLeftFilled = true;
                }
                if(colIndex == TetrisState.COLS-1 || field[rowIndex][colIndex + 1] != 0) {
                    isSquareOnRightFilled = true;
                }
                if(!isSquareEmpty && isSquareOnLeftFilled && isSquareOnRightFilled) {
                    wellSum++;
                }
            }
        }
        return wellSum;
    }
}
