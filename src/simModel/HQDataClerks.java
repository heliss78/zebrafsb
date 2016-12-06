package simModel;

import java.util.HashSet;

public class HQDataClerks {
	

	int numClerks; // number of clerks at each district
	// GAComment: this attribute is not required, since its value is maintained by the HashSet class.  You have getN that provides the value of n.  Please remove.
	int n;//number of applications being processed in list which also gives the number of occupied data clerks
	
	
	HashSet<LoanApplication> group = new HashSet<LoanApplication>();//maintains group of LoanApplications being serviced by loan officers
	
	void insert(LoanApplication icLoanApplication) {
		group.add(icLoanApplication);
		
	}
	
	boolean remove(LoanApplication icLoanApplication){
		return group.remove(icLoanApplication);
		
	}
	int getN(){
		return group.size();// ureturns number of LoanApplications being processed, i.e. the value of n
		
	}
	
	public void print(){
		for(LoanApplication a: group){
			System.out.println("LOAN APPLICATION IN OFFICERs XYZXYZ : " + a.id);
		}
	}

}
