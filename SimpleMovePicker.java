
public class SimpleMovePicker extends MovePicker {
    @Override
    public int pickMove(State currentState, int[][] legalMoves)  {
        int bestMoveIndex = 0;
		double bestMoveUtility = Double.NEGATIVE_INFINITY;
		for(int i = 0; i < legalMoves.length; i++) {
			int moveUtility = _utilityCalculator.getMoveUtility(currentState, i);
			if(moveUtility > bestMoveUtility) {
				bestMoveIndex = i;
				bestMoveUtility = moveUtility;
			}
		}
		return bestMoveIndex;
    }
}
