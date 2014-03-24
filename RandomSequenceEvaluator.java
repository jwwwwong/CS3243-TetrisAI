
public class RandomSequenceEvaluator extends AiEvaluator {

    @Override
    protected int numberOfTrials() {
        return 3;
    }

    @Override
    protected void alterPieceNumber(State s) {
    }
    
}
