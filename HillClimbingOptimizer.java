
public class HillClimbingOptimizer extends Optimizer {    
    
    final int MAX_ITERATIONS = 10;
    final int MAX_TRIAL_COUNT = 10;
    @Override
    public void optimize(UtilityCalculator utilityCalculator, MovePicker movePicker, AiEvaluator evaluator) {
        int iterationCount = 0;
        
        int currentUtilityIndex = 0;
        
        int[] weights = utilityCalculator.getWeights();       
        int utilityCount = weights.length;
        int[] bestWeights = ArrayHandler.makeCopy(weights);
        
        int previousPerformance = 0;
        int bestPerformance = 0;
               
        while(iterationCount < MAX_ITERATIONS) {
            for(int i = 0; i < 2; i++) {
                int trialCount = 0;
                while(true) {
                    if(i == 0) {
                        weights[currentUtilityIndex]++;
                    } else {
                        weights[currentUtilityIndex]--;
                    }
                    utilityCalculator.setWeights(weights);
                    int performance = testPerformance(weights, utilityCalculator, movePicker, evaluator);
                    if(performance <= previousPerformance) {
                        trialCount++;
                    }
                    if(trialCount >= MAX_TRIAL_COUNT) {
                        weights = ArrayHandler.makeCopy(bestWeights);
                        ArrayHandler.print(weights);
                        break;
                    }
                    if(performance > bestPerformance) {
                        bestPerformance = performance;
                        bestWeights = ArrayHandler.makeCopy(weights);
                    }

                    previousPerformance = performance;
                }
            }
            
            currentUtilityIndex = (currentUtilityIndex + 1) % utilityCount;
            iterationCount++;            
        }
        
        System.out.println("Best performance " + bestPerformance);
        System.out.print("Best weights: ");
        ArrayHandler.print(bestWeights);
    }
    
    private int testPerformance(int[] weights, UtilityCalculator utilityCalculator, MovePicker movePicker, AiEvaluator evaluator) {
        utilityCalculator.setWeights(weights);
        int performance = evaluator.evaluateAveragePerformance(movePicker, utilityCalculator);
        System.out.print("weights: ");
        ArrayHandler.print(weights);
        System.out.println("performance: " + performance);
        return performance;
    }
}
