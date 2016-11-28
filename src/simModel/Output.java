package simModel;

import simulationModelling.OutputSequence;

class Output 
{
	FSB model;
	
	protected OutputSequence phiICapprovalTurnAround;
	protected OutputSequence phiICtotalTurnAround;
	
	
	protected Output(FSB md) { this.model = md; 
	
	phiICapprovalTurnAround = model.getClock() - 2;
	phiICtotalTurnAround = new OutputSequence("TotalTurnAround");
	
	
	
	}
	
	protected double getAvgApprovalTurnAroundTime(){
		phiICapprovalTurnAround.computePhiDSOVs();
		return(phiICapprovalTurnAround.getMean());
		
	}
	
	protected double getAvgTotalTurnAroundTime(){
		phiICtotalTurnAround.computePhiDSOVs();
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
