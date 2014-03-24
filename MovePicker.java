
public abstract class MovePicker {
    public int pickMove(State currentState, int[][] legalMoves, UtilityCalculator utilityCalculator) {
        TetrisState t = new TetrisState();
        t.copyState(currentState);
        return pickMove(t, legalMoves, utilityCalculator);
    }
    public abstract int pickMove(TetrisState currentState, int[][] legalMoves, UtilityCalculator utilityCalculator);
}
