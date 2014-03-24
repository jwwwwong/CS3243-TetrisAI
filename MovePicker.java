
public abstract class MovePicker {
    UtilityCalculator _utilityCalculator = null;
    public void setUtilityCalculator(UtilityCalculator utilityCalculator) {
        _utilityCalculator = utilityCalculator;
    }
    public int pickMove(State currentState, int[][] legalMoves) {
        TetrisState t = new TetrisState();
        t.copyState(currentState);
        return pickMove(t, legalMoves);
    }
    public abstract int pickMove(TetrisState currentState, int[][] legalMoves);
}
