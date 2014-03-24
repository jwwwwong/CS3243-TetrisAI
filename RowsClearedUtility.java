public class RowsClearedUtility extends Utility {
    @Override
    public int evaluate(TetrisState predictedState) {
        return predictedState.getCurrentRowsCleared();
    }
}
