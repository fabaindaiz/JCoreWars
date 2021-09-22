package fabaindaiz.jcorewars.memory.operator;

import fabaindaiz.jcorewars.marsVM.StepExecutor;
import fabaindaiz.jcorewars.memory.Memory;

/**
 * LDP loads a number from private storage space
 */
public class LDP implements _IOperator {

    @Override
    public String toString() {
        return "LDP";
    }

    @Override
    public void preExecute(StepExecutor addr) {
        addr.report.read(addr.addrA);
    }

    @Override
    public void postExecute(StepExecutor addr) {
        addr.report.write(addr.addrB);
    }

    @Override
    public boolean executeI(Memory[] core, StepExecutor addr) {
        return true;
    }

    @Override
    public boolean executeA(Memory[] core, StepExecutor addr) {
        core[addr.addrB].aValue = addr.warrior.getPCell(addr.addrAAValue);
        return true;
    }

    @Override
    public boolean executeB(Memory[] core, StepExecutor addr) {
        core[addr.addrB].bValue = addr.warrior.getPCell(addr.addrABValue);
        return true;
    }

    @Override
    public boolean executeAB(Memory[] core, StepExecutor addr) {
        core[addr.addrB].bValue = addr.warrior.getPCell(addr.addrAAValue);
        return true;
    }

    @Override
    public boolean executeBA(Memory[] core, StepExecutor addr) {
        core[addr.addrB].aValue = addr.warrior.getPCell(addr.addrABValue);
        return true;
    }

    @Override
    public boolean executeF(Memory[] core, StepExecutor addr) {
        return true;
    }

    @Override
    public boolean executeX(Memory[] core, StepExecutor addr) {
        return true;
    }
}
