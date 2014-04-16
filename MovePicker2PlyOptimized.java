import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MovePicker2PlyOptimized extends MovePicker {

	ArrayList<Integer> previousBestMoveIndexList = new ArrayList<Integer>();
	private int round = 0;
	private static final int NUMBER_OF_BEST_1PLY = 5;
	private static final double FUTURE_MULTIPLYING_FACTOR = 0.6;
    
    @Override
	public int pickMove(TetrisState currentState, int[][] legalMoves, UtilityCalculator utilityCalculator)  {
		round = 1 - round;
		//this will make 'round' alternating between 0 and 1
		if(round == 0){
			//if round = 0, we don have to calculate 2ply again, 
			//we can just pick the move from what have been calculated before
			return previousBestMoveIndexList.get(currentState.getNextPiece());
		}
		int bestMoveIndex = 0;
		double bestMoveUtility = Double.NEGATIVE_INFINITY;
		//find all the 1ply Utility
		ArrayList<IndexValuePair> listOfMoveUtility = new ArrayList<IndexValuePair>();
		for (int i = 0; i < legalMoves.length; i++) {
			TetrisState predictedState = new TetrisState();
			predictedState.copyState(currentState);
			predictedState.makeMove(i);
			double moveUtility = utilityCalculator
					.getStateUtility(predictedState);
			listOfMoveUtility.add(new IndexValuePair(moveUtility, i));
		} 
		// sort utility
		Collections.sort(listOfMoveUtility, new Comparator<IndexValuePair>() {

			@Override
			public int compare(IndexValuePair arg0, IndexValuePair arg1) { 
				return (int) (arg1.compareFirstNumber(arg0));
			}

		});

		for (int index = 0; index < NUMBER_OF_BEST_1PLY; index++) {
			int bestUtilityIndex = listOfMoveUtility.get(index).index;
			TetrisState predictedState = new TetrisState();
			predictedState.copyState(currentState);
			predictedState.makeMove(bestUtilityIndex);
			double totalBestUtilityOfAllPieces = 0;
			//previousIndexListForThisMove is calculated and stored for future move
			ArrayList<Integer> previousIndexListForThisMove = new ArrayList<Integer>();
			for (int j = 0; j < TetrisState.N_PIECES; j++) {
				predictedState.setNextPiece(j);
				//add a ZERO value in first and edit later
				previousIndexListForThisMove.add(0);
				double bestUtility2Ply = Double.NEGATIVE_INFINITY;
				for (int k = 0; k < predictedState.legalMoves().length; k++) {
					TetrisState predictedState2ply = new TetrisState();
					predictedState2ply.copyState(predictedState);
					predictedState2ply.makeMove(k);
					double current2PlyMoveUtility = utilityCalculator
							.getStateUtility(predictedState2ply);
					if (current2PlyMoveUtility > bestUtility2Ply) {
						bestUtility2Ply = current2PlyMoveUtility;
						//update the best index for 1 of the 7 pieces
						previousIndexListForThisMove.set(j, k);
					}
				}
				totalBestUtilityOfAllPieces += bestUtility2Ply;
			}
			//take average of 7 pieces
			totalBestUtilityOfAllPieces /= TetrisState.N_PIECES; 
			totalBestUtilityOfAllPieces*= FUTURE_MULTIPLYING_FACTOR;
			//totalUtility is sum of 1ply Utility and 2ply Utility with multiplying factor
			double totalUtility = utilityCalculator
					.getStateUtility(predictedState) + totalBestUtilityOfAllPieces;
			
			//choose the best
			if (totalUtility > bestMoveUtility) {
				bestMoveIndex = bestUtilityIndex;
				bestMoveUtility = totalUtility;
				//if found the better set of 2ply moves, we save it for next round
				previousBestMoveIndexList = previousIndexListForThisMove;
			}
		}
		return bestMoveIndex;
	}    
    
    @Override
    public MovePicker copy() {
        return new MovePicker2PlyOptimized();
    }
}

class IndexValuePair {
	double value;
	int index;

	public IndexValuePair(double first, int second) {
		value = first;
		index = second;
	}

	public double compareFirstNumber(IndexValuePair anotherPair) {
		return value - anotherPair.value;
	}
}
