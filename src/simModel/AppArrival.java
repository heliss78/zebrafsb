package simModel;

import simulationModelling.ScheduledAction;
public class AppArrival extends ScheduledAction {

	int id = 1;
	
	FSB model;
	AppArrival(FSB model){this.model = model;}
	
	protected double timeSequence() {
		
		return (model.rvp.duLoanAppArrival());
	}

	@Override
	protected void actionEvent() {
		LoanApplication icLoanApplication = new LoanApplication();
		icLoanApplication.id = this.id;
		//System.out.println(" SIZE OF DATA ENTRY QUEUE in app arrivals -> " +model.qDataEntryWaiting.size() );
		//System.out.println("APPLICATION ID IS: " + id);
		id++;
		icLoanApplication.uOrig = model.rvp.uLoanAppOrigin();
		icLoanApplication.startTime = model.getClock();
		icLoanApplication.endDataEntryTime = 0.0;
		model.qDataEntryWaiting.add(icLoanApplication);
		
		
		
	}

}
