
public abstract class Optimizer {   
    protected Storage storage = new Storage();
    public abstract void optimize(UtilityCalculator utilityCalculator, MovePicker movePicker, AiEvaluator evaluator);    
}
