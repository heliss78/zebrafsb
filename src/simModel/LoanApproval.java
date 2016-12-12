package simModel;

import simulationModelling.ConditionalActivity;

public class LoanApproval extends ConditionalActivity {

	FSB model;
	LoanApplication icLoanApplication;

	public LoanApproval(FSB model) {
		this.model = model;

	}

	public static boolean precondition(FSB model) {

		return (model.udp.LoanApplicationAvailable() > -1);

	}

	@Override
	protected double duration() {

		return model.rvp.uDistrictProcessingTime();

	}

	@Override
	public void startingEvent() {

		icLoanApplication = model.qApprovalSet[model.udp.LoanApplicationAvailable()].remove(0);

		model.rgOfficers[icLoanApplication.uOrig].insert(icLoanApplication);

	}

	@Override
	protected void terminatingEvent() {

		model.output.phiICDistrictTurnAround[icLoanApplication.uOrig].put(model.getClock(),
				model.getClock() - icLoanApplication.startTime);

		model.output.phiICtotalTurnAround.put(model.getClock(), model.getClock() - icLoanApplication.startTime);

		model.rgOfficers[icLoanApplication.uOrig].remove(icLoanApplication);

	}

}
