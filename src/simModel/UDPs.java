package simModel;

import cern.jet.random.EmpiricalWalker;

class UDPs {
	FSB model; // for accessing the clock

	// Constructor
	protected UDPs(FSB model) {
		this.model = model;
	}

		// check to see if there is available officer and if there is an application in the queue and provide the id
	public int LoanApplicationAvailable() {
		int districtId = -1;

		for (int i = 0; i < 6; i++) {
			if (model.rgOfficers[i].getN() < model.rgOfficers[i].numOfficers && model.qApprovalSet[i].size() > 0) {
				districtId = i;
				return districtId;
			}

		}
		return districtId;
	}

}
