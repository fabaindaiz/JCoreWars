package memory.operator;

import memory.Address;
import memory.Memory;

public class SUB extends _AOperator {

    @Override
    public String toString(){
        return "SUB";
    }

    @Override
    public void preExecute(Address addr) {
        return;
    }

    @Override
    public void postExecute(Address addr) {
        addr.report.read(addr.addrA);
        addr.report.write(addr.addrB);
    }

    @Override
    public boolean executeI(Memory[] core, Address addr) {
        if ((core[addr.addrB].aValue = addr.addrBAValue - addr.addrAAValue) < 0)
            core[addr.addrB].aValue += addr.executer.coreSize;
        if ((core[addr.addrB].bValue = addr.addrBBValue - addr.addrABValue) < 0)
            core[addr.addrB].bValue += addr.executer.coreSize;
        return true;
    }

    @Override
    public boolean executeA(Memory[] core, Address addr) {
        if ((core[addr.addrB].aValue = addr.addrBAValue - addr.addrAAValue) < 0)
            core[addr.addrB].aValue += addr.executer.coreSize;
        return true;
    }

    @Override
    public boolean executeB(Memory[] core, Address addr) {
        if ((core[addr.addrB].bValue = addr.addrBBValue - addr.addrABValue) < 0)
            core[addr.addrB].bValue += addr.executer.coreSize;
        return true;
    }

    @Override
    public boolean executeAB(Memory[] core, Address addr) {
        if ((core[addr.addrB].bValue = addr.addrBAValue - addr.addrABValue) < 0)
            core[addr.addrB].bValue += addr.executer.coreSize;
        return true;
    }

    @Override
    public boolean executeBA(Memory[] core, Address addr) {
        if ((core[addr.addrB].aValue = addr.addrBBValue - addr.addrAAValue) < 0)
            core[addr.addrB].aValue += addr.executer.coreSize;
        return true;
    }

    @Override
    public boolean executeF(Memory[] core, Address addr) {
        return true;
    }

    @Override
    public boolean executeX(Memory[] core, Address addr) {
        if ((core[addr.addrB].bValue = addr.addrBAValue - addr.addrABValue) < 0)
            core[addr.addrB].bValue += addr.executer.coreSize;
        if ((core[addr.addrB].aValue = addr.addrBBValue - addr.addrAAValue) < 0)
            core[addr.addrB].aValue += addr.executer.coreSize;
        return true;
    }
}
