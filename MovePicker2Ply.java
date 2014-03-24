public class MovePicker2Ply extends MovePicker {
    @Override
    public int pickMove(TetrisState currentState, int[][] legalMoves, UtilityCalculator utilityCalculator)  {
        int bestMoveIndex = 0;
		double bestMoveUtility = Double.NEGATIVE_INFINITY;
        
		for(int i = 0; i < legalMoves.length; i++) {    
            TetrisState predictedState1 = new TetrisState();   
            predictedState1.copyState(currentState);
            predictedState1.makeMove(i);
            int totalMoveUtility = 0;
            for(int j = 0; j < TetrisState.N_PIECES; j++) {
                predictedState1.setNextPiece(j);
                for(int k = 0; k < predictedState1.legalMoves().length; k++) {
                    TetrisState predictedState2 = new TetrisState();   
                    predictedState2.copyState(predictedState1);
                    predictedState2.makeMove(k);             
                    int moveUtility = utilityCalculator.getStateUtility(predictedState2);
                    totalMoveUtility += moveUtility;                           
                }
            }
            if(totalMoveUtility > bestMoveUtility) {
                bestMoveIndex = i;
                bestMoveUtility = totalMoveUtility;
            }
		}
		return bestMoveIndex;
    }
}