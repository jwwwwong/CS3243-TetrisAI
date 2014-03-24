
public class RandomEvaluator extends Evaluator {

    @Override
    protected int numberOfTrials() {
        return 3;
    }

    @Override
    protected void alterPieceNumber(State s) {
    }
    
}
