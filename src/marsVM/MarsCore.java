package marsVM;

import steplistener.*;
import listener.CycleListener;
import listener.RoundCycleCounter;
import listener.RoundListener;
import steplistener.StepListener;

import javax.swing.*;
import java.awt.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Date;
import java.util.Enumeration;
import java.util.Vector;

public class MarsCore implements Runnable, FrontEndManager {

    // constants
    static final int numDefinedColors = 4;
    static final Color[][] wColors = {{Color.green.darker(), Color.yellow},
            {Color.cyan.darker(), Color.blue},
            {Color.red.darker(), Color.magenta},
            {Color.lightGray.darker(), Color.gray}};

    // Common variables
    int rounds;
    int coreSize;
    int cycles;
    int maxProc;
    int maxWarriorLength;
    int minWarriorDistance;
    int pSpaceSize;

    boolean usingInterface = false;
    boolean pSpaceChanged = false;
    boolean pausedMode = false;


    Vector<String> pathWarriors;
    int numWarriors;
    int minWarriors;

    WarriorObj[] warriors;
    RoundCycleCounter roundCycleCounter;
    MarsVM MARS;

    CoreDisplay coreDisplay;
    CoreList coreList;
    ProcList procList;

    int roundNum;
    int cycleNum;
    int warRun;
    int runWarriors;

    static Thread thread;

    Vector<StepListener> stepListeners;
    Vector<CycleListener> cycleListeners;
    Vector<RoundListener> roundListeners;

    Date startTime;
    Date endTime;
    double totalTime;

    public MarsCore() {

        stepListeners = new Vector<>();
        cycleListeners = new Vector<>();
        roundListeners = new Vector<>();
    }

    public void setRounds(int i) {
        rounds = i;
    }

    public void setCoreSize(int i) {
        coreSize = i;
    }

    public void setCycles(int i) {
        cycles = i;
    }

    public void setMaxProc(int i) {
        maxProc = i;
    }

    public void setMaxWarriorLength(int i) {
        maxWarriorLength = i;
    }

    public void setMinWarriorDistance(int i) {
        minWarriorDistance = i;
    }

    public void setPSpaceSize(int i) {
        pSpaceSize = i;
        pSpaceChanged = true;
    }

    public void setWarriors(Vector<String> i){
        numWarriors = i.size();
        pathWarriors = i;
    }

    public void application_coreList(JSplitPane con) {
        coreList = new CoreList(this, con);
    }

    public void application_procList(JSplitPane con) {
        procList = new ProcList(this, con);
    }

    public void application_update() {
        if (usingInterface)
        {
            coreList.loadCore(MARS.core);

            Vector<CustomListModel<Integer>> procs = new Vector<>();
            for (WarriorObj war:warriors) {
                procs.add(war.warriorRT.procQueue);
            }
            procList.loadProc(procs);

        }
    }

    /**
     * Initialization function for the application.
     */
    public void application_init()
    {

        // Set defaults for various constants
        rounds = 10;
        coreSize = 8000;
        cycles = 80000;
        maxProc = 8000;
        maxWarriorLength = 100;
        minWarriorDistance = 100;

    }

    public void application_display(Container con) {
        coreDisplay = new CoreDisplay(this, con, coreSize);
        roundCycleCounter = new RoundCycleCounter(this, con);
    }

    public void interface_display(Container con) {
        coreDisplay = new CoreDisplay(this, con, coreSize);
        roundCycleCounter = new RoundCycleCounter(this, con);

        usingInterface = true;
    }

