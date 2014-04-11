public class HeightUtility extends Utility {
    @Override
    public double evaluate(TetrisState predictedState) {
        double height = predictedState.getCurrentPieceLandingHeight() + (predictedState.getPrevPieceHeight() - 1) / 2;
        return height;
    }
}
