/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

public class RandomEvaluator extends Evaluator {

    @Override
    protected int numberOfTrials() {
        return 3;
    }

    @Override
    protected void alterPieceNumber(State s) {
    }
    
}
