
public class RandomSequenceEvaluator extends AiEvaluator {

    @Override
    protected int numberOfTrials() {
        return 1;
    }

    @Override
    protected void alterPieceNumber(TetrisState s) {
    }

    @Override
    protected void resetPieceNumber() {
    }

    @Override
    protected AiEvaluator copy() {
        return new RandomSequenceEvaluator();
    }
}
