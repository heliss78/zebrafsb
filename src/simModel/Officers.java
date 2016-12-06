package simModel;

import java.util.HashSet;

public class Officers {
	
	int n;  // GAComment: not required - maintained by the HashSet objects.

	public int numOfficers; // number of loan officers at each district
	
	
	HashSet<LoanApplication> group = new HashSet<LoanApplication>();//maintains group of LoanApplications being serviced by loan officers
	
	void insert(LoanApplication icLoanApplication) {
		
		group.add(icLoanApplication);
	}
	
	boolean remove(LoanApplication icLoanApplication){
		return group.remove(icLoanApplication);
		
	}
	int getN(){
		return group.size();//ureturns number of LoanApplications being processed
		
	}
	
	public void print(){
		for(LoanApplication a: group){
			System.out.println("LOAN APPLICATION IN OFFICERs : " + a.id);
		}
	}
	
}
