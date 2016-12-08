package simModel;

import simulationModelling.AOSimulationModel;
import java.util.*;

import simulationModelling.Behaviour;
import simulationModelling.SequelActivity;

//
// The Simulation model Class
public class FSB extends AOSimulationModel
{  
	public int id;
    // GAComment: Parameters should be declared here, in partcilar the pcntError, DataEntryAvgTime, districtProcessingAvgTime.
	/*-------------Entity Data Structures-------------------*/
	
	/* Group and Queue Entities */
	protected ArrayList<LoanApplication> qDataEntryWaiting = new ArrayList<LoanApplication>();
	// GAComment: This should be ArrayList<LoanAppication> since it is a queue of loan applications.  here you have declared a lists of lists.
	//  Actually what you want is an array of ArrayList objects, that is ArrayList<LoanApplications> [] qApprovalWaiting = new ArrayList[6]
	//  This will make the SM consistent with the CM (i.e. its concept of Set).
	//protected ArrayList<ArrayList<LoanApplication>> qApprovalSet = new ArrayList<ArrayList<LoanApplication>>();
	
	protected ArrayList<LoanApplication>[] qApprovalSet = (ArrayList<LoanApplication>[])new ArrayList[6];
	//qApprovalSet = new ArrayList<LoanApplication>[6];

	protected RVPs rvp;  // Reference to rvp object - object created in constructor
	UDPs udp = new UDPs(this);
	
	// Output object
	protected Output output = new Output(this);
	
	// Output values - define the public methods that return values
	// required for experimentation.
	// GAComment: the following are parmeters, please move above, before Entity Data Structures
	double pcntError;
	double dataEntryAvgTime;
	double districtProcessingAvgTime;

	// GAComment: please move above to the Entity Data Structures section.
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
		// GAComment - careful about indentation
	// GAComment - no clear what is happeing here. There there is one array list added - you need 6
	//             in fact, use an array of ArrayLists to implement qApprovalWaiting (see above comments)
		for (int i =0;i<6;i++){
			qApprovalSet[i] = new ArrayList<LoanApplication>();
			
		
	 //ArrayList<LoanApplication> qApprovalWaiting = new ArrayList<LoanApplication>();
	 //qApprovalSet.add(qApprovalWaiting);
			
	// qApprovalSet[i] = qApprovalWaiting[i];
		}
		
	// GAComment - some of this initialisation can be moved to the Initailize action (e.g. update of the numOfficers attributes.	
		rgHQDataClerks.numClerks = nDataClerks;
		this.pcntError = pcntError;
		this.dataEntryAvgTime = dataEntryAvgTime;
		
		this.districtProcessingAvgTime = districtProcessingAvgTime;
		
		for(int i =0;i<6;i++){
			rgOfficers[i] = new Officers();
			rgOfficers[i].numOfficers = nOfficersDistricts[i];
			//System.out.println("NUMBER OF OFFICERS " +nOfficersDistricts[i]);
			
		}
		
		
		
		// Create RVP object with given seed
		rvp = new RVPs(this,sd);
		initAOSimulModel(t0time);
		
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
			rgHQDataClerks.print();
			System.out.println(" SIZE OF DATA ENTRY QUEUE -> " +this.qDataEntryWaiting.size() +" with group variable -> " + rgHQDataClerks.getN());
			System.out.println(" Location of loan app  -> " +this.qDataEntryWaiting.get(0).uOrig );
			
			act.startingEvent();
			scheduleActivity(act);
			statusChanged = true;
		}
		if(LoanApproval.precondition(this) == true)
		{
			LoanApproval act = new LoanApproval(this);
			act.startingEvent();
			//rgOfficers[0].print();
			
			System.out.println(" SIZE OF DATA ENTRY QUEUE IN LOAN APPROVAL -> " +this.qDataEntryWaiting.size() +" with group vaiable of loan officers at boise -> " +  rgOfficers[0].getN() );
		//	System.out.println(" Location of loan app IN LOAN APPROVAL -> " +this.qDataEntryWaiting.get(0).uOrig);
			scheduleActivity(act);
			System.out.println(" Dist1 -> has this many loans  " +  rgOfficers[1].getN()  );
			System.out.println("the loan app ids at this district is " );
			rgOfficers[1].print();
			
			System.out.println(" Dist2 -> has this many loans " +  rgOfficers[2].getN() );
			System.out.println("the loan app ids at this district is " );
			rgOfficers[2].print();
			System.out.println(" Dist3 -> has this many loans " +  rgOfficers[3].getN() );
			System.out.println("the loan app ids at this district is " );
			rgOfficers[3].print();
			System.out.println(" Dist4 -> has this many loans" +  rgOfficers[4].getN() );
			System.out.println("the loan app ids at this district is " );
			rgOfficers[4].print();
			System.out.println(" Dist5 ->  has this many loans" +  rgOfficers[5].getN() );
			System.out.println("the loan app ids at this district is " );
			rgOfficers[5].print();
			
			statusChanged = true;
		}
		return (statusChanged);
	}
	
	public void eventOccured()
	{
		// GAComment: missing log section
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


