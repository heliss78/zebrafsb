package simModel;

import simulationModelling.AOSimulationModel;
import java.util.*;

import simulationModelling.Behaviour;
import simulationModelling.SequelActivity;

//
// The Simulation model Class
public class FSB extends AOSimulationModel {
	public int id;
	double pcntError;
	double dataEntryAvgTime;
	double districtProcessingAvgTime;
	/*-------------Entity Data Structures-------------------*/

	/* Group and Queue Entities */
	protected ArrayList<LoanApplication> qDataEntryWaiting = new ArrayList<LoanApplication>();

	@SuppressWarnings("unchecked")
	protected ArrayList<LoanApplication>[] qApprovalSet = (ArrayList<LoanApplication>[]) new ArrayList[6];
	// qApprovalSet = new ArrayList<LoanApplication>[6];

	protected RVPs rvp; // Reference to rvp object - object created in
						// constructor
	UDPs udp = new UDPs(this);

	// Output object
	protected Output output = new Output(this);

	// Output values - define the public methods that return values
	// required for experimentation.
	// GAComment: the following are parmeters, please move above, before Entity
	// Data Structures


	// GAComment: please move above to the Entity Data Structures section.
	public Officers[] rgOfficers = new Officers[6];
	HQDataClerks rgHQDataClerks = new HQDataClerks();

	public double getAvgApprovalTurnAroundTime(int origin) {
		return output.getAvgApprovalTurnAroundTime(origin);
	}

	public double getAvgTotalTurnAroundTime() {
		return output.getAvgTotalTurnAroundTime();
	}

	public void clearAvgApprovalTurnAroundTime(int origin) {
		output.clearAvgApprovalTurnAroundTime(origin);
	}

	public void clearAvgTotalTurnAroundTime() {
		output.clearAvgTotalTurnAroundTime();
	}

	// Constructor
	public FSB(double t0time, double tftime, int nDataClerks, int[] nOfficersDistricts, double pcntError,
			double dataEntryAvgTime, double districtProcessingAvgTime,
			/* define other args, */ Seeds sd) {

		// Initialise parameters here

		for (int i = 0; i < 6; i++) {
			qApprovalSet[i] = new ArrayList<LoanApplication>();

			// ArrayList<LoanApplication> qApprovalWaiting = new
			// ArrayList<LoanApplication>();
			// qApprovalSet.add(qApprovalWaiting);

			// qApprovalSet[i] = qApprovalWaiting[i];
		}

		// GAComment - some of this initialisation can be moved to the
		// Initailize action (e.g. update of the numOfficers attributes.
		rgHQDataClerks.numClerks = nDataClerks;
		this.pcntError = pcntError;
		this.dataEntryAvgTime = dataEntryAvgTime;

		this.districtProcessingAvgTime = districtProcessingAvgTime;

		for (int i = 0; i < 6; i++) {
			rgOfficers[i] = new Officers();
			rgOfficers[i].numOfficers = nOfficersDistricts[i];
			//System.out.println("NUMBER OF OFFICERSat DISTRICT :  " + i + " is " + nOfficersDistricts[i]);

		}

		// Create RVP object with given seed
		rvp = new RVPs(this, sd);
		initAOSimulModel(t0time);

	

		// Initialise the simulation model
		// initAOSimulModel(t0time,tftime);

		// Schedule the first arrivals and employee scheduling
		Initialise init = new Initialise(this);
		scheduleAction(init); // Should always be first one scheduled.
		AppArrival aarr = new AppArrival(this);
		scheduleAction(aarr);

		// Schedule other scheduled actions and acitvities here
	}

	/************ Implementation of Data Modules ***********/
	/*
	 * Testing preconditions
	 */
	protected void testPreconditions(Behaviour behObj) {
		reschedule(behObj);

		// Check preconditions of Conditional Activities
		while (scanPreconditions() == true)
			;

		// Check preconditions of Interruptions in Extended Activities
	}

	private boolean scanPreconditions() {
		boolean statusChanged = false;

		if (DataEntry.precondition(this) == true) {
			DataEntry act = new DataEntry(this);
		
	

			act.startingEvent();
			scheduleActivity(act);
			statusChanged = true;
		}
		if (LoanApproval.precondition(this) == true) {
			LoanApproval act = new LoanApproval(this);
			act.startingEvent();
			scheduleActivity(act);
			statusChanged = true;
		}
		return (statusChanged);
	}

	public void eventOccured() {
		// this.showSBL();
		System.out.println("\nCurrent time " + this.getClock());

		for (LoanApplication a : this.qDataEntryWaiting) {
			System.out.println("Loan in queue for data entry: " + a.id);
		}
		for (LoanApplication a : rgHQDataClerks.group) {
			System.out.println("Loan in data entry: " + a.id);
		}
		for (int i = 0; i < 6; i++) {
			for (LoanApplication a : qApprovalSet[i]) {
				System.out.println("Loan in queue for approval in district " + a.uOrig + ": " + a.id);
			}

			for (LoanApplication a : rgOfficers[i].group) {
				System.out.println("Loan being approved in district " + a.uOrig + ": " + a.id);
			}
		}

	}

	// Standard Procedure to start Sequel Activities with no parameters
	protected void spStart(SequelActivity seqAct) {
		seqAct.startingEvent();
		scheduleActivity(seqAct);
	}

}
