package Memory.operand;

import Memory.Address;
import Memory.Memory;

public class Peso extends _AOperand {

    @Override
    public String toString(){
        return "$";
    }

    @Override
    public void executeA(Memory[] core, Address addr) {
        addr.addrA = (addr.IP + addr.instr.aValue) % addr.coreSize;
        addr.instrA.copy(core[(addr.IP + addr.instr.aValue) % addr.coreSize]);
        addr.addrAAValue = core[addr.addrA].aValue;
        addr.addrABValue = core[addr.addrA].bValue;
    }

    @Override
    public void executeB(Memory[] core, Address addr) {
        addr.addrB = (addr.IP + addr.instr.bValue) % addr.coreSize;
        addr.instrB.copy(core[(addr.IP + addr.instr.bValue) % addr.coreSize]);
        addr.addrBAValue = core[addr.addrB].aValue;
        addr.addrBBValue = core[addr.addrB].bValue;
    }
}
