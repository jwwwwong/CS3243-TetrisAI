
public abstract class MovePicker {
    UtilityCalculator _utilityCalculator = null;
    public void setUtilityCalculator(UtilityCalculator utilityCalculator) {
        _utilityCalculator = utilityCalculator;
    }
    public abstract int pickMove(State currentState, int[][] legalMoves);
}
