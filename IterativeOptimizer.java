/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

public class IterativeOptimizer extends Optimizer {
    @Override
    public void optimize() {
        UtilityCalculator utilityCalculator = new UtilityCalculator();
        MovePicker movePicker = new SimpleMovePicker(utilityCalculator);
        int[] bestWeights = new int[utilityCalculator.getWeights().length];
        int testCount = 0;
        int bestPerformance = 0;
        for(int i = -10; i < 10; i++) {
            for(int j = -10; j < 10; j++) {
                int[] weights = {i, j};
                utilityCalculator.setWeights(weights);
                int performance = Evaluator.evaluatePerformanceForNonRandomSequence(movePicker);
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
