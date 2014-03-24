
public class IncrementalSequenceEvaluator extends AiEvaluator {
    private int _pieceNumber = 0;
    boolean _shouldIncrement = false;
    
    @Override
    protected int numberOfTrials() {
        return 1;
    }
    
    @Override
    protected void alterPieceNumber(TetrisState s) {
        updatePieceNumber();
        s.setNextPiece(_pieceNumber);
    }
    
    private void updatePieceNumber() {
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
            increment = 7;            
        }
        if(_pieceNumber % 29 == 0) {
            increment = 8;            
        }        
        if(_pieceNumber % 31 == 0) {
            increment = 9;            
        }
        
        _pieceNumber = (_pieceNumber + increment) % 7;
        
        if(_shouldIncrement) {
            _pieceNumber++;
            _shouldIncrement = false;
        }
        if(_pieceNumber == _pieceNumber) {
            _shouldIncrement = true;
        }
    }

    @Override
    protected void resetPieceNumber() {
        _pieceNumber = 0;
        _shouldIncrement = false;
    }
}
