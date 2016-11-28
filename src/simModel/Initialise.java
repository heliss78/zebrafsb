package simModel;

import simulationModelling.ScheduledAction;

class Initialise extends ScheduledAction
{
	FSB model;
	
	// Constructor
	protected Initialise(FSB model) { this.model = model; }

	double [] ts = { 0.0, -1.0 }; // -1.0 ends scheduling
	int tsix = 0;  // set index to first entry.
	protected double timeSequence() 
	{
		return ts[tsix++];  // only invoked at t=0
	}

	protected void actionEvent() 
	{
		/*for(int i =0;i<6;i++){
			model.rgOfficers[i].numOfficers = model.rgOfficers[i].numOfficers;
			
		}*/
			
		
		
		
		// System Initialisation
                // Add initilisation instructions 
	}
	

}
