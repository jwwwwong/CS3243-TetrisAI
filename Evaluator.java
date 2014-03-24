/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author johnwong
 */
public class Evaluator {    
    private static int _pieceNumber = 0;
    
    public static int evaluateAveragePerformance(MovePicker movePicker, int trials) {
        int performance = 0;
        for(int i = 0; i < trials; i++) {
            performance += evaluatePerformance(movePicker);
        }
        return (performance / trials);
    }
    
    private static int evaluatePerformance(MovePicker movePicker) {
        State s = new State();        
		while(!s.hasLost()) {
			s.makeMove(movePicker.pickMove(s,s.legalMoves()));
        }
        return s.getRowsCleared();
    }
    
    public static int evaluatePerformanceForNonRandomSequence(MovePicker movePicker) {        
        State s = new State();        
		while(!s.hasLost()) {
            _pieceNumber = getNextPieceNumber();            
            s.setNextPiece(_pieceNumber);
			s.makeMove(movePicker.pickMove(s,s.legalMoves()));
        }
        return s.getRowsCleared();
    }
    
    private static int getNextPieceNumber() {
        int increment = 1;
        if(_pieceNumber % 2 == 0) {
            increment = 2;            
        }
        if(_pieceNumber % 4 == 0) {
            increment = 3;            
        }
        if(_pieceNumber % 5 == 0) {
            increment = 4;            
        }
        if(_pieceNumber % 7 == 0) {
            increment = 5;            
        }
        if(_pieceNumber % 13 == 0) {
            increment = 6;            
        }
        if(_pieceNumber % 21 == 0) {
            increment = 8;            
        }
        if(_pieceNumber % 29 == 0) {
            increment = 9;            
        }        
        if(_pieceNumber % 31 == 0) {
            increment = 10;            
        }
        
        return (_pieceNumber + increment) % 7;
    }
}
