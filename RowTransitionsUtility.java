public class RowTransitionsUtility extends  Utility {
    @Override
    public double evaluate(TetrisState predictedState) {
        int rowTransitions = 0;
        boolean isPrevCellFilled = true;
        int[][] field = predictedState.getField();
        for(int i = 0; i < TetrisState.ROWS - 1; i++) {
            int[] row = field[i];
            for(int j = 0; j < TetrisState.COLS; j++) {
                boolean isCurrentCellFilled = false;
                if(row[j] != 0) {
                    isCurrentCellFilled = true;
                }
                if(isPrevCellFilled != isCurrentCellFilled) {
                    rowTransitions++;
                }
                isPrevCellFilled = isCurrentCellFilled;
            }
            if(!isPrevCellFilled) {
                rowTransitions++;
            }
            isPrevCellFilled = true;
        }
        return rowTransitions;
    }    
}
