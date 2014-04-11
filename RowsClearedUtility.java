public class RowsClearedUtility extends Utility {
    @Override
    public double evaluate(TetrisState predictedState) {
        return predictedState.getCurrentRowsCleared();
    }
}
