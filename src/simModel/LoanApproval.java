package simModel;
import simulationModelling.ConditionalActivity;
public class LoanApproval extends ConditionalActivity {

	FSB model;
	LoanApplication icLoanApplication;
	
	public LoanApproval(FSB model) {this.model = model;
	
	
	}
	
public static boolean precondition(FSB model) {
		//icLoanApplication = model.qDataEntryWaiting.remove(0);
		//icLoanApplication.
		
		
	return(model.qApprovalSet.get(0).size() !=0 && model.rgOfficers[0].n < model.rgOfficers[0].numOfficers);
		//return(model.qApprovalSet.get(model.rvp.uLoanAppOrigin()).size() !=0 && model.rgOfficers[0].n < model.rgOfficers[0].numOfficers);
		//return (model.qDataEntryWaiting.size() !=0 && model.rgHQDataClerks.n < model.rgHQDataClerks.numClerks);
	}
	@Override
	protected double duration() {
		
		
		// TODO Auto-generated method stub
		return model.rvp.uDistrictPorcessingTime();
	}

	@Override
	public void startingEvent() {
		
		
		icLoanApplication = model.qApprovalSet.get(0).remove(0);
		
		model.rgOfficers[0].insert(icLoanApplication);
		model.rgOfficers[0].n++;
		
		
		
		// TODO Auto-generated method stub
	
		
		
	}// model.output.phiICTankerTotalWait.put( model.getClock(), icTanker.totalWait);

	@Override
	protected void terminatingEvent() {
		
		
		model.output.phiICapprovalTurnAround.put(model.getClock(), model.getClock()-icLoanApplication.endDataEntryTime);
		model.output.phiICtotalTurnAround.put(model.getClock(), icLoanApplication.startWait);
		
		model.rgOfficers[0].remove(icLoanApplication);
		
		
		// TODO Auto-generated method stub
		
	}
	
	

}
