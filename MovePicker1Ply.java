
public class MovePicker1Ply extends MovePicker {
    @Override
    public int pickMove(TetrisState currentState, int[][] legalMoves, UtilityCalculator utilityCalculator)  {
        int bestMoveIndex = 0;
		double bestMoveUtility = Double.NEGATIVE_INFINITY;
        
		for(int i = 0; i < legalMoves.length; i++) {    
            TetrisState predictedState = new TetrisState();   
            predictedState.copyState(currentState);
            predictedState.makeMove(i);
			double moveUtility = utilityCalculator.getStateUtility(predictedState);
			if(moveUtility > bestMoveUtility) {
				bestMoveIndex = i;
				bestMoveUtility = moveUtility;
			}
		}
		return bestMoveIndex;
    }

    @Override
    public MovePicker copy() {
        return new MovePicker1Ply();
    }
}
