package simModel;
import simulationModelling.ConditionalActivity;
public class DataEntry extends ConditionalActivity {
	
	FSB model;
	LoanApplication icLoanApplication;
	
	public DataEntry(FSB model) {this.model = model;
		
		
	}
	
	public static boolean precondition(FSB model) {
		
		
		
		
		return (model.qDataEntryWaiting.size() !=0 && model.rgHQDataClerks.n < model.rgHQDataClerks.numClerks);
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
		model.rgHQDataClerks.n++;
		
	}

	@Override
	protected void terminatingEvent() {
		// TODO Auto-generated method stub
		icLoanApplication.endDataEntryTime = model.getClock();
		model.output.phiICtotalTurnAround.put(model.getClock(), icLoanApplication.endDataEntryTime);
		model.rgHQDataClerks.remove(icLoanApplication);
		model.qApprovalSet.get(0).add(icLoanApplication);
		model.rgHQDataClerks.n--;
		// model.output.phiICTankerTotalWait.put( model.getClock(), icTanker.totalWait);
		// Deberthing Activity Terminating Event SCS 		
	    // Update tanker status
	
	}

}
