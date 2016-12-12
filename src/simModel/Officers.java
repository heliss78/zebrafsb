package simModel;

import java.util.HashSet;

public class Officers {

	public int numOfficers; // number of loan officers at each district

	// maintains group of LoanApplications being serviced by loan officers

	HashSet<LoanApplication> group = new HashSet<LoanApplication>();
	// add a loan application to the queue
	void insert(LoanApplication icLoanApplication) {

		group.add(icLoanApplication);
	}
		// remove a loan application from queue
	boolean remove(LoanApplication icLoanApplication) {
		return group.remove(icLoanApplication);

	}

	int getN() {
		return group.size();// returns number of LoanApplications being
							// processed

	}

}
