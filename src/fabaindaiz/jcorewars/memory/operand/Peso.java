package fabaindaiz.jcorewars.memory.operand;

import fabaindaiz.jcorewars.marsVM.StepExecutor;
import fabaindaiz.jcorewars.memory.Memory;

/**
 * Direct address
 */
public class Peso implements _IOperand {

    @Override
    public String toString() {
        return " $";
    }

    @Override
    public void executeA(Memory[] core, StepExecutor addr) {
        addr.addrA = (addr.currentInstr + addr.instr.aValue) % addr.executer.coreSize;
        addr.instrA.copy(core[(addr.currentInstr + addr.instr.aValue) % addr.executer.coreSize]);
        addr.addrAAValue = core[addr.addrA].aValue;
        addr.addrABValue = core[addr.addrA].bValue;
    }

    @Override
    public void executeB(Memory[] core, StepExecutor addr) {
        addr.addrB = (addr.currentInstr + addr.instr.bValue) % addr.executer.coreSize;
        addr.instrB.copy(core[(addr.currentInstr + addr.instr.bValue) % addr.executer.coreSize]);
        addr.addrBAValue = core[addr.addrB].aValue;
        addr.addrBBValue = core[addr.addrB].bValue;
    }
}
