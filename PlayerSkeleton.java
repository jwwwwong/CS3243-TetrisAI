
public class PlayerSkeleton {
    static int[] _weights = {-17, -2, 0};
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
        AiEvaluator evaluator = new IncrementalSequenceEvalutor(); 		
 		int performance = evaluator.evaluateAveragePerformance(movePicker, utilityCalculator);
        System.out.print(performance);
        System.out.println();
    }
    
    private static void runMovePicker1Ply() {
        System.out.print("MovePicker1Ply rows cleared: ");
        runMovePicker(new MovePicker1Ply());
    }
    
    private static void runMovePicker2Ply() {
        System.out.print("MovePicker2Ply rows cleared: ");
        runMovePicker(new MovePicker2Ply());
    }
    
    private static void runOptimizer() {
        UtilityCalculator utilityCalculator = new UtilityCalculator();
        MovePicker movePicker = new MovePicker1Ply();
        AiEvaluator evaluator = new IncrementalSequenceEvalutor();
 		Optimizer optimizer = new HillClimbingOptimizer();

        optimizer.optimize(utilityCalculator, movePicker, evaluator);
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
		runOptimizer();
        runMovePicker1Ply();
        runMovePicker2Ply();
	}
	
}
