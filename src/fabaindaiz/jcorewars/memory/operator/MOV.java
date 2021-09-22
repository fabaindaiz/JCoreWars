package fabaindaiz.jcorewars.memory.operator;

import fabaindaiz.jcorewars.marsVM.StepExecutor;
import fabaindaiz.jcorewars.memory.Memory;

/**
 * MOV replaces the B-target with the A-value
 */
public class MOV implements _IOperator {

    @Override
    public String toString(){
        return "MOV";
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
        core[addr.addrB].copy(addr.instrA);
        return true;
    }

    @Override
    public boolean executeA(Memory[] core, StepExecutor addr) {
        core[addr.addrB].aValue = addr.addrAAValue;
        return true;
    }

    @Override
    public boolean executeB(Memory[] core, StepExecutor addr) {
        core[addr.addrB].bValue = addr.addrABValue;
        return true;
    }

    @Override
    public boolean executeAB(Memory[] core, StepExecutor addr) {
        core[addr.addrB].bValue = addr.addrAAValue;
        return true;
    }

    @Override
    public boolean executeBA(Memory[] core, StepExecutor addr) {
        core[addr.addrB].aValue = addr.addrABValue;
        return true;
    }

    @Override
    public boolean executeF(Memory[] core, StepExecutor addr) {
        core[addr.addrB].aValue = addr.addrAAValue;
        core[addr.addrB].bValue = addr.addrABValue;
        return true;
    }

    @Override
    public boolean executeX(Memory[] core, StepExecutor addr) {
        core[addr.addrB].bValue = addr.addrAAValue;
        core[addr.addrB].aValue = addr.addrABValue;
        return true;
    }
}
