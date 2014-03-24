
public class PlayerSkeleton {
    static int[] _weights = {-37, -28, 5};
    MovePicker _movePicker = new MovePicker1Ply();
    {
        UtilityCalculator utilityCalculator = new UtilityCalculator();
        _movePicker.setUtilityCalculator(utilityCalculator);
    }
	private int pickMove(State s, int[][] legalMoves) {		
        return _movePicker.pickMove(s, legalMoves);
	}
    
    private static void runMovePicker(MovePicker movePicker) {        
        UtilityCalculator utilityCalculator = new UtilityCalculator();
        utilityCalculator.setWeights(_weights);
        AiEvaluator evaluator = new IncrementalSequenceEvaluator(); 		
 		int performance = evaluator.evaluateAveragePerformance(movePicker, utilityCalculator);
        System.out.print(performance);
        System.out.println();
        System.out.print("Weights used: ");
        ArrayHandler.print(_weights);
        System.out.println();        
    }
    
    private static void runMovePicker1Ply() {
        System.out.print("MovePicker1Ply performance: "); // performance refers to total number of rows cleared
        runMovePicker(new MovePicker1Ply());
        System.out.println();        
    }
    
    private static void runMovePicker2Ply() {
        System.out.print("MovePicker2Ply performance: ");
        runMovePicker(new MovePicker2Ply());
        System.out.println();        
    }
    
    private static void runOptimizer() {
        UtilityCalculator utilityCalculator = new UtilityCalculator();
        MovePicker movePicker = new MovePicker1Ply();
        AiEvaluator evaluator = new IncrementalSequenceEvaluator();
 		Optimizer optimizer = new HillClimbingOptimizer();

        optimizer.optimize(utilityCalculator, movePicker, evaluator);
        System.out.println();        
    }
    
    private static void runNormalGame() {
        State s = new State();
		new TFrame(s);
		PlayerSkeleton p = new PlayerSkeleton();
		while(!s.hasLost()) {
			s.makeMove(p.pickMove(s,s.legalMoves()));
			s.draw();
			s.drawNext(0,0);
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("You have completed "+s.getRowsCleared()+" rows.");
    }
	
	public static void main(String[] args) {
		//runOptimizer();
        runMovePicker1Ply();
        runMovePicker2Ply();
	}
	
}
