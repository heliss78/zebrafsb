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
    
       int numClerks = 30;
       int[] numOfficers = {4,2,6,3,2,2};
       double pcntError = 0.1;
       
       
       double startTime=0.0, endTime=600.0;
       RandomSeedGenerator rsg = new RandomSeedGenerator();
       Seeds sds = new Seeds(rsg);
       // Simulation object

       // Lets get a set of uncorrelated seeds
       
       //for(int i=0 ; i<NUMRUNS ; i++) sds[i] = new Seeds(rsg);
       
       // Loop for NUMRUN simulation runs for each case
       // Case 1
       System.out.println(" Case 1");
       double turnaroundTime = 21;
       while (turnaroundTime > 20){
    	   
    		   
    		   FSB mname = new FSB(startTime,endTime,numClerks,numOfficers,pcntError,6,20,sds);
    		   mname.setTimef(endTime);
    		  
    		   mname.runSimulation();
    		 
    		   
    		   turnaroundTime = mname.getAvgTotalTurnAroundTime();
    		   
    		   for (int i = 0; i < 6; i++){
    			   
    			   if (turnaroundTime > 20)
    				   mname.rgOfficers[i].numOfficers++;
    		   }
       }
          // See examples for hints on collecting output
          // and developping code for analysis
          //clerks = 8
          
 
   
}}
