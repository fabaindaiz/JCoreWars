package fabaindaiz.jcorewars.memory.operator;

import fabaindaiz.jcorewars.marsVM.StepExecutor;
import fabaindaiz.jcorewars.memory.Memory;

public class MUL implements _IOperator {

    @Override
    public String toString(){
        return "MUL";
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
        core[addr.addrB].aValue = (int) ((long) addr.addrBAValue * addr.addrAAValue % addr.executer.coreSize);
        core[addr.addrB].bValue = (int) ((long) addr.addrBBValue * addr.addrABValue % addr.executer.coreSize);
        return true;
    }

    @Override
    public boolean executeA(Memory[] core, StepExecutor addr) {
        core[addr.addrB].aValue = (int) ((long) addr.addrBAValue * addr.addrAAValue % addr.executer.coreSize);
        return true;
    }

    @Override
    public boolean executeB(Memory[] core, StepExecutor addr) {
        core[addr.addrB].bValue = (int) ((long) addr.addrBBValue * addr.addrABValue % addr.executer.coreSize);
        return true;
    }

    @Override
    public boolean executeAB(Memory[] core, StepExecutor addr) {
        core[addr.addrB].bValue = (int) ((long) addr.addrBBValue * addr.addrAAValue % addr.executer.coreSize);
        return true;
    }

    @Override
    public boolean executeBA(Memory[] core, StepExecutor addr) {
        core[addr.addrB].aValue = (int) ((long) addr.addrBAValue * addr.addrABValue % addr.executer.coreSize);
        return true;
    }

    @Override
    public boolean executeF(Memory[] core, StepExecutor addr) {
        return executeI(core, addr);
    }

    @Override
    public boolean executeX(Memory[] core, StepExecutor addr) {
        core[addr.addrB].bValue = (int) ((long) addr.addrBAValue * addr.addrABValue % addr.executer.coreSize);
        core[addr.addrB].aValue = (int) ((long) addr.addrBBValue * addr.addrAAValue % addr.executer.coreSize);
        return true;
    }
}
