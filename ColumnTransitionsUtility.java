public class ColumnTransitionsUtility extends  Utility {
    @Override
    public double evaluate(TetrisState predictedState) {
        int columnTransitions = 0;
        boolean isPrevCellFilled = true;
        int[][] field = predictedState.getField();
        for(int colIndex = 0; colIndex < TetrisState.COLS; colIndex++) {
            int transitions = 0;
            for(int rowIndex = 0; rowIndex < TetrisState.ROWS - 1; rowIndex++) {
                boolean isCurrentCellFilled = false;
                if(field[rowIndex][colIndex] != 0) {
                    isCurrentCellFilled = true;
                }
                if(isPrevCellFilled != isCurrentCellFilled) {
                    columnTransitions++;
                    transitions++;
                }
                isPrevCellFilled = isCurrentCellFilled;
            }
            isPrevCellFilled = true;
        }
        return columnTransitions;
    }    
}
