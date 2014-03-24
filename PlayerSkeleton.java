
public class PlayerSkeleton {
    MovePicker _movePicker = new SimpleMovePicker();
    {
        UtilityCalculator utilityCalculator = new UtilityCalculator();
        _movePicker.setUtilityCalculator(utilityCalculator);
    }
	private int pickMove(State s, int[][] legalMoves) {		
        return _movePicker.pickMove(s, legalMoves);
	}
    
    private static void runOptimizer() {
        UtilityCalculator utilityCalculator = new UtilityCalculator();
        MovePicker movePicker = new SimpleMovePicker();
        Evaluator evaluator = new IncrementalSequenceEvalutor();
 		Optimizer optimizer = new IterativeOptimizer();

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
	}
	
}
