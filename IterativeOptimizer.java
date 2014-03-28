
public class IterativeOptimizer extends Optimizer {
    @Override
    public void optimize(UtilityCalculator utilityCalculator, MovePicker movePicker, AiEvaluator evaluator) {
        double[] bestWeights = new double[utilityCalculator.getWeights().length];
        int testCount = 0;
        int bestPerformance = 0;
        for(int i = -10; i < 10; i++) {
            for(int j = -10; j < 10; j++) {
                double[] weights = {i, j};
                utilityCalculator.setWeights(weights);
                int performance = evaluator.evaluateAveragePerformance(movePicker, utilityCalculator);
                if(performance > bestPerformance) {
                    bestPerformance = performance;
                    bestWeights = weights;
                }
                System.out.println("weights " +i + " " + j + ": " + performance);
                testCount++;
            }
        }
        System.out.println("Best performance " + bestPerformance);
        System.out.println("Best weights: " + bestWeights[0] + " " + bestWeights[1]);
    }
}
