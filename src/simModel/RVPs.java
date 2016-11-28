package simModel;

import cern.jet.random.Empirical;
import cern.jet.random.EmpiricalWalker;
import cern.jet.random.Exponential;
import cern.jet.random.Uniform;
import cern.jet.random.engine.MersenneTwister;
//import portModelV1.Tanker;

class RVPs 
{
	FSB model; // for accessing the clock
    // Data Models - i.e. random veriate generators for distributions
	// are created using Colt classes, define 
	// reference variables here and create the objects in the
	// constructor with seeds


	// Constructor
	protected RVPs(FSB model, Seeds sd) 
	{ 
		this.model = model; 
		DETMIN = (this.model.dataEntryAvgTime * 0.75);
		DETMAX = (model.dataEntryAvgTime * 1.25);
		
		DPTMIN = (model.districtProcessingAvgTime * 0.75);
		DPTMAX = (model.districtProcessingAvgTime * 1.25);
		
		DPTWEMIN = ((model.districtProcessingAvgTime * 0.75) + 6);
		DPTWEMAX = ((model.districtProcessingAvgTime * 1.25) + 6);
		
		// Set up distribution functions
		dataEntryTimeDist = new Uniform(DETMIN,DETMAX,sd.seed1);
		districtProcessingTimeDist = new Uniform(DPTMIN,DPTMAX,sd.seed1);
		districtProcessingTimeWEDist =  new Uniform(DPTWEMIN,DPTWEMAX,sd.seed1);
		interArrDist = new Exponential(1.0/WMEAN1,  
				                       new MersenneTwister(sd.seed1));
		uOriginDM = new EmpiricalWalker(appLocationPDF,
      		   Empirical.NO_INTERPOLATION,
      		   new MersenneTwister(sd.seed1));
		
	}
	
	
	/* Random Variate Procedure for Arrivals */
	private Exponential interArrDist;  // Exponential distribution for interarrival times
	private final double WMEAN1=3.8;
	private boolean firstApplication = true;
	
	
	//////Random Variate Procedure uDataEntryTime
	private Uniform dataEntryTimeDist;
	final double DETMIN; 
	final double DETMAX; 
	
	protected double uDataEntryTime(){
		
		
		double dataEntryTime = dataEntryTimeDist.nextDouble();
	
		return dataEntryTime;
		
	}
	
	//////// RVP uLoanAppPorigin
	
	final static double DIST1 = 0.177;
	final static double DIST2 = 0.096;
	final static double DIST3 = 0.41;
	final static double DIST4 = 0.106;
	final static double DIST5 = 0.099;
	final static double DIST6 = 0.112;
	
	private final double [] appLocationPDF = { DIST1, DIST2, DIST3,DIST4,DIST5,DIST6 }; // for creating discrete PDF
	private EmpiricalWalker uOriginDM; 
	LoanApplication.uOrigin uLoanAppOrigin()
	{
		LoanApplication.uOrigin loc;		
		switch(uOriginDM.nextInt())
		{
		
		   case 0: loc = LoanApplication.uOrigin.COUER_ALENE; break;
		   case 1: loc = LoanApplication.uOrigin.LEWISTON; break;
		   case 2: loc = LoanApplication.uOrigin.BOISE; break;
		   case 3: loc = LoanApplication.uOrigin.SHOSONE; break;
		   case 4: loc = LoanApplication.uOrigin.POCATELLO; break;
		   case 5: loc = LoanApplication.uOrigin.RIGBY; break;
		   
		   
		   default: 
			   System.out.println("LoanApplication Origin returned invalid value");
		   	   loc = LoanApplication.uOrigin.BOISE;		// back to headquareters   	
		}
		return(loc);
	}
	
	
	
	/////// Random Variate Procedure uDistrictProcessingTime
	
	private Uniform districtProcessingTimeDist;
	private Uniform districtProcessingTimeWEDist;
	
	final double DPTMIN;
	final double DPTMAX;
	
	final double DPTWEMIN;
	final double DPTWEMAX;
	
	protected double uDistrictPorcessingTime() {
		//boolean testEr = true;
		double districtProcessingTime;
		double rnum = Math.random();
		if(rnum<model.pcntError)
			districtProcessingTime = districtProcessingTimeWEDist.nextDouble();
		else
			districtProcessingTime = districtProcessingTimeDist.nextDouble();
		
		return districtProcessingTime;
	}
	
	protected double duLoanAppArrival()  // for getting next value of duInput
	{
	    double nextTime;
	    if(firstApplication) {
	    	nextTime = 0;
	    	firstApplication = false;
	    }
	    else {
	    	nextTime = model.getClock() + interArrDist.nextDouble();
	    	
	    }
	    

       // nxtInterArr = interArrDist.nextDouble();
	    // Note that interarrival time is added to current
	    // clock value to get the next arrival time.
	    return(nextTime);
	}

}
