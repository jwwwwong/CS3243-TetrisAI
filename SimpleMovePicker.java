/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

public class SimpleMovePicker extends MovePicker {
    public SimpleMovePicker(UtilityCalculator utilityCalculator) {
        super(utilityCalculator);
    }
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
