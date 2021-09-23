package fabaindaiz.jcorewars.warrior;

import fabaindaiz.jcorewars.marsVM.StepReport;

/**
 * Represents a class which can execute warrior process
 */
public class WarriorExecutor {

    public StepReport lastReport;
    protected WarriorProcess<Integer> processQueue;
    protected WarriorLoader warriorLoader;
    protected int[] pSpace;

    /**
     * @param loader    Warrior loader
     * @param firstInst Warrior first instruction reference
     * @param pSpace    Warrior pSpace
     */
    public WarriorExecutor(WarriorLoader loader, int firstInst, int[] pSpace) {
        loader.setWarriorExecutor(this);
        warriorLoader = loader;

        processQueue = new WarriorProcess<>();
        processQueue.addElement(firstInst);
        this.pSpace = pSpace;
    }

    /**
     * Return the current warrior loader
     *
     * @return Warrior loader
     */
    public WarriorLoader getLoader() {
        return warriorLoader;
    }

    /**
     * Return the warrior process queue
     *
     * @return Process queue
     */
    public WarriorProcess<Integer> processQueue() {
        return processQueue;
    }

    /**
     * Adds a instruction to warrior process queue
     *
     * @param instr next instruction
     */
    public void addProcess(int instr) {
        processQueue.addElement(instr);
    }

    /**
     * Gets the next instruction to execute
     *
     * @return current instruction
     */
    public int getProcess() {
        return processQueue.remove(0);
    }

    /**
     * Gets the number of warrior active process
     *
     * @return number of process
     */
    public int numProcess() {
        return processQueue.size();
    }

    /**
     * Gets the pSpace register
     *
     * @return pSpace register
     */
    public int[] getPSpace() {
        return pSpace;
    }

    /**
     * Sets a value into pSpace register
     *
     * @param addr  PSpace address
     * @param value Value to store
     */
    public void setPCell(int addr, int value) {
        pSpace[addr % pSpace.length] = value;
    }

    /**
     * Gets a value from pSpace register
     *
     * @param addr PSpace address
     * @return Value from pSpace
     */
    public int getPCell(int addr) {
        return pSpace[addr % pSpace.length];
    }

}
