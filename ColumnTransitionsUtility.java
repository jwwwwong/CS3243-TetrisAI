public class ColumnTransitionsUtility extends  Utility {
    @Override
    public int evaluate(TetrisState predictedState) {
        int columnTransitions = 0;
        boolean isPrevCellFilled = false;
        int[][] field = predictedState.getField();
        for(int colIndex = 0; colIndex < TetrisState.COLS; colIndex++) {
            isPrevCellFilled = false;
            if(field[0][colIndex] != 0) {
                isPrevCellFilled = true;
            }
            for(int rowIndex = 1; rowIndex < TetrisState.ROWS; rowIndex++) {
                boolean isCurrentCellFilled = false;
                if(field[rowIndex][colIndex] != 0) {
                    isCurrentCellFilled = true;
                }
                if(isPrevCellFilled != isCurrentCellFilled) {
                    columnTransitions++;
                }
                isPrevCellFilled = isCurrentCellFilled;
            }
        }
        return columnTransitions;
    }    
}
