package memory.operator;

import memory.Address;
import memory.Memory;

public class LDP extends _AOperator {

    @Override
    public String toString() {
        return "LDP";
    }

    @Override
    public void preExecute(Address addr) {
        addr.report.read(addr.addrA);
    }

    @Override
    public void postExecute(Address addr) {
        addr.report.write(addr.addrB);
    }

    @Override
    public boolean executeI(Memory[] core, Address addr) {
        return true;
    }

    @Override
    public boolean executeA(Memory[] core, Address addr) {
        core[addr.addrB].aValue = addr.executer.currentW.getPCell(addr.addrAAValue);
        return true;
    }

    @Override
    public boolean executeB(Memory[] core, Address addr) {
        core[addr.addrB].bValue = addr.executer.currentW.getPCell(addr.addrABValue);
        return true;
    }

    @Override
    public boolean executeAB(Memory[] core, Address addr) {
        core[addr.addrB].bValue = addr.executer.currentW.getPCell(addr.addrAAValue);
        return true;
    }

    @Override
    public boolean executeBA(Memory[] core, Address addr) {
        core[addr.addrB].aValue = addr.executer.currentW.getPCell(addr.addrABValue);
        return true;
    }

    @Override
    public boolean executeF(Memory[] core, Address addr) {
        return true;
    }

    @Override
    public boolean executeX(Memory[] core, Address addr) {
        return true;
    }
}
