
public class HillClimbingOptimizer extends Optimizer {
    final int MAX_ITERATIONS = 1000000;
    @Override
    public void optimize(UtilityCalculator utilityCalculator, MovePicker movePicker, Evaluator evaluator) {
        int iterationCount = 0;
        int currentUtilityIndex = 0;
        boolean performanceImproved = false;        
        int[] bestWeights = new int[utilityCalculator.getWeights().length];                
        int previousPerformance = evaluator.evaluateAveragePerformance(movePicker, utilityCalculator);
        while(iterationCount < MAX_ITERATIONS) {
            
        }
    }
}
