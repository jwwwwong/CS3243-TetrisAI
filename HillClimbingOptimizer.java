
public class HillClimbingOptimizer extends Optimizer {    
    
    final int MAX_ITERATIONS = 5;
    final int MAX_TRIAL_COUNT = 10;
    @Override
    public void optimize(UtilityCalculator utilityCalculator, MovePicker movePicker, Evaluator evaluator) {
        int iterationCount = 0;
        
        int currentUtilityIndex = 0;
        
        int[] weights = utilityCalculator.getWeights();       
        int utilityCount = weights.length;
        int[] bestWeights = weights;
        
        int previousPerformance = 0;
        int initialUtilityWeight = 0; 
        int bestPerformance = 0;
               
        while(iterationCount < MAX_ITERATIONS) {
            initialUtilityWeight = weights[currentUtilityIndex];
            
            int incrementPerformance = 0;
            int beforeIncrementPerformance = previousPerformance;
            int incrementTrialCount = 0;
            
            while(true) {
                weights[currentUtilityIndex]++;
                System.out.println("increment trial count " + incrementTrialCount);
                incrementPerformance = testPerformance(weights, utilityCalculator, movePicker, evaluator);
                if(incrementPerformance <= previousPerformance) {
                    incrementTrialCount++;
                }
                if(incrementTrialCount >= MAX_TRIAL_COUNT) {
                    break;
                }
                previousPerformance = incrementPerformance;
            }
            
            if(incrementPerformance <= beforeIncrementPerformance) {
                weights[currentUtilityIndex] = initialUtilityWeight;
                previousPerformance = beforeIncrementPerformance;
            }
            
            int decrementPerformance = 0;
            int beforeDecrementPerformance = previousPerformance;            
            int decrementTrialCount = 0;
            
            while(true) {
                int trialCount = 0;
                weights[currentUtilityIndex]--;
                System.out.println("decrement trial count " + incrementTrialCount);
                decrementPerformance = testPerformance(weights, utilityCalculator, movePicker, evaluator);
                if(decrementPerformance <= previousPerformance) {
                    decrementTrialCount++;
                } 
                if(decrementTrialCount >= MAX_TRIAL_COUNT) {
                    break;
                }
                previousPerformance = decrementPerformance;
            }
            if(decrementPerformance <= beforeDecrementPerformance) {
                weights[currentUtilityIndex] = initialUtilityWeight;
                previousPerformance = beforeDecrementPerformance;
            }
            
            if(previousPerformance > bestPerformance) {
                bestPerformance = previousPerformance;
                bestWeights = weights;
            }
            
            currentUtilityIndex = (currentUtilityIndex + 1) % utilityCount;
            iterationCount++;            
        }
        
        System.out.println("Best performance " + bestPerformance);
        System.out.print("Best weights: ");
        Printer.print(bestWeights);
    }
    
    private int testPerformance(int[] weights, UtilityCalculator utilityCalculator, MovePicker movePicker, Evaluator evaluator) {
        utilityCalculator.setWeights(weights);
        int performance = evaluator.evaluateAveragePerformance(movePicker, utilityCalculator);
        Printer.print(weights);
        System.out.println("performance " + performance);
        return performance;
    }
}
