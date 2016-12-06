package simModel;

import simulationModelling.OutputSequence;

class Output 
{
	FSB model;
	// GAComment: in the CM 6 sequences are defined, should phiICapprovalTurnAround be an array of OutputSequence objects
	protected OutputSequence phiICapprovalTurnAround;
	protected OutputSequence phiICtotalTurnAround;
	
	
	protected Output(FSB md) { this.model = md; 
	// GAComment: careful with indentation.
	phiICapprovalTurnAround = new OutputSequence("ApprovalTurnAround");
	phiICtotalTurnAround = new OutputSequence("TotalTurnAround");
	
	
	
	}
	
	protected double getAvgApprovalTurnAroundTime(){
		// GAComment: need to build an array of 5 double values to provide the 6 output values, one from each district
		phiICapprovalTurnAround.computePhiDSOVs();
		return(phiICapprovalTurnAround.getMean());
		
	}
	
	protected double getAvgTotalTurnAroundTime(){
		phiICtotalTurnAround.computePhiDSOVs();
		//GAComment - what is the followint output about?
		System.out.println("PHI AROUND TIME  ______" + phiICtotalTurnAround.getMean());
		System.out.println("PHI AROUND TIME  ______" + phiICtotalTurnAround.getMean());
		System.out.println("PHI AROUND TIME  ______" + phiICtotalTurnAround.getMean());
		System.out.println("PHI AROUND TIME  ______" + phiICtotalTurnAround.getMean());
		System.out.println("PHI AROUND TIME  ______" + phiICtotalTurnAround.getMean());
		System.out.println("PHI AROUND TIME  ______" + phiICtotalTurnAround.getMean());

		return(phiICtotalTurnAround.getMean());
		
	}
	
	protected void clearAvgApprovalTurnAroundTime() { phiICapprovalTurnAround.clearSet();}
	protected void clearAvgTotalTurnAroundTime() { phiICtotalTurnAround.clearSet();}
	
    // Use OutputSequence class to define Trajectory and Sample Sequences
    // Trajectory Sequences

    // Sample Sequences

    // DSOVs available in the OutputSequence objects
    // If seperate methods required to process Trajectory or Sample
    // Sequences - add them here

    // SSOVs
}
