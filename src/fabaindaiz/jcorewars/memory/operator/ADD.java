package fabaindaiz.jcorewars.memory.operator;

import fabaindaiz.jcorewars.marsVM.StepExecutor;
import fabaindaiz.jcorewars.memory.Memory;

/**
 * ADD replaces the B-target with the sum of the A-value and the B-value
 */
public class ADD implements _IOperator {

    @Override
    public String toString() {
        return "ADD";
    }

    @Override
    public void preExecute(StepExecutor addr) {
        return;
    }

    @Override
    public void postExecute(StepExecutor addr) {
        addr.report.read(addr.addrA);
        addr.report.write(addr.addrB);
    }

    @Override
    public boolean executeI(Memory[] core, StepExecutor addr) {
        core[addr.addrB].aValue = (addr.addrBAValue + addr.addrAAValue) % addr.executer.coreSize;
        core[addr.addrB].bValue = (addr.addrBBValue + addr.addrABValue) % addr.executer.coreSize;
        return true;
    }

    @Override
    public boolean executeA(Memory[] core, StepExecutor addr) {
        core[addr.addrB].aValue = (addr.addrBAValue + addr.addrAAValue) % addr.executer.coreSize;
        return true;
    }

    @Override
    public boolean executeB(Memory[] core, StepExecutor addr) {
        core[addr.addrB].bValue = (addr.addrBBValue + addr.addrABValue) % addr.executer.coreSize;
        return true;
    }

    @Override
    public boolean executeAB(Memory[] core, StepExecutor addr) {
        core[addr.addrB].bValue = (addr.addrBBValue + addr.addrAAValue) % addr.executer.coreSize;
        return true;
    }

    @Override
    public boolean executeBA(Memory[] core, StepExecutor addr) {
        core[addr.addrB].aValue = (addr.addrBAValue + addr.addrABValue) % addr.executer.coreSize;
        return true;
    }

    @Override
    public boolean executeF(Memory[] core, StepExecutor addr) {
        return executeI(core, addr);
    }

    @Override
    public boolean executeX(Memory[] core, StepExecutor addr) {
        core[addr.addrB].bValue = (addr.addrBAValue + addr.addrABValue) % addr.executer.coreSize;
        core[addr.addrB].aValue = (addr.addrBBValue + addr.addrAAValue) % addr.executer.coreSize;
        return true;
    }
}
