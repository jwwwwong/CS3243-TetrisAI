public class HeightUtility extends Utility {
    @Override
    public int evaluate(TetrisState predictedState) {
        int height = predictedState.getCurrentPieceLandingHeight() + predictedState.getPrevPieceHeight() / 2;
        return height;
    }
}
