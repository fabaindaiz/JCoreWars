package fabaindaiz.jcorewars.marsVM;

import fabaindaiz.jcorewars.warrior.WarriorExecutor;
import fabaindaiz.jcorewars.memory.Memory;

/**
 * Represents a class which report all step execution changes
 */
public class StepReport {

    public Memory[] core;
    protected WarriorExecutor warrior;

    protected int[] readAddr;
    protected int[] writeAddr;
    protected int[] decAddr;
    protected int[] incAddr;

    protected int numRead;
    protected int numWrite;
    protected int numDec;
    protected int numInc;

    protected int numProc;
    protected int execAddr;

    protected boolean pDie;
    protected boolean wDeath;

    protected final static int MAX_READS = 4;
    protected final static int MAX_WRITES = 4;
    protected final static int MAX_DECS = 5;
    protected final static int MAX_INCS = 5;

    /**
     * @param core Core
     * @param warrior Current warrior execution
     */
    public StepReport(Memory[] core, WarriorExecutor warrior) {
        this.core = core;
        this.warrior = warrior;

        readAddr = new int[MAX_READS];
        writeAddr = new int[MAX_WRITES];
        decAddr = new int[MAX_DECS];
        incAddr = new int[MAX_INCS];

        numRead = 0;
        numWrite = 0;
        numDec = 0;
        numInc = 0;

        execAddr = -1;
        pDie = false;
        wDeath = false;
    }

    /**
     * Set the core
     * @param core Core
     */
    public void setCore(Memory[] core) {
        this.core = core;
    }

    /**
     * Set the Warrior object
     * @param warrior - Warrior object of currently executing warrior.
     */
    public void warrior(WarriorExecutor warrior) {
        this.warrior = warrior;
    }

    /**
     * Set a location read from.
     * @param addr - address of location read
     */
    public void read(int addr) {
        readAddr[numRead] = addr;
        numRead++;
    }

    /**
     * Set a location that was written to
     * @param addr - address written to
     */
    public void write(int addr) {
        writeAddr[numWrite] = addr;
        numWrite++;
    }

    /**
     * Set a location that was decremented
     * @param addr - address that was decremented
     */
    public void decrement(int addr) {
        decAddr[numDec] = addr;
        numDec++;
    }

    /**
     * Set a location that was incremented
     * @param addr - address that was incremented
     */
    public void increment(int addr) {
        incAddr[numInc] = addr;
        numInc++;
    }

    /**
     * Set the location that was executed
     * @param addr - address that was executed
     */
    public void execute(int addr) {
        execAddr = addr;
    }

    /**
     * set the number of processes in the current warrior.
     * @param numP - number of processes
     */
    public void numProc(int numP) {
        numProc = numP;
    }

    /**
     * called if a process dies
     */
    public void pDie() {
        pDie = true;
    }

    /**
     * called if warrior dies
     */
    public void wDie() {
        wDeath = true;
    }

    /**
     * Get the warrior object of the currently executing warrior
     * @return old.marsVM.WarriorObj
     */
    public WarriorExecutor warrior() {
        return warrior;
    }

    /**
     * Get the addresses read
     * @return int[] - array of addresses
     */
    public int[] addrRead() {
        int[] value = new int[numRead];
        if (numRead >= 0) System.arraycopy(readAddr, 0, value, 0, numRead);
        return value;
    }

    /**
     * Get the addresses written to
     * @return int[] - array of addresses
     */
    public int[] addrWrite() {
        int[] value = new int[numWrite];
        if (numWrite >= 0) System.arraycopy(writeAddr, 0, value, 0, numWrite);
        return value;
    }

    /**
     * Get the addresses decremented
     * @return int[] - array of addresses
     */
    public int[] addrDec() {
        int[] value = new int[numDec];
        if (numDec >= 0) System.arraycopy(decAddr, 0, value, 0, numDec);
        return value;
    }

    /**
     * Get the addresses incremented
     * @return int[] - array of addresses
     */
    public int[] addrInc() {
        int[] value = new int[numInc];
        if (numInc >= 0) System.arraycopy(incAddr, 0, value, 0, numInc);
        return value;
    }

    /**
     * Get the address executed
     * @return int - address
     */
    public int addrExec() {
        return execAddr;
    }

    /**
     * Get the number of processes
     * @return int - number of processes
     */
    public int numProc() {
        return numProc;
    }

    /**
     * Check to see if current process died
     * @return boolean - true if process died
     */
    public boolean pDeath() {
        return pDie;
    }

    /**
     * Check to see if warrior died
     * @return boolean - true if warrior died
     */
    public boolean wDeath() {
        return wDeath;
    }

}
