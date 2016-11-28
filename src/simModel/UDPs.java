package simModel;

import cern.jet.random.EmpiricalWalker;


class UDPs 
{
	FSB model;  // for accessing the clock
	
	// Constructor
	protected UDPs(FSB model) { this.model = model; }

	
	public LoanApplication.uOrigin getID(FSB model) {
		
		LoanApplication.uOrigin uOrig;
		uOrig = LoanApplication.uOrigin.BOISE;
		
		
		
		
		return uOrig;
		
		
		
	}
	// Translate User Defined Procedures into methods
    /*-------------------------------------------------
	                       Example
	    protected int ClerkReadyToCheckOut()
        {
        	int num = 0;
        	Clerk checker;
        	while(num < model.NumClerks)
        	{
        		checker = model.Clerks[num];
        		if((checker.currentstatus == Clerk.status.READYCHECKOUT)  && checker.list.size() != 0)
        		{return num;}
        		num +=1;
        	}
        	return -1;
        }
	------------------------------------------------------------*/
	
	
}
