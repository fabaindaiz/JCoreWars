package fabaindaiz.jcorewars.memory.operator;

import fabaindaiz.jcorewars.marsVM.StepExecutor;
import fabaindaiz.jcorewars.memory.Memory;

/**
 * SUB replaces the B-target with the difference of the B-value and the A-value
 */
public class SUB implements _IOperator {

    @Override
    public String toString(){
        return "SUB";
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
        if ((core[addr.addrB].aValue = addr.addrBAValue - addr.addrAAValue) < 0)
            core[addr.addrB].aValue += addr.executer.coreSize;
        if ((core[addr.addrB].bValue = addr.addrBBValue - addr.addrABValue) < 0)
            core[addr.addrB].bValue += addr.executer.coreSize;
        return true;
    }

    @Override
    public boolean executeA(Memory[] core, StepExecutor addr) {
        if ((core[addr.addrB].aValue = addr.addrBAValue - addr.addrAAValue) < 0)
            core[addr.addrB].aValue += addr.executer.coreSize;
        return true;
    }

    @Override
    public boolean executeB(Memory[] core, StepExecutor addr) {
        if ((core[addr.addrB].bValue = addr.addrBBValue - addr.addrABValue) < 0)
            core[addr.addrB].bValue += addr.executer.coreSize;
        return true;
    }

    @Override
    public boolean executeAB(Memory[] core, StepExecutor addr) {
        if ((core[addr.addrB].bValue = addr.addrBBValue - addr.addrAAValue) < 0)
            core[addr.addrB].bValue += addr.executer.coreSize;
        return true;
    }

    @Override
    public boolean executeBA(Memory[] core, StepExecutor addr) {
        if ((core[addr.addrB].aValue = addr.addrBAValue - addr.addrABValue) < 0)
            core[addr.addrB].aValue += addr.executer.coreSize;
        return true;
    }

    @Override
    public boolean executeF(Memory[] core, StepExecutor addr) {
        return executeI(core, addr);
    }

    @Override
    public boolean executeX(Memory[] core, StepExecutor addr) {
        if ((core[addr.addrB].bValue = addr.addrBAValue - addr.addrABValue) < 0)
            core[addr.addrB].bValue += addr.executer.coreSize;
        if ((core[addr.addrB].aValue = addr.addrBBValue - addr.addrAAValue) < 0)
            core[addr.addrB].aValue += addr.executer.coreSize;
        return true;
    }
}
