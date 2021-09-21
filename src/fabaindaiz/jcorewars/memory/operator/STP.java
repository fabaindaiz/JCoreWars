package fabaindaiz.jcorewars.memory.operator;

import fabaindaiz.jcorewars.marsVM.StepExecutor;
import fabaindaiz.jcorewars.memory.Memory;

public class STP implements _IOperator {

    @Override
    public String toString() {
        return "STP";
    }

    @Override
    public void preExecute(StepExecutor addr) {
        addr.report.read(addr.addrA);
    }

    @Override
    public void postExecute(StepExecutor addr) {
        return;
    }

    @Override
    public boolean executeI(Memory[] core, StepExecutor addr) {
        return true;
    }

    @Override
    public boolean executeA(Memory[] core, StepExecutor addr) {
        addr.warrior.setPCell(addr.addrBAValue, addr.addrAAValue);
        return true;
    }

    @Override
    public boolean executeB(Memory[] core, StepExecutor addr) {
        addr.warrior.setPCell(addr.addrBBValue, addr.addrABValue);
        return true;
    }

    @Override
    public boolean executeAB(Memory[] core, StepExecutor addr) {
        addr.warrior.setPCell(addr.addrBBValue, addr.addrAAValue);
        return true;
    }

    @Override
    public boolean executeBA(Memory[] core, StepExecutor addr) {
        addr.warrior.setPCell(addr.addrBAValue, addr.addrABValue);
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
