package memory.operator;

import memory.Address;
import memory.Memory;

public class STP extends _AOperator {

    @Override
    public String toString() {
        return "STP";
    }

    @Override
    public void preExecute(Address addr) {
        addr.report.read(addr.addrA);
    }

    @Override
    public void postExecute(Address addr) {
        return;
    }

    @Override
    public boolean executeI(Memory[] core, Address addr) {
        return true;
    }

    @Override
    public boolean executeA(Memory[] core, Address addr) {
        addr.executer.currentW.setPCell(addr.addrBAValue, addr.addrAAValue);
        return true;
    }

    @Override
    public boolean executeB(Memory[] core, Address addr) {
        addr.executer.currentW.setPCell(addr.addrBBValue, addr.addrABValue);
        return true;
    }

    @Override
    public boolean executeAB(Memory[] core, Address addr) {
        addr.executer.currentW.setPCell(addr.addrBBValue, addr.addrAAValue);
        return true;
    }

    @Override
    public boolean executeBA(Memory[] core, Address addr) {
        addr.executer.currentW.setPCell(addr.addrBAValue, addr.addrABValue);
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
