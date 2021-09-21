package fabaindaiz.jcorewars.memory.operator;

import fabaindaiz.jcorewars.marsVM.StepExecutor;
import fabaindaiz.jcorewars.memory.Memory;

public class DJN implements _IOperator {

    @Override
    public String toString() {
        return "DJN";
    }

    @Override
    public void preExecute(StepExecutor addr) {
        addr.report.decrement(addr.addrB);
    }

    @Override
    public void postExecute(StepExecutor addr) {
        return;
    }

    @Override
    public boolean executeI(Memory[] core, StepExecutor addr) {
        if (--core[addr.addrB].aValue < 0)
            core[addr.addrB].aValue = addr.executer.coreSize - 1;
        if (--core[addr.addrB].bValue < 0)
            core[addr.addrB].bValue = addr.executer.coreSize - 1;
        if ((addr.addrBAValue != 1) || (addr.addrBBValue != 1))
            return execute(addr);
        return true;
    }

    @Override
    public boolean executeA(Memory[] core, StepExecutor addr) {
        return executeBA(core, addr);
    }

    @Override
    public boolean executeB(Memory[] core, StepExecutor addr) {
        return executeAB(core, addr);
    }

    @Override
    public boolean executeAB(Memory[] core, StepExecutor addr) {
        if (--core[addr.addrB].bValue < 0)
            core[addr.addrB].bValue = addr.executer.coreSize - 1;
        if (addr.addrBBValue != 1)
            return execute(addr);
        return true;
    }

    @Override
    public boolean executeBA(Memory[] core, StepExecutor addr) {
        if (--core[addr.addrB].aValue < 0)
            core[addr.addrB].aValue = addr.executer.coreSize - 1;
        if (addr.addrBAValue != 1)
            return execute(addr);
        return true;
    }

    @Override
    public boolean executeF(Memory[] core, StepExecutor addr) {
        return executeI(core, addr);
    }

    @Override
    public boolean executeX(Memory[] core, StepExecutor addr) {
        return executeI(core, addr);
    }

    private boolean execute(StepExecutor addr) {
        addr.warrior.addProcess(addr.addrA);
        addr.manager.nextWarrior();
        return false;
    }
}
