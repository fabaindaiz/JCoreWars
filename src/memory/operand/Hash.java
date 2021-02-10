package memory.operand;

import memory.Address;
import memory.Memory;

public class Hash extends _AOperand{

    @Override
    public String toString(){
        return " #";
    }

    @Override
    public void executeA(Memory[] core, Address addr) {
        addr.addrA = addr.IP;
        addr.instrA.copy(core[addr.IP]);
        addr.addrAAValue = addr.instr.aValue;
        addr.addrABValue = addr.instr.bValue;
    }

    @Override
    public void executeB(Memory[] core, Address addr) {
        addr.addrB = addr.IP;
        addr.instrB.copy(core[addr.IP]);
        addr.addrBAValue = addr.instr.aValue;
        addr.addrBBValue = addr.instr.bValue;
    }
}
