package simModel;

import simulationModelling.ConditionalActivity;

public class LoanApproval extends ConditionalActivity {

	FSB model;
	LoanApplication icLoanApplication;
	// GAComment: the district id should be a class variable so it can be seen
	// by all methods

	public LoanApproval(FSB model) {
		this.model = model;

	}

	public static boolean precondition(FSB model) {
		// icLoanApplication = model.qDataEntryWaiting.remove(0);
		// icLoanApplication.

		// GAComment: should be model.udp.LoanApplicationAvailable() != NONE
		return (model.udp.LoanApplicationAvailable() > -1);
		// return(model.qApprovalSet.get(model.rvp.uLoanAppOrigin()).size() !=0
		// && model.rgOfficers[0].n < model.rgOfficers[0].numOfficers);
		// return (model.qDataEntryWaiting.size() !=0 && model.rgHQDataClerks.n
		// < model.rgHQDataClerks.numClerks);
	}

	@Override
	protected double duration() {

		// TODO Auto-generated method stub
		// GAComent - check the name Porcessing, not Processing
		return model.rvp.uDistrictPorcessingTime();

	}

	@Override
	public void startingEvent() {

		// GAComment: again qApprovalSet not consistent with CM.
		// Need to use UDP to get id of entity Q.ApprovalWaiting
		// icLoanApplication =
		// model.qApprovalSet.get(icLoanApplication.uOrig).remove(0);
		icLoanApplication = model.qApprovalSet[model.udp.LoanApplicationAvailable()].remove(0);
		// GAComment: note the 0
		model.rgOfficers[icLoanApplication.uOrig].insert(icLoanApplication);
		// model.rgOfficers[icLoanApplication.uOrig].n++;

		// TODO Auto-generated method stub

	}// model.output.phiICTankerTotalWait.put( model.getClock(),
		// icTanker.totalWait);

	@Override
	protected void terminatingEvent() {

		// GAComment: should be updating one of 6 sample sequences (for the
		// specific district.
		model.output.phiICapprovalTurnAround[icLoanApplication.uOrig].put(model.getClock(),
				model.getClock() - icLoanApplication.startTime);
		// GAComment: Not consistent with CM - note how the turnaround time is
		// calculated
		model.output.phiICtotalTurnAround.put(model.getClock(), model.getClock() - icLoanApplication.startTime);

		model.rgOfficers[icLoanApplication.uOrig].remove(icLoanApplication);
		// System.out.println("LOAN APPROVAL TURN AROUND TIME: "
		// +(model.getClock()-icLoanApplication.startTime ));

		// TODO Auto-generated method stub

	}

}
