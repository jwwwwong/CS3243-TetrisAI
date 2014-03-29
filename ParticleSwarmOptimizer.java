
public class ParticleSwarmOptimizer extends Optimizer {

	final static int MAX_ITERATION=100000;
	final static int NUM_PARTICLE=8;
	final static double MIN_SEARCH_RANGE=-100;
	final static double MAX_SEARCH_RANGE=100;
	private int globalBestFitness=-1;
	private double [] globalBestPosition;
	private Particle[] particle;


	@Override
	public void optimize(UtilityCalculator utilityCalculator,
			MovePicker movePicker, AiEvaluator evaluator) {
		// TODO Auto-generated method stub
		int dimension= utilityCalculator.getWeights().length;
		globalBestPosition= new double[dimension];
		globalBestFitness=0;
		particle = new Particle[NUM_PARTICLE];
		for(int i=0; i<NUM_PARTICLE; i++)
		{
			particle[i]= new Particle(i, dimension, MAX_SEARCH_RANGE, MIN_SEARCH_RANGE);
		}

		Thread [] particleThread =new Thread[NUM_PARTICLE];
		for(int i=0; i<MAX_ITERATION; i++)
		{

			for(int j=0; j<NUM_PARTICLE; j++)
			{
				particleThread[j]= new Thread(particle[j]);
				particleThread[j].run();
				//				particle[j].run();
			}


			for(int j=0; j<NUM_PARTICLE; j++)
			{
				{
					try {
						particleThread[j].join();


						int personalBest= particle[j].getParticleBestFitness();
						if(personalBest>globalBestFitness)
						{
							globalBestFitness=personalBest;
							globalBestPosition=ArrayHandler.makeCopy(particle[j].getParticleBestPosition());

						}
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				} 


			}

			for(int j=0; j<NUM_PARTICLE; j++)
			{
				particle[j].setGlobalBestPostion(globalBestPosition);
			}
			System.out.println("Global best: " +globalBestFitness);
			System.out.println("Global best parameters:");
			for(int j=0; j<dimension; j++)
			{
				System.out.print(globalBestPosition[j]);
				if(j!=dimension-1)
				{
					System.out.print(",");
				}
				else
				{
					System.out.println();
				}

			}

		}

	}



}



