package fabaindaiz.jcorewars.memory.operator;

import fabaindaiz.jcorewars.marsVM.StepExecutor;
import fabaindaiz.jcorewars.memory.Memory;

/**
 * SLT compares two values, and skips the next instruction if the first is lower than the second
 */
public class SLT implements _IOperator {

    @Override
    public String toString() {
        return "SLT";
    }

    @Override
    public void preExecute(StepExecutor addr) {
        addr.report.read(addr.addrA);
        addr.report.read(addr.addrB);
    }

    @Override
    public void postExecute(StepExecutor addr) {
        return;
    }

    @Override
    public boolean executeI(Memory[] core, StepExecutor addr) {
        if ((addr.addrAAValue < addr.addrBAValue) && (addr.addrABValue < addr.addrBBValue))
            return execute(addr);
        return true;
    }

    @Override
    public boolean executeA(Memory[] core, StepExecutor addr) {
        if (addr.addrAAValue < addr.addrBAValue)
            return execute(addr);
        return true;
    }

    @Override
    public boolean executeB(Memory[] core, StepExecutor addr) {
        if (addr.addrABValue < addr.addrBBValue)
            return execute(addr);
        return true;
    }

    @Override
    public boolean executeAB(Memory[] core, StepExecutor addr) {
        if (addr.addrAAValue < addr.addrBBValue)
            return execute(addr);
        return true;
    }

    @Override
    public boolean executeBA(Memory[] core, StepExecutor addr) {
        if (addr.addrABValue <= addr.addrBAValue)
            return execute(addr);
        return true;
    }

    @Override
    public boolean executeF(Memory[] core, StepExecutor addr) {
        return executeI(core, addr);
    }

    @Override
    public boolean executeX(Memory[] core, StepExecutor addr) {
        if ((addr.addrAAValue < addr.addrBBValue) && (addr.addrABValue < addr.addrBAValue))
            return execute(addr);
        return true;
    }

    private boolean execute(StepExecutor addr) {
        addr.warrior.addProcess((addr.currentInstr + 2) % addr.executer.coreSize);
        addr.manager.nextWarrior();
        return false;
    }
}
