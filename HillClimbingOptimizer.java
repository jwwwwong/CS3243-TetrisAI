
public class HillClimbingOptimizer extends Optimizer {    
    
    final int MAX_ITERATIONS = 10;
    final int MAX_TRIAL_COUNT = 20;
    final int PROBABILITY_FACTOR = 7;
    
    @Override
    public void optimize(UtilityCalculator utilityCalculator, MovePicker movePicker, AiEvaluator evaluator) {
        int iterationCount = 0;
        
        int currentUtilityIndex = 0;
        
        int[] weights = {-12, -7, 6};// utilityCalculator.getWeights();       
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
                    if(performance == previousPerformance) {
                        if((int)(Math.random()*PROBABILITY_FACTOR) == 1) {
                            bestWeights[currentUtilityIndex] = weights[currentUtilityIndex];
                        }
                    }
                    if(trialCount >= MAX_TRIAL_COUNT) {
                        weights = ArrayHandler.makeCopy(bestWeights);
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
        
        System.out.println("Hill climbing best performance " + bestPerformance);
        System.out.print("Hill climbing best weights: ");
        ArrayHandler.print(bestWeights);
    }
    
    private int testPerformance(int[] weights, UtilityCalculator utilityCalculator, MovePicker movePicker, AiEvaluator evaluator) {
        utilityCalculator.setWeights(weights);
        int performance = evaluator.evaluateAveragePerformance(movePicker, utilityCalculator);
        System.out.println("Performance: " + performance);
        System.out.print("Weights used: ");
        ArrayHandler.print(weights);
        System.out.println();
        return performance;
    }
}
