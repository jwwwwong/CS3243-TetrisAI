/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

public class Evaluator {            
    public int evaluateAveragePerformance(MovePicker movePicker, int trials) {
        int performance = 0;
        for(int i = 0; i < trials; i++) {
            performance += evaluatePerformance(movePicker);
        }
        return (performance / trials);
    }
    
    private int evaluatePerformance(MovePicker movePicker) {
        State s = new State();        
		while(!s.hasLost()) {
            alterPieceNumber(s);
			s.makeMove(movePicker.pickMove(s,s.legalMoves()));
        }
        return s.getRowsCleared();
    }       
    
    protected void alterPieceNumber(State s) {
    }
}
