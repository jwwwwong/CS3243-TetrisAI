import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;


public class Particle implements Runnable{
     private double [] curPosition;
     private int particleBestFitness;
     private double[] curVelocity;
     
     private int particleID;
     private double[] particleBestPosition;
     
     private double[] globalBestPostion;
     static private final double INERTIA_WEIGHT= 0.7;
     static private final double PERSONAL_WEIGHT=1.4;
     static private final double GLOBAL_WEIGHT=1.4;
     private boolean firstRun;
     
     AiEvaluator evaluator;
     private int dimension;
     MovePicker movePicker;
     UtilityCalculator utilityCalculator;
      
     
    public Particle(int particleID,int dimension, double maxSearchRange, double minSearchRange) {
		// TODO Auto-generated constructor stub
    	
    	this.dimension=dimension;
    	this.particleBestFitness=0;
    	firstRun=true;
    	this.particleID=particleID;
    	curPosition= new double[dimension];
    	curVelocity= new double[dimension];
    	particleBestPosition= new double [dimension];
    	
    	for(int i=0; i<dimension; i++)
    	{
    		curPosition[i]=minSearchRange+new Random().nextDouble()*(maxSearchRange-minSearchRange);
    	}
    	double rangeLength=maxSearchRange-minSearchRange;
    	for(int i=0; i<dimension; i++)
    	{
    		curVelocity[i]=-rangeLength+ new Random().nextDouble()*(rangeLength*2);
    	}
    	particleBestPosition=ArrayHandler.makeCopy(curPosition);
    	
    	evaluator = new RandomSequenceEvaluator();
		 utilityCalculator = new UtilityCalculator();
	      movePicker = new MovePicker1Ply();
    	
    	
    	
	} 
    
    

	@Override
	public void run() {
		// TODO Auto-generated method stub
		  
	      
	      if(!firstRun)
	      {
	    	  for(int i=0; i<dimension; i++)
	    	  {   //update velocity
	    		  curVelocity[i]=INERTIA_WEIGHT*curVelocity[i]+PERSONAL_WEIGHT*new Random().nextDouble()*(particleBestPosition[i]-curPosition[i])+GLOBAL_WEIGHT*new Random().nextDouble()*(globalBestPostion[i]-curPosition[i]);
	    		  //update position
	    		  curPosition[i]+=curVelocity[i];
	    	  }
	    	
	      }
//	        File file = new File("C:/Users/A0081205/Dropbox/CS3243 workplace/good.txt");
//	      
//	    	if (!file.exists()) {
//				try {
//					file.createNewFile();
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
// 
//			
//	        
//	        String string="current parameters:";
//			for(int j=0; j<dimension; j++)
//			{
//				string+=curPosition[j];
//				if(j!=dimension-1)
//				{
//					string+=",";
//				}
//				else
//				{
//					string+="\n";
//				}
//					
//			}
//			System.out.println(string);
			
//			FileWriter fw;
//			try {
//				fw = new FileWriter(file.getAbsoluteFile());
//				BufferedWriter bw = new BufferedWriter(fw);
//				bw.write(string);
//				bw.close();
//			} catch (IOException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//			
//		
// 
//			System.out.println("Done");
			
			
	      
	      utilityCalculator.setWeights(curPosition);
	    
	  //   System.out.println("Particle "+particleID+" still running");
	     
	     int performance = evaluator.evaluateAveragePerformance(movePicker, utilityCalculator);
	    
	  //   System.out.println("Particle "+particleID+" stop running");
	      
	      if(performance>particleBestFitness)
	      {
	    	  particleBestFitness=performance;
	    	  particleBestPosition=ArrayHandler.makeCopy(particleBestPosition);
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
		
    	// TODO Auto-generated method stub
    	 String string="";
    	 string+="Particle "+ particleID+" : "+ "Personal Best: "+ particleBestFitness+"\n";
    	 
    	 return string;
    }

}
