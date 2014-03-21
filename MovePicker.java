/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

public abstract class MovePicker {
    UtilityCalculator _utilityCalculator;
    public MovePicker(UtilityCalculator utilityCalculator) {
        _utilityCalculator = utilityCalculator;
    }
    public abstract int pickMove(State currentState, int[][] legalMoves);
}
