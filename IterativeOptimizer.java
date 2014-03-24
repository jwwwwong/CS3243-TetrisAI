/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

public class IterativeOptimizer extends Optimizer {
    @Override
    public void optimize(MovePicker movePicker, Evaluator evaluator) {
        UtilityCalculator utilityCalculator = new UtilityCalculator();
        movePicker.setUtilityCalculator(utilityCalculator);
        int[] bestWeights = new int[utilityCalculator.getWeights().length];
        int testCount = 0;
        int bestPerformance = 0;
        for(int i = -10; i < 10; i++) {
            for(int j = -10; j < 10; j++) {
                int[] weights = {i, j};
                utilityCalculator.setWeights(weights);
                int performance = evaluator.evaluateAveragePerformance(movePicker, 1);
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
