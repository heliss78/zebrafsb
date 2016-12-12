// File: Experiment.java
// Description:

import simModel.*;
import cern.jet.random.engine.*;
import outputAnalysis.ConfidenceInterval;

// Main Method: Experiments
// 
class Experiment
{
   public static void main(String[] args)
   {
	   
       int numClerks = 30;
       int[] numOfficers = {4,2,6,3,2,2};
       double pcntError = 0.05;
       double targetTime = 26;
       double avgApprovalTime = 22;
       double avgEntryTime = 7;
       int addedWorkers = 0;
       double[] turnaroundTimeArray = {0,0,0,0,0,0,0,0,0,0};
       double arraySum = 0;
       
       
       double startTime=0.0, endTime=3000.0;
       RandomSeedGenerator rsg = new RandomSeedGenerator();
       Seeds sds = new Seeds(rsg);
       // Simulation object
       
       // Loop for NUMRUN simulation runs for each case
       double turnaroundTime = targetTime+1;
       double districtTurnAround[] = new double[6];
       FSB mname;

       for (int j = 0; j < 3; j ++) {
    	   System.out.println(" Case " + j);
           addedWorkers = 0;
           avgEntryTime--;
           avgApprovalTime-=2;
           turnaroundTime = targetTime+1;
           numClerks = 30;
           numOfficers[0] = 4;
           numOfficers[1] = 2;
           numOfficers[2] = 6;
           numOfficers[3] = 3;
           numOfficers[4] = 2;
           numOfficers[5] = 2;

           while (turnaroundTime > targetTime && (addedWorkers < 10)) { // give up if you add so many workers and still don't get a reasonable time
        	   arraySum = 0;
        	   for (int q = 0 ;q<10; q++){
            	   mname = new FSB(startTime, endTime, numClerks, numOfficers, pcntError, avgEntryTime, avgApprovalTime, sds);
            	   mname.setTimef(endTime);
            	   mname.runSimulation();
            	   turnaroundTimeArray[q] = mname.getAvgTotalTurnAroundTime();
            	   arraySum += turnaroundTimeArray[q];
               }
               turnaroundTime = arraySum / 10;
               
               mname = new FSB(startTime, endTime, numClerks, numOfficers, pcntError, avgEntryTime, avgApprovalTime, sds);
        	   mname.setTimef(endTime);
        	   mname.runSimulation();
        	   
               for (int i = 0; i < 6; i++) {
                   districtTurnAround[i] = mname.getAvgApprovalTurnAroundTime(i);
                   if (districtTurnAround[i] > targetTime) {
                       numOfficers[i]++;
                       addedWorkers ++;
                       i=7;
                   }
               }
           }
           ConfidenceInterval ciTurnaroundTime = new ConfidenceInterval(turnaroundTimeArray, 0.95);
           double ciMin = ciTurnaroundTime.getCfMin(), ciMax = ciTurnaroundTime.getCfMax();
           System.out.println("After adding officers, in case " + j + ". Average time is" + turnaroundTime + ". Confidence is [" + ciMin + "," + ciMax + "].");
           
           
           
           numClerks = 8;
           turnaroundTime = targetTime + 1;
           while (turnaroundTime > targetTime && (addedWorkers < 10)) {
        	   arraySum = 0;
        	   for (int q = 0 ;q<10; q++){
            	   mname = new FSB(startTime, endTime, numClerks, numOfficers, pcntError, avgEntryTime, avgApprovalTime, sds);
            	   mname.setTimef(endTime);
            	   mname.runSimulation();
            	   turnaroundTimeArray[q] = mname.getAvgTotalTurnAroundTime();
            	   arraySum+= turnaroundTimeArray[q];
               }
        	   turnaroundTime = arraySum / 10;
               
               if (turnaroundTime > targetTime) {
                   numClerks++;
               }
           }
           ConfidenceInterval ciTurnaroundTime2 = new ConfidenceInterval(turnaroundTimeArray, 0.95);
           double ciMin2 = ciTurnaroundTime2.getCfMin(), ciMax2 = ciTurnaroundTime2.getCfMax();
           System.out.println("After adding clerks, in case " + j + ". Average time is" + turnaroundTime + ". Confidence is [" + ciMin2 + "," + ciMax2 + "].");
       }
    }
}

