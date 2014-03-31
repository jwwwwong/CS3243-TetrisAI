public class MovePicker2PlyOptimized extends MovePicker {
    @Override
    public int pickMove(TetrisState currentState, int[][] legalMoves, UtilityCalculator utilityCalculator)  {
        return 0;
    }

    @Override
    public MovePicker copy() {
        return new MovePicker2PlyOptimized();
    }
}
