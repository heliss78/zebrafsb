package simModel;

import simulationModelling.OutputSequence;

class Output {
	FSB model;
	String name = "ApprovalTurnAround";

	protected OutputSequence phiICDistrictTurnAround[] = new OutputSequence[6];

	protected OutputSequence phiICtotalTurnAround;

	protected Output(FSB md) {
		this.model = md;

		for (int i = 0; i < 6; i++) {
			name = name + i;
			phiICDistrictTurnAround[i] = new OutputSequence(name);
		}
		phiICtotalTurnAround = new OutputSequence("TotalTurnAround");

	}

	protected double getAvgApprovalTurnAroundTime(int origin) {

		phiICDistrictTurnAround[origin].computePhiDSOVs();
		return (phiICDistrictTurnAround[origin].getMean());

	}

	protected double getAvgTotalTurnAroundTime() {
		phiICtotalTurnAround.computePhiDSOVs();

		return (phiICtotalTurnAround.getMean());

	}

	protected void clearAvgApprovalTurnAroundTime(int origin) {
		phiICDistrictTurnAround[origin].clearSet();
	}

	protected void clearAvgTotalTurnAroundTime() {
		phiICtotalTurnAround.clearSet();
	}

}
