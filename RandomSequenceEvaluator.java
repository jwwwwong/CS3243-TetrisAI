
public class RandomSequenceEvaluator extends AiEvaluator {

    @Override
    protected int numberOfTrials() {
        return 5;
    }

    @Override
    protected void alterPieceNumber(TetrisState s) {
    }

    @Override
    protected void resetPieceNumber() {
    }
    
}
