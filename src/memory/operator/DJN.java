package memory.operator;

import memory.Address;
import memory.Memory;

public class DJN extends _AOperator {

    @Override
    public String toString() {
        return "DJN";
    }

    @Override
    public void preExecute(Address addr) {
        addr.report.decrement(addr.addrB);
    }

    @Override
    public void postExecute(Address addr) {
        return;
    }

    @Override
    public boolean executeI(Memory[] core, Address addr) {
        if (--core[addr.addrB].aValue < 0)
            core[addr.addrB].aValue = addr.executer.coreSize - 1;
        if (--core[addr.addrB].bValue < 0)
            core[addr.addrB].bValue = addr.executer.coreSize - 1;
        if ((addr.addrBAValue != 1) || (addr.addrBBValue != 1))
            return execute(addr);
        return true;
    }

    @Override
    public boolean executeA(Memory[] core, Address addr) {
        return true;
    }

    @Override
    public boolean executeB(Memory[] core, Address addr) {
        return true;
    }

    @Override
    public boolean executeAB(Memory[] core, Address addr) {
        if (--core[addr.addrB].bValue < 0)
            core[addr.addrB].bValue = addr.executer.coreSize - 1;
        if (addr.addrBBValue != 1)
            return execute(addr);
        return true;
    }

    @Override
    public boolean executeBA(Memory[] core, Address addr) {
        if (--core[addr.addrB].aValue < 0)
            core[addr.addrB].aValue = addr.executer.coreSize - 1;
        if (addr.addrBAValue != 1)
            return execute(addr);
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

    private boolean execute(Address addr) {
        addr.executer.currentW.addProc(addr.addrA);
        addr.executer.currentW = addr.executer.currentW.getNextWarrior();
        return false;
    }
}
