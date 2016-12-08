// File: Experiment.java
// Description:

import simModel.*;
import cern.jet.random.engine.*;

// Main Method: Experiments
// 
class Experiment
{
   public static void main(String[] args)
   {
    
       int numClerks = 8;
       int[] numOfficers = {4,2,6,3,2,2};
       double pcntError = 0.1;
       
       
       double startTime=0.0, endTime=6000.0;
       RandomSeedGenerator rsg = new RandomSeedGenerator();
       Seeds sds = new Seeds(rsg);
       // Simulation object

       // Lets get a set of uncorrelated seeds
       
       //for(int i=0 ; i<NUMRUNS ; i++) sds[i] = new Seeds(rsg);
       
       // Loop for NUMRUN simulation runs for each case
       // Case 1
       System.out.println(" Case 1");
       double turnaroundTime = 41;
       double districtTurnAround[] = new double[6];
       while (turnaroundTime > 28){
    	   
    		   
    		   FSB mname = new FSB(startTime,endTime,numClerks,numOfficers,pcntError,6,20,sds);
    		   mname.setTimef(endTime);
    		  
    		   mname.runSimulation();
    		 
    		   
    		   turnaroundTime = mname.getAvgTotalTurnAroundTime();
    		  
    		   System.out.println("REAL TURN AROUND TIME = " + turnaroundTime);
    		   for (int i = 0; i < 6; i++){
    			   districtTurnAround[i] = mname.getAvgApprovalTurnAroundTime(i);
    			   if (districtTurnAround[i] > 28)
    				   numOfficers[i]++;
    			   System.out.println("number of officers at district: " +i+" = "+ mname.rgOfficers[i].numOfficers + 
    					   "after officers time : = " + districtTurnAround[i]); 
    		   }
       }
          // See examples for hints on collecting output
          // and developping code for analysis
          //clerks = 8
          
 
   
}}
