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
       int NUMRUNS = 30; 
       int numClerks = 30;
       int[] numOfficers = {4,2,6,3,2,2};
       double pcntError = 0.1;
       
       
       double startTime=0.0, endTime=660.0;
       RandomSeedGenerator rsg = new RandomSeedGenerator();
       Seeds sds = new Seeds(rsg);
       // Simulation object

       // Lets get a set of uncorrelated seeds
       
       //for(int i=0 ; i<NUMRUNS ; i++) sds[i] = new Seeds(rsg);
       
       // Loop for NUMRUN simulation runs for each case
       // Case 1
       System.out.println(" Case 1");
       double turnaroundTime = 0;
       
       while (turnaroundTime <= 1){
    	   for (int i = 0; i < 6; i++){
    		   System.out.println("BEFOREE CONSTRUCTORR AROUND TIME  ______" );
    		   FSB mname = new FSB(startTime,endTime,numClerks,numOfficers,pcntError,6,20,sds);
    		   mname.setTimef(endTime);
    		   System.out.println("BEFFOREEEEEEEE AROUND TIME  ______" + mname.getAvgTotalTurnAroundTime());
    		   mname.runSimulation();
    		   System.out.println("AFTERRRRRRR AROUND TIME  ______" + mname.getAvgTotalTurnAroundTime());
    		   turnaroundTime = mname.getAvgTotalTurnAroundTime();
    		   System.out.println("TURN AROUND TIME  ______" + mname.getAvgTotalTurnAroundTime());
    		   mname.rgOfficers[i].numOfficers++;
    	   }
       }
          // See examples for hints on collecting output
          // and developping code for analysis
          //clerks = 8
          
 
   
}}
