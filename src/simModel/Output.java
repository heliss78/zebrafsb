package simModel;

import simulationModelling.OutputSequence;

class Output {
	FSB model;
	String name = "ApprovalTurnAround";
	// GAComment: in the CM 6 sequences are defined, should
	// phiICapprovalTurnAround be an array of OutputSequence objects
	protected OutputSequence phiICapprovalTurnAround[] = new OutputSequence[6];

	protected OutputSequence phiICtotalTurnAround;

	protected Output(FSB md) {
		this.model = md;
		
		// GAComment: careful with indentation.
		for(int i =0;i<6;i++){
			name = name + i;
		phiICapprovalTurnAround[i] = new OutputSequence(name);
		}
		phiICtotalTurnAround = new OutputSequence("TotalTurnAround");

	}

	protected double getAvgApprovalTurnAroundTime(int origin) {
		
		phiICapprovalTurnAround[origin].computePhiDSOVs();
		//System.out.println("TOTAL TURN AROUND TIME AT LOCATION:  " +origin + " is " + +phiICapprovalTurnAround[origin].getMean());
		return (phiICapprovalTurnAround[origin].getMean());

	}

	protected double getAvgTotalTurnAroundTime() {
		phiICtotalTurnAround.computePhiDSOVs();
		
		//System.out.println("TOTAL TURN AROUND TIME  ______" + phiICtotalTurnAround.getMean());

		return (phiICtotalTurnAround.getMean());

	}

	protected void clearAvgApprovalTurnAroundTime(int origin) {
		phiICapprovalTurnAround[origin].clearSet();
	}

	protected void clearAvgTotalTurnAroundTime() {
		phiICtotalTurnAround.clearSet();
	}

}
