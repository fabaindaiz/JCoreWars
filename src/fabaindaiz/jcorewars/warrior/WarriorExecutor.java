package fabaindaiz.jcorewars.warrior;

import fabaindaiz.jcorewars.marsVM.StepReport;

public class WarriorExecutor {

    protected WarriorProcess<Integer> processQueue;
    protected WarriorLoader warriorLoader;
    protected int[] pSpace;

    public StepReport lastReport;

    public WarriorExecutor (WarriorLoader loader, int firstInst, int[] pSpace) {
        loader.setWarriorExecutor(this);
        warriorLoader = loader;

        processQueue = new WarriorProcess<>();
        processQueue.addElement(firstInst);
        this.pSpace = pSpace;
    }

    public WarriorLoader getLoader() {
        return warriorLoader;
    }

    public WarriorProcess<Integer> processQueue() {
        return processQueue;
    }

    public void addProcess(int instr) {
        processQueue.addElement(instr);
    }

    public int getProcess() {
        return processQueue.remove(0);
    }

    public int numProcess() {
        return processQueue.size();
    }

    public int[] getPSpace() {
        return pSpace;
    }

    public void setPCell(int addr, int value) {
        pSpace[addr%pSpace.length] = value;
    }

    public int getPCell(int addr) {
        return pSpace[addr%pSpace.length];
    }

}
