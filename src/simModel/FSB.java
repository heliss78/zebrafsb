package simModel;

import simulationModelling.AOSimulationModel;
import java.util.*;

import simulationModelling.Behaviour;
import simulationModelling.SequelActivity;

//
// The Simulation model Class
public class FSB extends AOSimulationModel
{
	// Constants available from Constants class
	/* Parameter */
        // Define the parameters
	
	

	/*-------------Entity Data Structures-------------------*/
	


	
	
	
	/* Group and Queue Entities */
	protected ArrayList<LoanApplication> qDataEntryWaiting = new ArrayList<LoanApplication>();
	
	//protected ArrayList<LoanApplication> qApprovalWaiting = new ArrayList<LoanApplication>();
	protected ArrayList<ArrayList<LoanApplication>> qApprovalSet = new ArrayList<ArrayList<LoanApplication>>();
	//protected ArrayList<LoanApplication>[] aa = new ArrayList<LoanApplication>[6];
	
	
	//LoanApplication[] l;
	// Define the reference variables to the various 
	// entities with scope Set and Unary
	
	
	// Objects can be created here or in the Initialise Action

	/* Input Variables */
	// Define any Independent Input Varaibles here
	
	// References to RVP and DVP objects
	protected RVPs rvp;  // Reference to rvp object - object created in constructor
	
	
	// Output object
	protected Output output = new Output(this);
	
	// Output values - define the public methods that return values
	// required for experimentation.
	double pcntError;
	double dataEntryAvgTime;
	double districtProcessingAvgTime;

	public Officers[] rgOfficers = new Officers[6];
	HQDataClerks rgHQDataClerks = new HQDataClerks();
	
	
	public double getAvgApprovalTurnAroundTime(){ return output.getAvgApprovalTurnAroundTime();}
	public double getAvgTotalTurnAroundTime(){ return output.getAvgTotalTurnAroundTime();}
	
	public void clearAvgApprovalTurnAroundTime() { output.clearAvgApprovalTurnAroundTime();}
	public void clearAvgTotalTurnAroundTime() { output.clearAvgTotalTurnAroundTime();}
	
	

	// Constructor
	public FSB(double t0time, double tftime,int nDataClerks,
			int[] nOfficersDistricts,double pcntError,double dataEntryAvgTime,
			double districtProcessingAvgTime,/*define other args,*/ Seeds sd)
	{
		
		// Initialise parameters here
	 ArrayList<LoanApplication> qApprovalWaiting = new ArrayList<LoanApplication>();
	 qApprovalSet.add(qApprovalWaiting);
		
		
		rgHQDataClerks.numClerks = nDataClerks;
		this.pcntError = pcntError;
		this.dataEntryAvgTime = dataEntryAvgTime;
		this.districtProcessingAvgTime = districtProcessingAvgTime;
		
		for(int i =0;i<6;i++){
			rgOfficers[i] = new Officers();
			rgOfficers[i].numOfficers = nOfficersDistricts[i];
			
		}
		
		
		
		// Create RVP object with given seed
		rvp = new RVPs(this,sd);
		initAOSimulModel(t0time,tftime+60);
		
		// rgCounter and qCustLine objects created in Initalise Action
		
		// Initialise the simulation model
		//initAOSimulModel(t0time,tftime);   

		     // Schedule the first arrivals and employee scheduling
		Initialise init = new Initialise(this);
		scheduleAction(init);  // Should always be first one scheduled.
		AppArrival aarr = new AppArrival(this);
		scheduleAction(aarr);
		
		// Schedule other scheduled actions and acitvities here
	}

	/************  Implementation of Data Modules***********/	
	/*
	 * Testing preconditions
	 */
	protected void testPreconditions(Behaviour behObj)
	{
		reschedule (behObj);
		
		// Check preconditions of Conditional Activities
		while (scanPreconditions() == true);
			
		// Check preconditions of Interruptions in Extended Activities
	}
	
	private boolean scanPreconditions(){
		boolean statusChanged = false;
		
		if(DataEntry.precondition(this) == true)
		{
			DataEntry act = new DataEntry(this);
			act.startingEvent();
			scheduleActivity(act);
			statusChanged = true;
		}
		if(LoanApproval.precondition(this) == true)
		{
			LoanApproval act = new LoanApproval(this);
			act.startingEvent();
			scheduleActivity(act);
			statusChanged = true;
		}
		return (statusChanged);
	}
	
	public void eventOccured()
	{
		this.showSBL();
		// Can add other debug code to monitor the status of the system
		// See examples for suggestions on setup logging

		// Setup an updateTrjSequences() method in the Output class
		// and call here if you have Trajectory Sets
		// updateTrjSequences() 
	}

	// Standard Procedure to start Sequel Activities with no parameters
	protected void spStart(SequelActivity seqAct)
	{
		seqAct.startingEvent();
		scheduleActivity(seqAct);
	}	

}


