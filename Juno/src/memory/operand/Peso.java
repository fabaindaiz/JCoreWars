package memory.operand;

import memory.Address;
import memory.Memory;

public class Peso extends _AOperand {

    @Override
    public String toString(){
        return "$";
    }

    @Override
    public void executeA(Memory[] core, Address addr) {
        addr.addrA = (addr.IP + addr.instr.aValue) % addr.executer.coreSize;
        addr.instrA.copy(core[(addr.IP + addr.instr.aValue) % addr.executer.coreSize]);
        addr.addrAAValue = core[addr.addrA].aValue;
        addr.addrABValue = core[addr.addrA].bValue;
    }

    @Override
    public void executeB(Memory[] core, Address addr) {
        addr.addrB = (addr.IP + addr.instr.bValue) % addr.executer.coreSize;
        addr.instrB.copy(core[(addr.IP + addr.instr.bValue) % addr.executer.coreSize]);
        addr.addrBAValue = core[addr.addrB].aValue;
        addr.addrBBValue = core[addr.addrB].bValue;
    }
}
