package fabaindaiz.jcorewars.marsVM;

import fabaindaiz.jcorewars.memory.Memory;
import fabaindaiz.jcorewars.warrior.WarriorManager;

/**
 * Represents a class which manage the redcode execution
 */
public class MarsVM {

    public final Memory[] core;
    public final int coreSize;
    public final int maxProc;

    public WarriorManager warriorManager;

    /**
     * @param coreSize Core size
     * @param maxProc  Max number of process
     */
    public MarsVM(int coreSize, int maxProc) {

        this.core = new Memory[coreSize];
        this.coreSize = coreSize;
        this.maxProc = maxProc;

        warriorManager = new WarriorManager(this);
        for (int i = 0; i < core.length; i++) {
            core[i] = new Memory();
        }
    }

    /**
     * Reset the redcode execution
     */
    public void reset() {
        warriorManager.saveWarriors();
        warriorManager = new WarriorManager(this);
        for (int i = 0; i < core.length; i++) {
            core[i] = new Memory();
        }
    }

    /**
     * Copy warrior instructions to core
     *
     * @param warriorMemory Warrior instructions array
     * @param startPosition Warrior start position
     * @return true if copy complete, otherwise false
     */
    public boolean copyWarrior(Memory[] warriorMemory, int startPosition) {
        if ((startPosition + warriorMemory.length - 1) > coreSize) {
            return false;
        }
        for (int i = 0; i < warriorMemory.length; i++) {
            core[startPosition + i].copy(warriorMemory[i]);
        }

        return true;
    }

    /**
     * Execute the current instruction from current warrior
     *
     * @return true if execution complete, otherwise false
     */
    public StepReport executeInstruction() {
        StepExecutor stepExecutor = new StepExecutor(this);
        return stepExecutor.executeStep();
    }

}
