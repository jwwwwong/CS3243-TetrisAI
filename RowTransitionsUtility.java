public class RowTransitionsUtility extends  Utility {
    @Override
    public int evaluate(TetrisState predictedState) {
        int rowTransitions = 0;
        boolean isPrevCellFilled = false;
        int[][] field = predictedState.getField();
        for(int i = 0; i < field.length; i++) {
            int[] row = field[i];
            isPrevCellFilled = false;
            if(row[0] != 0) {
                isPrevCellFilled = true;
            }
            for(int j = 1; j < row.length; j++) {
                boolean isCurrentCellFilled = false;
                if(row[j] != 0) {
                    isCurrentCellFilled = true;
                }
                if(isPrevCellFilled != isCurrentCellFilled) {
                    rowTransitions++;
                }
                isPrevCellFilled = isCurrentCellFilled;
            }
        }
        return rowTransitions;
    }    
}
