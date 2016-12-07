package simModel;

import cern.jet.random.EmpiricalWalker;


class UDPs 
{
	FSB model;  // for accessing the clock
	
	// Constructor
	protected UDPs(FSB model) { this.model = model; }
	
	
		
		
	
	
	

    // GACOmment: clearly a bad translation from the CM.  Name is not correct.  The contants should be defined in the Constants class.
	//            And there is no searching of the RG.Officers/Q.ApprovalWiating entities for determining the id of the
	//            RG.Officers that can start processing a loan application.
	public LoanApplication.uOrigin getID(FSB model) {
		
		LoanApplication.uOrigin uOrig;
		uOrig = LoanApplication.uOrigin.BOISE;
		
		
		
		
		return uOrig;
		
		
		
	}
	
	public int LoanApplicationAvailable() {
		int districtId =-1;
	
		for(int i =0;i<6;i++){
			if(model.rgOfficers[i].n < model.rgOfficers[i].numOfficers && model.qApprovalSet[i].size() >0){
				districtId = i;
				return districtId;
			} 
			
	}
		return districtId;
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

