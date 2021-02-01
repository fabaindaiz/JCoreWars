package memory.operator;

import memory.Address;
import memory.Memory;

public class CMP extends _AOperator {

    @Override
    public String toString() {
        return "CMP";
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
        if (!core[addr.addrB].equals(core[addr.addrA]))
            return true;
        // fallthrough for rest
        return true;
    }

    @Override
    public boolean executeA(Memory[] core, Address addr) {
        if (addr.addrBAValue != addr.addrAAValue)
            return true;
        addr.executer.currentW.addProc((addr.IP + 2) % addr.executer.coreSize);
        addr.executer.currentW = addr.executer.currentW.getNextWarrior();
        return false;
    }

    @Override
    public boolean executeB(Memory[] core, Address addr) {
        if (addr.addrBBValue != addr.addrABValue)
            return true;
        addr.executer.currentW.addProc((addr.IP + 2) % addr.executer.coreSize);
        addr.executer.currentW = addr.executer.currentW.getNextWarrior();
        return false;
    }

    @Override
    public boolean executeAB(Memory[] core, Address addr) {
        if (addr.addrBBValue != addr.addrAAValue)
            return true;
        addr.executer.currentW.addProc((addr.IP + 2) % addr.executer.coreSize);
        addr.executer.currentW = addr.executer.currentW.getNextWarrior();
        return false;
    }

    @Override
    public boolean executeBA(Memory[] core, Address addr) {
        if (addr.addrBAValue != addr.addrABValue)
            return true;
        addr.executer.currentW.addProc((addr.IP + 2) % addr.executer.coreSize);
        addr.executer.currentW = addr.executer.currentW.getNextWarrior();
        return false;
    }

    @Override
    public boolean executeF(Memory[] core, Address addr) {
        if (addr.addrBAValue != addr.addrAAValue)
            return true;
        // fallthrough for rest
        return true;
    }

    @Override
    public boolean executeX(Memory[] core, Address addr) {
        if (addr.addrBBValue != addr.addrAAValue)
            return true;
        // fallthrough for rest
        return true;
    }
}
