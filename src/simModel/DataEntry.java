package simModel;
import simulationModelling.ConditionalActivity;
public class DataEntry extends ConditionalActivity {
	
	FSB model;
	LoanApplication icLoanApplication;
	
	public DataEntry(FSB model) {this.model = model;
		
		
	}
	
	public static boolean precondition(FSB model) {
		
		
		
		// GAComment:  small detail but order of logic expression not identical to the one in the CM
		return (model.qDataEntryWaiting.size() !=0 && model.rgHQDataClerks.getN() < model.rgHQDataClerks.numClerks);
	}
	

	@Override
	protected double duration() {
		// TODO Auto-generated method stub
		
		return model.rvp.uDataEntryTime();
	}

	@Override
	public void startingEvent() {
		// TODO Auto-generated method stub
		icLoanApplication = model.qDataEntryWaiting.remove(0);
		
		
		model.rgHQDataClerks.insert(icLoanApplication);
		//model.rgHQDataClerks.n++;  // GACOmment: this is not required, since the HashSet object will maintain this value.  Note there is not CM equivalent of this instruction.
		
	}

	@Override
	protected void terminatingEvent() {
		// TODO Auto-generated method stub
		
		icLoanApplication.endDataEntryTime = model.getClock();
		// GAComment: Why is the sample sequence updated here (and with the time noless).  According to the CM, the sample sequences are updated at the end of the Approval Activity.
		//model.output.phiICtotalTurnAround.put(model.getClock(), icLoanApplication.endDataEntryTime);
		model.rgHQDataClerks.remove(icLoanApplication);
		model.qApprovalSet[icLoanApplication.uOrig].add(icLoanApplication); 
		//System.out.println("DATA ENTRY AVG TIME: " + (icLoanApplication.endDataEntryTime-icLoanApplication.startTime));
		// GAComment: this should be model.qApprovalWaiting[icLoanApplication.uOrigin].icLoanApplication
		                                                   // Inconsistence defnition of the SET of ApprovalWaiting entities.  Name is wrong.
		                                                   // Finally all applications are added to Q.ApprovalWiating[0]
		//model.rgHQDataClerks.n--;
		// GAComment: please remove comments that are not relevant to this project.
		// model.output.phiICTankerTotalWait.put( model.getClock(), icTanker.totalWait);
		// Deberthing Activity Terminating Event SCS 		
	    // Update tanker status
	
	}

}
