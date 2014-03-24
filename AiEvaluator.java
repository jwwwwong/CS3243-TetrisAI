
public abstract class AiEvaluator {            
    public int evaluateAveragePerformance(MovePicker movePicker, UtilityCalculator utilityCalculator) {
        int performance = 0;
        int trials = numberOfTrials();
        for(int i = 0; i < trials; i++) {
            performance += evaluatePerformance(movePicker, utilityCalculator);
        }
        return (performance / trials);
    }
    
    protected abstract int numberOfTrials();
    
    private int evaluatePerformance(MovePicker movePicker, UtilityCalculator utilityCalculator) {
        resetPieceNumber();
        TetrisState s = new TetrisState();
		while(!s.hasLost()) {
            alterPieceNumber(s);
            int[][] legalMoves = s.legalMoves();
            int move = movePicker.pickMove(s, legalMoves, utilityCalculator);
            s.makeMove(move);
        }
        int performance = s.getRowsCleared();
        return performance;
    }       
    
    protected abstract void resetPieceNumber();
    protected abstract void alterPieceNumber(TetrisState s);
}
