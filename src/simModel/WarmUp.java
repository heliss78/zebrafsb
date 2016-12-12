package simModel;

import simModel.*;
import cern.jet.random.engine.*;

class WarmUp{

    public static void main(String[] args)
    {

        int numClerks = 8;
        int[] numOfficers = {4,2,6,3,2,2};
        double pcntError = 0.1;

        double startTime=0.0, endTime=600.0;
        double window = 60.0;
        RandomSeedGenerator rsg = new RandomSeedGenerator();
        Seeds sds = new Seeds(rsg);


        System.out.println(" Case 1");
        double turnaroundTime = 41;
        double windowStart = startTime - 5;
        double[] avg = new double[50];
        for (int i = 0;i<50;i++) {
            windowStart += 5;
            avg[i] = 0;
            for (int j = 0; j < 20; j++) {

                FSB mname = new FSB(startTime, endTime, numClerks, numOfficers, pcntError, 6, 20, sds);
                mname.setTimef(windowStart);

                mname.runSimulation();

                for (int q = 0;q<6;q++) {
                    mname.clearAvgApprovalTurnAroundTime(q);
                }
                mname.clearAvgTotalTurnAroundTime();

                mname.setTimef(windowStart + window);
                mname.runSimulation();

                turnaroundTime = mname.getAvgTotalTurnAroundTime();
                avg[i] += turnaroundTime;
            }

            avg[i] = avg[i]/20;
        }
        for (int i =0;i<50;i++){
            System.out.println(avg[i]);
        }
    }
}

