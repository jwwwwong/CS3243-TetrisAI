public class SurvivalUtility extends Utility {

    @Override
    public double evaluate(TetrisState predictedState) {
        if(predictedState.hasLost()) {
            return 0;
        }
        return 1;
    }

}
