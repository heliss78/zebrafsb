package simModel;

import simulationModelling.ConditionalActivity;

public class DataEntry extends ConditionalActivity {

	FSB model;
	LoanApplication icLoanApplication;

	public DataEntry(FSB model) {
		this.model = model;

	}

	public static boolean precondition(FSB model) {

		return (model.rgHQDataClerks.getN() < model.rgHQDataClerks.numClerks && model.qDataEntryWaiting.size() != 0);
	}

	@Override
	protected double duration() {

		return model.rvp.uDataEntryTime();
	}

	@Override
	public void startingEvent() {

		icLoanApplication = model.qDataEntryWaiting.remove(0);
		model.rgHQDataClerks.insert(icLoanApplication);

	}

	@Override
	protected void terminatingEvent() {
		icLoanApplication.endDataEntryTime = model.getClock();
		model.rgHQDataClerks.remove(icLoanApplication);
		model.qApprovalSet[icLoanApplication.uOrig].add(icLoanApplication);

	}

}
