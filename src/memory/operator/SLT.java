package memory.operator;

import memory.Address;
import memory.Memory;

public class SLT extends _AOperator {

    @Override
    public String toString() {
        return "SLT";
    }

    @Override
    public void preExecute(Address addr) {
        addr.report.read(addr.addrA);
        addr.report.read(addr.addrB);
    }

    @Override
    public void postExecute(Address addr) {
        return;
    }

    @Override
    public boolean executeI(Memory[] core, Address addr) {
        if ((addr.addrAAValue < addr.addrBAValue) && (addr.addrABValue < addr.addrBBValue))
            return execute(addr);
        return true;
    }

    @Override
    public boolean executeA(Memory[] core, Address addr) {
        if (addr.addrAAValue < addr.addrBAValue)
            return execute(addr);
        return true;
    }

    @Override
    public boolean executeB(Memory[] core, Address addr) {
        if (addr.addrABValue < addr.addrBBValue)
            return execute(addr);
        return true;
    }

    @Override
    public boolean executeAB(Memory[] core, Address addr) {
        if (addr.addrAAValue < addr.addrBBValue)
            return execute(addr);
        return true;
    }

    @Override
    public boolean executeBA(Memory[] core, Address addr) {
        if (addr.addrABValue <= addr.addrBAValue)
            return execute(addr);
        return true;
    }

    @Override
    public boolean executeF(Memory[] core, Address addr) {
        return true;
    }

    @Override
    public boolean executeX(Memory[] core, Address addr) {
        if ((addr.addrAAValue < addr.addrBBValue) && (addr.addrABValue < addr.addrBAValue))
            return execute(addr);
        return true;
    }

    private boolean execute(Address addr) {
        addr.executer.currentW.addProc((addr.IP + 2) % addr.executer.coreSize);
        addr.executer.currentW = addr.executer.currentW.getNextWarrior();
        return false;
    }
}
