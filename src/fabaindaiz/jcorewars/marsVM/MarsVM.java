package fabaindaiz.jcorewars.marsVM;

import fabaindaiz.jcorewars.memory.Memory;
import fabaindaiz.jcorewars.warrior.WarriorManager;

public class MarsVM {

    public final Memory[] core;
    public final int coreSize;
    public final int maxProc;

    public WarriorManager warriorManager;

    public MarsVM(int coreSize, int maxProc) {

        this.core = new Memory[coreSize];
        this.coreSize = coreSize;
        this.maxProc = maxProc;

        reset();
    }

    public void reset() {
        warriorManager = new WarriorManager(this);
        for (int i = 0; i < core.length; i++) {
            core[i] = new Memory();
        }
    }

    public boolean copyWarrior(Memory[] warriorMemory, int startPosition) {
        if ((startPosition + warriorMemory.length - 1) > coreSize) {
            return false;
        }
        for (int i = 0; i < warriorMemory.length; i++) {
            core[startPosition + i].copy(warriorMemory[i]);
        }

        return true;
    }

    public StepReport executeInstruction() {
        StepExecutor stepExecutor = new StepExecutor(this);
        return stepExecutor.executeStep();
    }

}
