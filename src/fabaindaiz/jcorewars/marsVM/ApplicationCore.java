package fabaindaiz.jcorewars.marsVM;

import fabaindaiz.jcorewars.listener.CycleListener;
import fabaindaiz.jcorewars.listener.RoundCycleCounter;
import fabaindaiz.jcorewars.listener.RoundListener;
import fabaindaiz.jcorewars.listener.StepListener;
import fabaindaiz.jcorewars.steplistener.CoreDisplay;
import fabaindaiz.jcorewars.steplistener.CoreList;
import fabaindaiz.jcorewars.steplistener.ProcList;
import fabaindaiz.jcorewars.warrior.WarriorLoader;
import fabaindaiz.jcorewars.warrior.WarriorProcess;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

/**
 * Represents a class which manage application execution
 */
public class ApplicationCore implements FrontEndManager {

    static final Color[][] wColors = {{Color.green.darker(), Color.yellow},
            {Color.cyan.darker(), Color.blue},
            {Color.red.darker(), Color.magenta},
            {Color.lightGray.darker(), Color.gray}};

    static final Color[][] dynColors = {{Color.green.darker(), Color.green, Color.green.brighter()},
            {Color.cyan.darker(), Color.cyan, Color.cyan.brighter()},
            {Color.red.darker(), Color.red, Color.red.brighter()},
            {Color.lightGray.darker(), Color.lightGray, Color.lightGray.brighter()}};

    static final Color[][] Colors = {{Color.green.darker(), Color.green.darker(), Color.green.darker()},
            {Color.cyan.darker(), Color.cyan.darker(), Color.cyan.darker()},
            {Color.red.darker(), Color.red.darker(), Color.red.darker()},
            {Color.lightGray.darker(), Color.lightGray.darker(), Color.lightGray.darker()}};

    public MarsCore core;

    protected Vector<StepListener> stepListeners;
    protected Vector<CycleListener> cycleListeners;
    protected Vector<RoundListener> roundListeners;

    protected RoundCycleCounter roundCycleCounter;

    protected CoreDisplay coreDisplay;
    protected CoreList coreList;
    protected ProcList procList;

    public ApplicationCore() {
        core = new MarsCore(this);

        stepListeners = new Vector<>();
        cycleListeners = new Vector<>();
        roundListeners = new Vector<>();
    }

    /**
     * Sets core list display
     *
     * @param con Parent display jpane
     */
    public void application_coreList(JSplitPane con) {
        coreList = new CoreList(this, con);
    }

    /**
     * Sets process list display
     *
     * @param con Parent display jpane
     */
    public void application_procList(JSplitPane con) {
        procList = new ProcList(this, con);
    }

    /**
     * Sets application counters
     *
     * @param con Parent display jpane
     */
    public void application_display(Container con) {
        coreDisplay = new CoreDisplay(this, con, core.coreSize);
        roundCycleCounter = new RoundCycleCounter(this, con);
    }

    /**
     * Start the application execution
     */
    public void application_start() {
        core.init();
    }

    /**
     * Step the application execution
     */
    public void application_step() {
        core.thread.notify();
    }

    /**
     * Update main application displays
     */
    public void application_update() {
        coreList.loadCore(core.MARS.core);

        Vector<WarriorProcess<Integer>> procs = new Vector<>();
        Vector<Color> color = new Vector<>();

        for (WarriorLoader warrior : core.warriors) {
            procs.add(warrior.warriorExecutor.processQueue());
            color.add(wColors[warrior.num][0]);
        }

        procList.loadProc(procs, color);
    }

    @Override
    public Color getColor(int num) {
        return wColors[num][0];
    }

    @Override
    public Color getDColor(int num) {
        return wColors[num][1];
    }

    @Override
    public Color[] getDynColors(int num) {
        return dynColors[num];
    }

    @Override
    public Color[] getColors(int num) {
        return Colors[num];
    }

    @Override
    public void registerStepListener(StepListener l) {
        stepListeners.addElement(l);
    }

    @Override
    public void registerCycleListener(CycleListener c) {
        cycleListeners.addElement(c);
    }

    @Override
    public void registerRoundListener(RoundListener r) {
        roundListeners.addElement(r);
    }

    /**
     * Execute all process before notify step listeners
     *
     * @param report Report of step execution
     */
    protected void notifyStepListeners(StepReport report) {
        stepListeners.forEach((stepListener -> {
            if (report.warrior().lastReport != null) {
                stepListener.endProcess(report.warrior().lastReport);
            }
            stepListener.stepProcess(report);
        }));
        report.warrior().lastReport = report;
    }

    /**
     * Execute all process before notify cycle listeners
     *
     * @param cycle Cycle number
     */
    protected void notifyCycleListeners(int cycle) {
        cycleListeners.forEach((cycleListener -> cycleListener.cycleFinished(cycle)));
    }

    /**
     * Execute all process before notify round listeners
     *
     * @param round Round number
     */
    protected void notifyRoundListeners(int round) {
        roundListeners.forEach((roundListener -> roundListener.roundResults(round)));
    }

}