    public void application_start() {

        if (!pSpaceChanged)
            pSpaceSize = coreSize / 16;

        warriors = new WarriorObj[numWarriors];

        for (int i=0; i<numWarriors; i++)
        {
            try
            {
                FileReader wFile = new FileReader(pathWarriors.get(i));
                warriors[i] = new WarriorObj(wFile, maxWarriorLength, wColors[i % numDefinedColors][0], wColors[i % numDefinedColors][1]);
                warriors[i].initPSpace(pSpaceSize);
                warriors[i].setPCell(0, -1);
            } catch (FileNotFoundException e)
            {
                System.out.println("Could not find warrior file " + pathWarriors.get(i));
                System.exit(0);
            }
        }

        MARS = new MarsVM(coreSize, maxProc);

        loadWarriors();

        application_update();

        runWarriors = numWarriors;
        minWarriors = (numWarriors == 1) ? 0 : 1;
        roundNum = 0;
        cycleNum = 0;
        warRun = 0;

        thread = new Thread(this);
        thread.setPriority(Thread.NORM_PRIORITY-1);
        thread.start();
    }

    public void application_step() {
        thread.notify();
    }

    /**
     * main function and loop for jMARS. Runs the battles and handles display.
     */
    public void run()
    {
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

            notifyRoundListeners(roundNum);

            totalTime = ((double) endTime.getTime() - (double) startTime.getTime()) / 1000;
            System.out.println("Round="+ (roundNum+1) +"  Total time="+ totalTime +"  Cycles="+ cycleNum +"  Avg. time/cycle="+ (totalTime/cycleNum));

            try { Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace(); }

            coreDisplay.clear();
            startTime = new Date();

            MARS.reset();
            runWarriors = numWarriors;
            loadWarriors();

            application_update();

            cycleNum = 0;
        }
    }

    public void executeCycle() {
        for (; warRun < runWarriors; warRun++) {
            StepReport stats = MARS.executeInstr();

            WarriorObj war = stats.warrior();
            war.numProc = stats.numProc();

            if (stats.wDeath()) {
                war.Alive = false;
                runWarriors--;
            }

            notifyStepListeners(stats);
        }

        notifyCycleListeners(cycleNum);

        warRun = 0;
    }

    /**
     * Load warriors into core
     */
    void loadWarriors()
    {
        int[] location = new int[warriors.length];

        if (MARS.loadWarrior(warriors[0], 0)) {
            //coreDisplay.paintWarrior(warriors[0], 0);
        } else {
            System.out.println("ERROR: could not load warrior 1.");
        }

        for (int i=1, r; i<numWarriors; i++)
        {
            boolean validSpot;
            do
            {
                validSpot = true;
                r = (int) (Math.random() * coreSize);

                if (r < minWarriorDistance || r > (coreSize - minWarriorDistance))
                    validSpot = false;

                for (int k : location)
                    if (r < (minWarriorDistance + k) && r > (minWarriorDistance + k)) {
                        validSpot = false;
                        break;
                    }
            } while (!validSpot);

            if (MARS.loadWarrior(warriors[i], r)) {
                //coreDisplay.paintWarrior(warriors[i], r);
            } else {
                System.out.println("ERROR: could not load warrior " + (i+1) + ".");
            }
        }
    }

    public void registerStepListener(StepListener l)
    {
        stepListeners.addElement(l);
    }

    public void registerCycleListener(CycleListener c)
    {
        cycleListeners.addElement(c);
    }

    public void registerRoundListener(RoundListener r)
    {
        roundListeners.addElement(r);
    }

    protected void notifyStepListeners(StepReport step)
    {
        for (Enumeration<StepListener> e = stepListeners.elements(); e.hasMoreElements(); )
        {
            StepListener j = e.nextElement();
            if (step.warrior().lastReport != null)
                j.endProcess(step.warrior().lastReport);
            j.stepProcess(step);
        }

        step.warrior().lastReport = step;
    }

    protected void notifyCycleListeners(int cycle)
    {
        for (Enumeration<CycleListener> e = cycleListeners.elements(); e.hasMoreElements(); )
        {
            CycleListener j = e.nextElement();
            j.cycleFinished(cycle);
        }
    }

    protected void notifyRoundListeners(int round)
    {
        for (Enumeration<RoundListener> e = roundListeners.elements(); e.hasMoreElements(); )
        {
            RoundListener j = e.nextElement();
            j.roundResults(round);
        }
    }

}
