package fabaindaiz.jcorewars.marsVM;

import fabaindaiz.jcorewars.warrior.WarriorExecutor;
import fabaindaiz.jcorewars.warrior.WarriorLoader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Date;
import java.util.Vector;

/**
 * Represents a class which manage redcore execution cycles and rounds
 */
public class MarsCore implements Runnable {

    AplicationCore application;
    MarsVM MARS;

    // Common variables
    int rounds;
    int coreSize;
    int cycles;
    int maxProc;
    int maxWarriorLength;
    int minWarriorDistance;
    int pSpaceSize;

    boolean pSpaceChanged = false;
    boolean pausedMode = false;

    public static Thread thread;

    WarriorLoader[] warriors;
    Vector<String> pathWarriors;
    int numWarriors;
    int minWarriors;

    int roundNum;
    int cycleNum;
    int warRun;
    int runWarriors;

    Date startTime;
    Date endTime;
    double totalTime;

    /**
     * @param aplication Application main class
     */
    public MarsCore(AplicationCore aplication) {
        this.application = aplication;

        rounds = 10;
        coreSize = 8000;
        cycles = 80000;
        maxProc = 8000;
        maxWarriorLength = 100;
        minWarriorDistance = 100;
    }

    /**
     * Initialize all variables and warriors
     */
    public void init() {
        if (!pSpaceChanged) {
            pSpaceSize = coreSize / 16;
        }
        warriors = new WarriorLoader[numWarriors];

        for (int i=0; i<numWarriors; i++) {
            try {
                FileReader wFile = new FileReader(pathWarriors.get(i));
                warriors[i] = new WarriorLoader(wFile, maxWarriorLength);
                warriors[i].initPSpace(pSpaceSize);
                warriors[i].setPCell(0, -1);
            } catch (FileNotFoundException e)
            {
                System.out.println("Could not find warrior file " + pathWarriors.get(i));
                System.exit(0);
            }
        }
        MARS = new MarsVM(coreSize, maxProc);
        MARS.warriorManager.loadWarriors(warriors, minWarriorDistance, maxWarriorLength);
        application.application_update();

        runWarriors = numWarriors;
        minWarriors = (numWarriors == 1) ? 0 : 1;
        roundNum = 0;
        cycleNum = 0;
        warRun = 0;

        thread = new Thread(this);
        thread.setPriority(Thread.NORM_PRIORITY-1);
        thread.start();
    }

    /**
     * Run the main warriors execution program
     */
    @Override
    public void run() {
        startTime = new Date();

        for (; roundNum<rounds; roundNum++)
        {
            for (; cycleNum<cycles; cycleNum++)
            {
                executeCycle();

                endTime = new Date();

                if (pausedMode) {
                    try { thread.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace(); }
                }

                if (runWarriors <= minWarriors)
                    break;
            }

            application.notifyRoundListeners(roundNum);

            totalTime = ((double) endTime.getTime() - (double) startTime.getTime()) / 1000;
            System.out.println("Round="+ (roundNum+1) +"  Total time="+ totalTime +"  Cycles="+ cycleNum +"  Avg. time/cycle="+ (totalTime/cycleNum));

            try { Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace(); }

            application.coreDisplay.clear();
            startTime = new Date();

            MARS.reset();
            MARS.warriorManager.loadWarriors(warriors, minWarriorDistance, maxWarriorLength);
            application.application_update();
            runWarriors = numWarriors;

            cycleNum = 0;
        }
    }

    /**
     * Execute a cycle of warriors simulation
     */
    public void executeCycle() {
        for (; warRun < runWarriors; warRun++) {
            StepReport stats = MARS.executeInstruction();

            WarriorExecutor warrior = stats.warrior();
            //warrior.numProc = stats.numProc();

            if (stats.wDeath()) {
                runWarriors--;
            }

            application.notifyStepListeners(stats);
        }

        application.notifyCycleListeners(cycleNum);

        warRun = 0;
    }

    /**
     * Sets core size
     * @param i Core size
     */
    public void setCoreSize(int i) {
        coreSize = i;
    }

    /**
     * Sets max number of rounds
     * @param i Rounds
     */
    public void setRounds(int i) {
        rounds = i;
    }

    /**
     * Sets max number of cycles
     * @param i Cycles
     */
    public void setCycles(int i) {
        cycles = i;
    }

    /**
     * Sets max number of warrior proces
     * @param i Max number of process
     */
    public void setMaxProc(int i) {
        maxProc = i;
    }

    /**
     * Sets max warrior length
     * @param i Max warrior length
     */
    public void setMaxWarriorLength(int i) {
        maxWarriorLength = i;
    }

    /**
     * Sets min warrior distance
     * @param i Min warrior distance
     */
    public void setMinWarriorDistance(int i) {
        minWarriorDistance = i;
    }

    /**
     * Sets pSpace size
     * @param i pSpace size
     */
    public void setPSpaceSize(int i) {
        pSpaceSize = i;
        pSpaceChanged = true;
    }

    /**
     * Sets warriors num
     * @param i Warriors vector
     */
    public void setWarriors(Vector<String> i){
        numWarriors = i.size();
        pathWarriors = i;
    }
}
