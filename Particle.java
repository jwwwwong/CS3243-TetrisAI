import java.util.Random;


public class Particle implements Runnable{
     private final double [] curPosition;
     private int particleBestFitness;
     private final double[] curVelocity;
     
     private final int particleID;
     private double[] particleBestPosition;
     
     private double[] globalBestPostion;
     static private final double INERTIA_WEIGHT= 0.7;
     static private final double PERSONAL_WEIGHT=1.4;
     static private final double GLOBAL_WEIGHT=1.4;
     private boolean firstRun;
     
     AiEvaluator evaluator;
     private final int dimension;
     MovePicker movePicker;
     UtilityCalculator utilityCalculator;
      
     
    public Particle(int particleID,int dimension, double maxSearchRange, double minSearchRange, AiEvaluator evaluator, UtilityCalculator utilityCalculator, MovePicker movePicker) {
		this.dimension=dimension;
    	this.particleBestFitness=0;
    	firstRun=true;
    	this.particleID=particleID;
    	curPosition= new double[dimension];
    	curVelocity= new double[dimension];
    	particleBestPosition= new double [dimension];
    	
    	for(int i = 0; i < dimension; i++) {
    		curPosition[i]=minSearchRange+new Random().nextDouble()*(maxSearchRange-minSearchRange);
    	}
    	double rangeLength=maxSearchRange-minSearchRange;
    	for(int  i =0; i < dimension; i++) {
    		curVelocity[i]=-rangeLength+ new Random().nextDouble()*(rangeLength*2);
    	}
    	particleBestPosition=ArrayHandler.makeCopy(curPosition);
    	
    	this.evaluator = evaluator.copy();
		this.utilityCalculator = utilityCalculator.copy();
	    this.movePicker = movePicker.copy();
	} 
    
	@Override
	public void run() {
        if(!firstRun) {
            for(int i=0; i<dimension; i++) {
                curVelocity[i]=INERTIA_WEIGHT*curVelocity[i]+PERSONAL_WEIGHT*new Random().nextDouble()*(particleBestPosition[i]-curPosition[i])+GLOBAL_WEIGHT*new Random().nextDouble()*(globalBestPostion[i]-curPosition[i]);
                curPosition[i]+=curVelocity[i];
            }	    	
        }
	      
	    utilityCalculator.setWeights(curPosition);	    
	    int performance = evaluator.evaluateAveragePerformance(movePicker, utilityCalculator);	    
        if(performance>particleBestFitness) {
            particleBestFitness=performance;
            particleBestPosition=ArrayHandler.makeCopy(curPosition);
        }
	      
        firstRun=false;  	    
	}
	
	public int getParticleBestFitness() {
		return particleBestFitness;
	}
	public double[] getParticleBestPosition() {
		return particleBestPosition;
	}
	public void setGlobalBestPostion(double[] globalBestPostion) {
		this.globalBestPostion = ArrayHandler.makeCopy(globalBestPostion);
	}
	
     @Override
    public String toString() {
        String string="";
    	string+="Particle "+ particleID+" : "+ "Personal Best: "+ particleBestFitness+"\n";
    	 
    	return string;
    }

}
