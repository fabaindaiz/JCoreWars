package fabaindaiz.jcorewars.memory.operand;

import fabaindaiz.jcorewars.marsVM.StepExecutor;
import fabaindaiz.jcorewars.memory.Memory;

/**
 * Immediate address
 */
public class Hash implements _IOperand {

    @Override
    public String toString() {
        return " #";
    }

    @Override
    public void executeA(Memory[] core, StepExecutor addr) {
        addr.addrA = addr.currentInstr;
        addr.instrA.copy(core[addr.currentInstr]);
        addr.addrAAValue = addr.instr.aValue;
        addr.addrABValue = addr.instr.bValue;
    }

    @Override
    public void executeB(Memory[] core, StepExecutor addr) {
        addr.addrB = addr.currentInstr;
        addr.instrB.copy(core[addr.currentInstr]);
        addr.addrBAValue = addr.instr.aValue;
        addr.addrBBValue = addr.instr.bValue;
    }
}
