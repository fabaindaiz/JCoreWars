package fabaindaiz.jcorewars.memory.operand;

import fabaindaiz.jcorewars.marsVM.StepExecutor;
import fabaindaiz.jcorewars.memory.Memory;

/**
 * A post increment address
 */
public class Close implements _IOperand {

    @Override
    public String toString() {
        return " }";
    }

    @Override
    public void executeA(Memory[] core, StepExecutor addr) {
        addr.addrA = (core[addr.tempAddr].aValue + addr.tempAddr) % addr.executer.coreSize;

        addr.instrA.copy(core[addr.addrA]);
        addr.addrAAValue = core[addr.addrA].aValue;
        addr.addrABValue = core[addr.addrA].bValue;

        addr.report.increment(addr.tempAddr);

        core[addr.tempAddr].aValue = (++core[addr.tempAddr].aValue) % addr.executer.coreSize;
        addr.report.increment(addr.tempAddr);
    }

    @Override
    public void executeB(Memory[] core, StepExecutor addr) {
        addr.addrB = (core[addr.tempAddr].aValue + addr.tempAddr) % addr.executer.coreSize;

        addr.instrB.copy(core[addr.addrB]);
        addr.addrBAValue = core[addr.addrB].aValue;
        addr.addrBBValue = core[addr.addrB].bValue;

        addr.report.increment(addr.tempAddr);

        core[addr.tempAddr].aValue = (++core[addr.tempAddr].aValue) % addr.executer.coreSize;
        addr.report.increment(addr.tempAddr);
    }
}
