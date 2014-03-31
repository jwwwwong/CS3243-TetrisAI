
public class PlayerSkeleton {
    static double[] _weights = {-4.500158825082766, 3.4181268101392694, -3.2178882868487753, -9.348695305445199, -7.899265427351652, -3.3855972247263626};
    
    MovePicker _movePicker = new MovePicker1Ply();
    UtilityCalculator _utilityCalculator = new UtilityCalculator();
    {
        _utilityCalculator.setWeights(_weights);
    }
        
	private int pickMove(State s, int[][] legalMoves) {		
        return _movePicker.pickMove(s, legalMoves, _utilityCalculator);
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
        AiEvaluator evaluator = new RandomSequenceEvaluator();
 		Optimizer optimizer = new ParticleSwarmOptimizer();

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
			/*
            try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
            */
		}
		System.out.println("You have completed "+s.getRowsCleared()+" rows.");
    }
	
	public static void main(String[] args) {
        //runNormalGame();
		runOptimizer();
        //runMovePicker1Ply();
        //runMovePicker2Ply();
	}
	
}
