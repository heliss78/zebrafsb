import simModel.*;
import cern.jet.random.engine.*;

class Validation{

    public static void main(String[] args)
    {

        int numClerks = 8;
        int[] numOfficers = {4,2,6,3,2,2};
        double pcntError = 0.1;

        double startTime=0.0, endTime=40.0;
        RandomSeedGenerator rsg = new RandomSeedGenerator();
        Seeds sds = new Seeds(rsg);

                FSB mname = new FSB(startTime, endTime, numClerks, numOfficers, pcntError, 6, 20, sds);
                mname.setTimef(endTime);

                mname.runSimulation();

        System.out.println("Average turnaround time is " + mname.getAvgTotalTurnAroundTime());
    }
}

