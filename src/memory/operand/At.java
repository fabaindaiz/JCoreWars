package memory.operand;

import memory.Address;
import memory.Memory;

public class At extends _AOperand {

    @Override
    public String toString(){
        return "@";
    }

    @Override
    public void executeA(Memory[] core, Address addr) {
        addr.addrA = (core[addr.tempAddr].bValue + addr.tempAddr) % addr.executer.coreSize;

        addr.instrA.copy(core[addr.addrA]);
        addr.addrAAValue = core[addr.addrA].aValue;
        addr.addrABValue = core[addr.addrA].bValue;

        addr.report.increment(addr.tempAddr);
    }

    @Override
    public void executeB(Memory[] core, Address addr) {
        addr.addrB = (core[addr.tempAddr].bValue + addr.tempAddr) % addr.executer.coreSize;

        addr.instrB.copy(core[addr.addrB]);
        addr.addrBAValue = core[addr.addrB].aValue;
        addr.addrBBValue = core[addr.addrB].bValue;

        addr.report.increment(addr.tempAddr);
    }
}
